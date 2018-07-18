package kr.co.miracom.framework.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.util.StringUtils;

import kr.co.miracom.dbist.dml.Query;

public class CacheUtils {
    public static final Object NULL = "";

    public static boolean isNull(Object data) {
        return data == null || NULL.equals(data);
    }

    public static Object get(String name, Object key, boolean clone) throws Exception {
        if (name == null || key == null) {
            return null;
        }

        String keyStr = toKeyStr(key);
        if (keyStr == null) {
            return null;
        }

        // Thread Cache
        {
            @SuppressWarnings("unchecked")
            Map<String, Object> tc = (Map<String, Object>) ThreadUtils.getProp(name);
            if (tc != null && tc.containsKey(keyStr)) {
                Object data = tc.get(keyStr);
                if (clone && !NULL.equals(data)) {
                    data = ValueUtils.clone(data);
                }
                return data;
            }
        }

        // TODO myjung.jung

        return null;
    }

    public static void put(String name, Object key, Object data, boolean clone) throws Exception {
        if (name == null || key == null) {
            return;
        }

        String keyStr = toKeyStr(key);
        if (keyStr == null) {
            return;
        }

        if (data == null) {
            data = NULL;
        } else if (clone) {
            data = ValueUtils.clone(data);
        }

        // Thread Cache
        {
            @SuppressWarnings("unchecked")
            Map<String, Object> tc = (Map<String, Object>) ThreadUtils.getProp(name);
            if (tc == null) {
                tc = new HashMap<>();
                ThreadUtils.setProp(name, tc);
            }

            tc.put(keyStr, data);
        }

        // TODO myjung.jung
    }

    private static void remove(String name, Object key) throws Exception {
        if (name == null || key == null) {
            return;
        }

        String keyStr = toKeyStr(key);
        if (keyStr == null) {
            return;
        }

        // Thread Cache
        {
            @SuppressWarnings("unchecked")
            Map<String, Object> tc = (Map<String, Object>) ThreadUtils.getProp(name);

            if (tc != null && tc.containsKey(keyStr)) {
                tc.remove(keyStr);
            }
        }

        // TODO myjung.jung
    }

    private static void removeAll(String name) throws Exception {
        if (name == null) {
            return;
        }

        // Thread Cache
        {
            @SuppressWarnings("unchecked")
            Map<String, Object> tc = (Map<String, Object>) ThreadUtils.getProp(name);

            if (tc != null) {
                tc.clear();
            }
        }

        // TODO myjung.jung
    }

    public static Object get(Class<?> clazz, Object key) throws Exception {
        Object data = get(clazz, key, true);
        return data;
    }

    public static Object get(Class<?> clazz, Object key, boolean clone) throws Exception {
        if (clazz == null || key == null) {
            return null;
        }

        String name = toName(clazz, key);
        String keyStr = toKeyStr(clazz, key);

        Object data = get(name, keyStr, clone);

        return data;
    }

    private static final Set<String> GCM = new HashSet<>();

    public static void put(Class<?> clazz, Object key, Object data) throws Exception {
        put(clazz, key, data, true);
    }

    public static void put(Class<?> clazz, Object key, Object data, boolean clone) throws Exception {
        if (clazz == null || key == null) {
            return;
        }

        String name = toName(clazz, key);
        String keyStr = toKeyStr(clazz, key);
        if (name == null || keyStr == null) {
            return;
        }

        put(name, keyStr, data, clone);

        if (name.startsWith("GCM_")) {
            synchronized (GCM) {
                GCM.add(name);
            }
        }
    }

    public static void remove(Class<?> clazz, Object key) throws Exception {
        if (clazz == null || key == null) {
            return;
        }

        String name = toName(clazz, key);
        String keyStr = toKeyStr(clazz, key);

        remove(name, keyStr);
    }

    public static void removeAll(Class<?> clazz) throws Exception {
        if (clazz == null) {
            return;
        }
        boolean gcm = clazz.getSimpleName().equals("MGcmTblDat");

        if (gcm) {
            synchronized (GCM) {
                for (String name : GCM) {
                    removeAll(name);
                }
                GCM.clear();
            }

        } else {
            String name = toName(clazz, null);
            if (name == null) {
                return;
            }

            removeAll(name);
        }
    }

    private static String toName(Class<?> clazz, Object key) {
        if (!clazz.getSimpleName().equals("MGcmTblDat")) {
            return clazz.getSimpleName();
        }

        if (key == null || key instanceof Query) {
            return null;
        }

        if ((key instanceof Object[])) {
            Object[] keys = (Object[]) key;
            return keys.length > 2 ? "GCM_" + ValueUtils.toString(keys[1]) : null;
        } else if (key instanceof List) {
            @SuppressWarnings("unchecked")
            List<Object> keys = (List<Object>) key;
            return keys.size() > 2 ? "GCM_" + ValueUtils.toString(keys.get(1)) : null;
        }

        return null;
    }

    private static String toKeyStr(Class<?> clazz, Object key) throws Exception {
        StringBuffer buf = new StringBuffer();

        if (key instanceof String) {
            String str = (String) key;
            str = StringUtils.replace(str, ", ", ",");
            return str;
        }

        if ((key instanceof Object[])) {
            Object[] keys = (Object[]) key;
            int i = 0;
            int size = keys.length;
            for (@SuppressWarnings("unused")
            String fieldName : DbUtils.getPkFieldNames(clazz)) {
                buf.append(i == 0 ? "" : ",").append(size > i ? ValueUtils.toString(keys[i++]) : "null");
            }
            return buf.toString();

        } else if (key instanceof List) {
            @SuppressWarnings("unchecked")
            List<Object> keys = (List<Object>) key;
            int i = 0;
            int size = keys.size();
            for (@SuppressWarnings("unused")
            String fieldName : DbUtils.getPkFieldNames(clazz))
                buf.append(i == 0 ? "" : ",").append(size > i ? ValueUtils.toString(keys.get(i++)) : "null");
            return buf.toString();

        } else if (key instanceof Query) {
            return null;
        }

        int i = 0;
        for (String fieldName : DbUtils.getPkFieldNames(clazz)) {
            String value = ValueUtils.getString(key, fieldName);
            buf.append(i++ == 0 ? "" : ",").append(ValueUtils.isEmpty(value) ? "null" : value);
        }
        return buf.toString();
    }

    private static String toKeyStr(Object key) throws Exception {
        if (key instanceof String) {
            return (String) key;
        }

        StringBuffer buf = new StringBuffer();

        if ((key instanceof Object[])) {
            Object[] keys = (Object[]) key;
            int i = 0;
            for (Object keyObj : keys) {
                buf.append(i++ == 0 ? "" : ",").append(ValueUtils.toString(keyObj));
            }
            return buf.toString();

        } else if (key instanceof List) {
            @SuppressWarnings("unchecked")
            List<Object> keys = (List<Object>) key;
            int i = 0;
            for (Object keyObj : keys) {
                buf.append(i++ == 0 ? "" : ",").append(ValueUtils.toString(keyObj));
            }
            return buf.toString();
        }

        return null;
    }

}
