package kr.co.miracom.framework.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ReflectionUtils;
import kr.co.miracom.framework.util.ValueUtils;

public class RestService4OneToMany<P, T, V> {
    private Class<P> parentClass;
    private Class<T> tableClass;
    private Class<V> itemClass;
    private Field seqNoField;
    private String[] pkFieldNames;

    public RestService4OneToMany(Class<P> parentClass, Class<T> tableClass, Class<V> itemClass) {
        this.parentClass = parentClass;
        this.tableClass = tableClass;
        this.itemClass = itemClass;
    }

    private Field getSeqNoField() {
        if (seqNoField == null) {
            seqNoField = ReflectionUtils.getField(tableClass, "seqNo");
        }
        return seqNoField;
    }

    private String[] getPkFieldNames() {
        if (pkFieldNames == null) {
            pkFieldNames = DbUtils.getPkFieldNames(tableClass);
        }
        return pkFieldNames;
    }

    private void populateChildKey(Object parent, Object child) throws Exception {
        for (String pkFieldName : getPkFieldNames()) {
            Field cfield = ReflectionUtils.getField(child, pkFieldName);
            Field pfield = ReflectionUtils.getField(parent, pkFieldName);
            if (pfield == null) {
                if (String.class.equals(cfield.getType())) {
                    cfield.set(child, "NONE");
                }
                continue;
            }
            Object value = pfield.get(parent);
            cfield.set(child, value);
        }
    }

    private String getCode(V item) throws Exception {
        StringBuffer buf = new StringBuffer();
        int i = 0;
        for (String pkFieldName : getPkFieldNames()) {
            Field pkField = ReflectionUtils.getField(item, pkFieldName);
            if (pkField == null) {
                continue;
            }
            Object value = pkField.get(item);
            buf.append(i++ == 0 ? "" : ",").append(value);
        }
        return buf.toString();
    }

    private Query newChildQuery(Object parent, Query query) throws Exception {
        query = query == null ? new Query() : query;
        for (String pkFieldName : getPkFieldNames()) {
            Field pfield = ReflectionUtils.getField(parent, pkFieldName);
            if (pfield == null) {
                continue;
            }
            Object value = pfield.get(parent);
            query.addFilter(pkFieldName, value);
        }
        if (getSeqNoField() != null) {
            query.addOrder("seqNo", true);
        }
        return query;
    }

    public GetListOut<V> getList(Object parentId) throws Exception {
        return getList(parentId, null);
    }

    public GetListOut<V> getList(Object parentId, Query query) throws Exception {
        P parent = DbUtils.select(parentClass, parentId, null, parentClass, true);
        if (query == null) {
            query = new Query();
        }
        if (getSeqNoField() != null) {
            query.addOrder("seqNo", true);
        }
        List<V> list = DbUtils.selectOneToManyList(tableClass, parent, itemClass, query);
        GetListOut<V> output = new GetListOut<>();
        output.setTotalSize(list.size());
        output.setList(list);
        return output;
    }

    public void saveList(Object parentId, List<V> list, String[] fieldNames) throws Exception {
        saveList(parentId, null, list, fieldNames);
    }

    public void saveList(Object parentId, Query query, List<V> list, String[] fieldNames) throws Exception {
        P parent = DbUtils.select(parentClass, parentId, null, parentClass, true);

        if (getSeqNoField() != null) {
            List<T> ilist = new ArrayList<>();
            int seqNo = 0;
            for (V item : list) {
                T data = tableClass.newInstance();
                populateChildKey(parent, data);
                ValueUtils.populate(item, data);
                getSeqNoField().set(data, ++seqNo);
                ilist.add(data);
            }
            query = newChildQuery(parent, query);
            DbUtils.deleteList(tableClass, query);
            DbUtils.insertBatch(ilist);
            return;
        }

        Map<String, V> oldCodes = new HashMap<>();
        for (V item : getList(parentId).getList()) {
            String code = getCode(item);
            oldCodes.put(code, item);
        }

        List<T> ilist = new ArrayList<>();
        List<T> dlist = new ArrayList<>();
        for (V item : list) {
            String code = getCode(item);
            if (oldCodes.containsKey(code)) {
                oldCodes.remove(code);
                continue;
            }
            T data = tableClass.newInstance();
            populateChildKey(parent, data);
            ValueUtils.populate(item, data);
            ilist.add(data);
        }

        for (V item : oldCodes.values()) {
            T data = tableClass.newInstance();
            populateChildKey(parent, data);
            ValueUtils.populate(item, data);
            dlist.add(data);
        }

        DbUtils.insertBatch(ilist, fieldNames);
        DbUtils.deleteBatch(dlist);
    }

    public void deleteList(Object parentId, List<V> list) throws Exception {
        deleteList(parentId, null, list);
    }

    public void deleteList(Object parentId, Query query, List<V> list) throws Exception {
        P parent = DbUtils.select(parentClass, parentId, null, parentClass, true);

        List<T> dlist = new ArrayList<>();
        for (V item : list) {
            T data = tableClass.newInstance();
            populateChildKey(parent, data);
            ValueUtils.populate(item, data);
            dlist.add(data);
        }

        DbUtils.deleteBatch(dlist);

        List<T> ulist = new ArrayList<>();
        if (getSeqNoField() != null) {
            query = newChildQuery(parent, query);
            List<T> dataList = DbUtils.selectList(tableClass, query);
            int seqNo = 0;
            for (T data : dataList) {
                Object value = getSeqNoField().get("seqNo");
                if (value.equals(++seqNo)) {
                    continue;
                }
                getSeqNoField().set(data, seqNo);
            }
            DbUtils.updateBatch(ulist, new String[] { "seqNo" });
        }
    }
}
