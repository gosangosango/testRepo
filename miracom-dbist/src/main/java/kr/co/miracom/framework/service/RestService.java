package kr.co.miracom.framework.service;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import kr.co.miracom.dbist.dml.Page;
import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.exception.BizException;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.Property;
import kr.co.miracom.framework.util.ReflectionUtils;
import kr.co.miracom.framework.util.ValueUtils;
import lombok.Setter;

@Setter
public class RestService<T, V, D> {
    private Class<T> tableClass;
    private Class<V> itemClass;
    private Class<D> detailClass;
    private String idValueRule;
    private String pkFieldName;
    private Map<String, Pattern> fieldPatterns;

    public RestService(Class<T> tableClass, Class<V> itemClass, Class<D> detailClass) {
        this.tableClass = tableClass;
        this.itemClass = itemClass;
        this.detailClass = detailClass;
    }

    public void addFieldPattern(String name, String regex) {
        if (fieldPatterns == null) {
            fieldPatterns = new LinkedHashMap<>();
        }
        Pattern pattern = Pattern.compile(regex);
        fieldPatterns.put(name, pattern);
    }

    public void setTableClass(Class<T> tableClass) {
        this.tableClass = tableClass;
    }

    public void setItemClass(Class<V> itemClass) {
        this.itemClass = itemClass;
    }

    public void setDetailClass(Class<D> detailClass) {
        this.detailClass = detailClass;
    }

    public D get(String id, SelectOptions options) throws Exception {
        D data = DbUtils.select(tableClass, id, options, detailClass, true);
        return data;
    }

    public void post(String id, D data, String[] fieldNames) throws Exception {
        id = adjustId(id, data);
        if (!ValueUtils.isEmpty(fieldPatterns)) {
            for (String fieldName : fieldPatterns.keySet()) {
                checkPattern(data, fieldName);
            }
        }
        DbUtils.insert(tableClass, id, data, fieldNames);
    }

    public void put(String id, D data, String[] fieldNames) throws Exception {
        DbUtils.update(tableClass, id, data, fieldNames);
    }

    public void delete(String id, D data) throws Exception {
        DbUtils.delete(tableClass, id, data);
    }

    private static final Set<Field> DEFAULT_GET_LIST_FIELDS;
    static {
        DEFAULT_GET_LIST_FIELDS = new HashSet<>(ReflectionUtils.getFieldList(GetListIn.class, true));
    }

    public GetListOut<V> getList(GetListIn input) throws Exception {
        Query query = new Query();
        if (ReflectionUtils.getField(tableClass, "factoryCode") != null) {
            query.addFilter("factoryCode", AuthUtils.getFactoryCode());
        }
        for (Field field : ReflectionUtils.getFieldList(input, true)) {
            if (DEFAULT_GET_LIST_FIELDS.contains(field)) {
                continue;
            }

            Object value = field.get(input);
            if (String.class.equals(field.getType())) {
                DbUtils.addContains(query, field.getName(), (String) value);
            } else {
                DbUtils.addEquals(query, field.getName(), value);
            }
        }
        Page<V> page = DbUtils.selectPage(tableClass, query, itemClass);
        GetListOut<V> output = new GetListOut<>();
        ValueUtils.populate(page, output);
        return output;
    }

    public void postList(List<V> list, String[] fieldNames) throws Exception {
        if (ValueUtils.isEmpty(list)) {
            return;
        }
        Field idField = ReflectionUtils.getField(list.get(0), "id", true);
        Field pkField = ValueUtils.isEmpty(pkFieldName) ? null
                        : ReflectionUtils.getField(list.get(0), pkFieldName, true);
        for (V data : list) {
            String id = ValueUtils.toString(idField.get(data));
            if (ValueUtils.isEmpty(id)) {
                if (pkField != null) {
                    id = ValueUtils.toString(pkField.get(data));
                    adjustId(id, data);
                }
            } else {
                id = adjustId(id, data);
                idField.set(data, id);
            }
            if (!ValueUtils.isEmpty(fieldPatterns)) {
                for (String fieldName : fieldPatterns.keySet()) {
                    checkPattern(data, fieldName);
                }
            }
        }
        DbUtils.insertBatch(list, fieldNames);
    }

    public void putList(List<V> list, String[] fieldNames) throws Exception {
        DbUtils.updateBatch(list, fieldNames);
    }

    public void saveList(List<V> list, String[] fieldNames) throws Exception {
        if (ValueUtils.isEmpty(list)) {
            return;
        }
        Field idField = ReflectionUtils.getField(list.get(0), "id", true);
        Field pkField = ValueUtils.isEmpty(pkFieldName) ? null
                        : ReflectionUtils.getField(list.get(0), pkFieldName, true);
        for (V data : list) {
            String id = ValueUtils.toString(idField.get(data));
            boolean insert = ValueUtils.isEmpty(id);
            if (insert) {
                if (pkField != null) {
                    id = ValueUtils.toString(pkField.get(data));
                    adjustId(id, data);
                }
            }
            if (!ValueUtils.isEmpty(fieldPatterns)) {
                for (String fieldName : fieldPatterns.keySet()) {
                    if (!insert && fieldName.equals(pkFieldName)) {
                        continue;
                    }
                    checkPattern(data, fieldName);
                }
            }
        }
        DbUtils.upsertBatch(tableClass, list, fieldNames);
    }

    public void deleteList(List<V> list) throws Exception {
        if (ValueUtils.isEmpty(list)) {
            return;
        }
        Field field = ReflectionUtils.getField(list.get(0), "deleteFlag");
        if (field == null) {
            DbUtils.deleteBatch(tableClass, list);
        } else {
            for (V item : list) {
                field.set(item, true);
            }
            DbUtils.updateBatch(tableClass, list, new String[] { "deleteFlag", "deleteUserId", "deleteTime" });
        }
    }

    private String adjustId(String id, Object data) throws Exception {
        if (idValueRule == null || ValueUtils.isEmpty(id)) {
            return id;
        }

        String rule = idValueRule.toUpperCase();
        if ("UPPER".equals(rule)) {
            id = id.toUpperCase();
        } else if ("LOWER".equals(rule)) {
            id = id.toLowerCase();
        }
        if (pkFieldName != null) {
            Field field = ReflectionUtils.getField(data, pkFieldName, true);
            field.set(data, id);
        }
        return id;
    }

    private void checkPattern(Object data, String fieldName) throws Exception {
        Field field = ReflectionUtils.getField(data, fieldName, true);
        String str = ValueUtils.toString(field.get(data));
        if (ValueUtils.isEmpty(str)) {
            return;
        }
        Pattern pattern = fieldPatterns.get(fieldName);
        if (!pattern.matcher(str).matches()) {
            throw new BizException("XXXXXX", "값 패턴이 유효하지 않습니다.", new Property(fieldName, str));
        }
    }
}
