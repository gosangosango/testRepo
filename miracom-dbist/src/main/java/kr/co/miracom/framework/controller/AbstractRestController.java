package kr.co.miracom.framework.controller;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.miracom.dbist.dml.Page;
import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListIn;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.framework.util.Closure;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ReflectionUtils;
import kr.co.miracom.framework.util.SyncCtrlUtils;
import kr.co.miracom.framework.util.ValueUtils;

public abstract class AbstractRestController<T, V, D, I extends GetListIn> {
    private Class<T> tableClass;
    private Class<V> itemClass;
    private Class<D> detailClass;
    private Class<I> getListInClass;

    public void setTableClass(Class<T> tableClass) {
        this.tableClass = tableClass;
    }

    public void setItemClass(Class<V> itemClass) {
        this.itemClass = itemClass;
    }

    public void setDetailClass(Class<D> detailClass) {
        this.detailClass = detailClass;
    }

    public void setGetListInClass(Class<I> getListInClass) {
        this.getListInClass = getListInClass;
    }

    private Class<?> getClass;
    private Method getMethod;
    private Class<?> postClass;
    private Method postMethod;
    private Class<?> putClass;
    private Method putMethod;
    private Class<?> saveListClass;
    private Method saveListMethod;
    private Class<?> deleteListClass;
    private Method deleteListMethod;
    private Class<?> getListClass;
    private Method getListMethod;
    private boolean loaded = false;

    private void load() throws Exception {
        if (loaded)
            return;

        SyncCtrlUtils.wrap(getClass().getName(), new Closure<Object, Exception>() {

            @Override
            public Object execute() throws Exception {
                if (loaded) {
                    return null;
                }

                String resource;
                String pkg = detailClass.getPackage().getName();
                {
                    String[] pkgs = StringUtils.tokenizeToStringArray(pkg, ".");
                    int pkgi = pkgs.length;
                    resource = pkgs[pkgi - 2];
                    resource = ValueUtils.toCamelCase(resource, '_', true);
                    pkgi--;
                    StringBuffer buf = new StringBuffer();
                    for (int i = 0; i < pkgi; i++) {
                        buf.append(pkgs[i]);
                        buf.append('.');
                    }
                    buf.append("service");
                    pkg = buf.toString();
                }

                getMethod = getServiceMethod(pkg + "." + resource + "Service", "get", String.class,
                                SelectOptions.class);
                getClass = getServiceClass(getMethod);

                putMethod = getServiceMethod(pkg + "." + resource + "Service", "put", String.class, detailClass,
                                String[].class);
                putClass = getServiceClass(putMethod);

                saveListMethod = getServiceMethod(pkg + ".save_list.Save" + resource + "List", "saveList", List.class,
                                String[].class);
                saveListClass = getServiceClass(saveListMethod);

                deleteListMethod = getServiceMethod(pkg + ".delete_list.Delete" + resource + "List", "deleteList",
                                List.class);
                deleteListClass = getServiceClass(deleteListMethod);

                getListMethod = getServiceMethod(pkg + ".get_list.Get" + resource + "List", "getList", getListInClass);
                getListClass = getServiceClass(getListMethod);

                loaded = true;
                return null;
            }

        });
    }

    private Method getServiceMethod(String className, String methodName, Class<?>... parameterTypes) throws Exception {
        try {
            Class<?> clazz = ClassUtils.getClass(className);
            Method method = clazz.getMethod(methodName, parameterTypes);
            return method;
        } catch (Exception e) {
        }
        return null;
    }

    private Class<?> getServiceClass(Method method) {
        if (method == null) {
            return null;
        }
        return method.getDeclaringClass();
    }

    public D get(@PathVariable String id, SelectOptions options) throws Exception {
        load();
        if (getMethod != null) {
            @SuppressWarnings("unchecked")
            D data = (D) getMethod.invoke(BeanUtils.get(getClass), id, options);
            return data;
        }
        D data = DbUtils.select(tableClass, id, options, detailClass, true);
        return data;
    }

    public void post(@PathVariable String id, @RequestBody D data, @RequestParam(required = false) String[] fieldName)
                    throws Exception {
        load();
        if (postMethod != null) {
            postMethod.invoke(BeanUtils.get(postClass), id, data, fieldName);
            return;
        }
        DbUtils.insert(tableClass, id, data, fieldName);
    }

    public void put(@PathVariable String id, @RequestBody D data, @RequestParam(required = false) String[] fieldName)
                    throws Exception {
        load();
        if (putMethod != null) {
            putMethod.invoke(BeanUtils.get(putClass), id, data, fieldName);
            return;
        }
        DbUtils.update(tableClass, id, data, fieldName);
    }

    public void saveList(@RequestBody List<V> list, @RequestParam(required = false) String[] fieldName)
                    throws Exception {
        load();
        if (saveListMethod != null) {
            saveListMethod.invoke(BeanUtils.get(saveListClass), list, fieldName);
            return;
        }
        DbUtils.upsertBatch(tableClass, list, fieldName);
    }

    public void deleteList(@RequestBody List<V> list) throws Exception {
        load();
        if (deleteListMethod != null) {
            deleteListMethod.invoke(BeanUtils.get(deleteListClass), list);
            return;
        }
        if (ReflectionUtils.getField(tableClass, "deleteFlag") == null) {
            DbUtils.deleteBatch(tableClass, list);
        } else {
            DbUtils.updateBatch(tableClass, list, new String[] { "deleteFlag", "deleteUserId", "deleteTime" });
        }
    }

    private static final Set<Field> DEFAULT_GET_LIST_FIELDS;
    static {
        DEFAULT_GET_LIST_FIELDS = new HashSet<>(ReflectionUtils.getFieldList(GetListIn.class, true));
    }

    public GetListOut<V> getList(I input) throws Exception {
        load();
        if (getListMethod != null) {
            @SuppressWarnings("unchecked")
            GetListOut<V> output = (GetListOut<V>) getListMethod.invoke(BeanUtils.get(getListClass), input);
            return output;
        }

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

}
