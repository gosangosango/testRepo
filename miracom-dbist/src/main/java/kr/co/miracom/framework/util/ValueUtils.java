/**
 * Copyright 2011-2013 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package kr.co.miracom.framework.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import kr.co.miracom.framework.exception.BizException;

/**
 * @author Steve M. Jung
 * @since 2011. 6. 2. (version 0.0.1)
 */
public class ValueUtils {
    private static final Logger logger = LoggerFactory.getLogger(ValueUtils.class);

    public static final String DATEPATTERN_XSDDATETIME = "yyyy-MM-dd'T'HH:mm:ss'.'S";
    public static final String DATEPATTERN_XSDSMALLDATETIME = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String DATEPATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss'.'S";
    public static final String DATEPATTERN_SMALLDATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String DATEPATTERN_DATE = "yyyy-MM-dd";
    public static final String DATEPATTERN_DATETIME_SHORT = "yyyyMMddHHmmssS";
    public static final String DATEPATTERN_SMALLDATETIME_SHORT = "yyyyMMddHHmmss";
    public static final String DATEPATTERN_DATE_SHORT = "yyyyMMdd";

    public static final int DELIMITERCASETYPE_LOWER = 0;
    public static final int DELIMITERCASETYPE_UPPER = 1;
    public static final int DELIMITERCASETYPE_UPPERANDLOWER = 2;

    private static final List<String> DATEPATTERN_LIST;
    static {
        DATEPATTERN_LIST = new ArrayList<String>();
        for (Field field : ValueUtils.class.getFields()) {
            int mod = field.getModifiers();
            if (Modifier.isStatic(mod) && Modifier.isFinal(mod)) {
                String name = field.getName();
                if (name.startsWith("DATEPATTERN_") && String.class.equals(field.getType())) {
                    try {
                        DATEPATTERN_LIST.add((String) field.get(null));
                    } catch (IllegalArgumentException e) {
                        logger.warn(e.getMessage(), e);
                    } catch (IllegalAccessException e) {
                        logger.warn(e.getMessage(), e);
                    }
                }
            }
        }
    }

    /**
     * input 객체에 필드 값이 비어있지 않은지를 확인합니다.<br>
     * 비어있을 시에는 에러가 발생합니다.
     * @param input 확인할 대상 객체
     * @param fieldNames 필드 명
     * @throws Exception
     */
    public static void checkNotEmpty(Object input, String... fieldNames) throws Exception {
        checkValue(input, fieldNames, new CheckClosure() {
            @Override
            public void execute(String fieldName, Object value) throws Exception {
                if (ValueUtils.isEmpty(value)) {
                    throw new BizException("MIC-00002", "값이 빈 필드가 있습니다.", new Property(fieldName, ""));
                }
            }
        });
    }

    /**
     * input 객체에 필드 값이 0보다 작지 않은지 확인합니다.<br>
     * 0보다 작을 시에는 에러가 발생합니다.
     * @param input
     * @param fieldNames
     * @throws Exception
     */
    public static void checkNotMinus(Object input, String... fieldNames) throws Exception {
        checkValue(input, fieldNames, new CheckClosure() {
            @Override
            public void execute(String fieldName, Object value) throws Exception {
                double v = ValueUtils.toDouble(value, 0D);
                if (v < 0D) {
                    throw new BizException("XXXXXX", "값이 0보다 작습니다.", new Property(fieldName, v));
                }
            }
        });
    }

    /**
     * input 객체에 필드 값이 0보다 큰지를 확인합니다.<br>
     * 0보다 작을 시에는 에러가 발생합니다.
     * @param input
     * @param fieldNames
     * @throws Exception
     */
    public static void checkPositive(Object input, String... fieldNames) throws Exception {
        checkValue(input, fieldNames, new CheckClosure() {
            @Override
            public void execute(String fieldName, Object value) throws Exception {
                double v = ValueUtils.toDouble(value, 0D);
                if (v <= 0D) {
                    throw new BizException("XXXXXX", "값이 0과 같거나 작습니다.", new Property(fieldName, v));
                }
            }
        });
    }

    private static void checkValue(Object input, String[] fieldNames, CheckClosure closure) throws Exception {
        if (isEmpty(fieldNames)) {
            return;
        }

        List<String> fieldNameList = new ArrayList<String>();
        for (String fieldName : fieldNames) {
            if (fieldName.contains(",")) {
                for (String fName : StringUtils.tokenizeToStringArray(fieldName, ","))
                    fieldNameList.add(fName);
                continue;
            }
            fieldNameList.add(fieldName);
        }

        Map<String, Field> fields = ReflectionUtils.getFieldByNameMap(input, true);

        for (String fieldName : fieldNameList) {
            Field field = fields.get(fieldName);
            if (field == null) {
                throw new IllegalArgumentException("Undeclared Field: " + input.getClass().getName() + "." + fieldName);
            }
            Object value = field.get(input);
            closure.execute(fieldName, value);
        }
    }

    private interface CheckClosure {
        public void execute(String fieldName, Object value) throws Exception;
    }

    public static void assertNotNull(String name, Object value) {
        if (value == null)
            throw new IllegalArgumentException(name + " is null.");
    }

    public static void assertNotEmpty(String name, Object value) {
        if (isEmpty(value))
            throw new IllegalArgumentException(name + " is empty.");
    }

    /**
     * 값이 비어있는지 여부를 반환합니다.
     * @param value 확인할 값
     * @return
     */
    public static boolean isEmpty(Object value) {
        if (value == null) {
            return true;
        } else if (value instanceof String || value instanceof StringBuffer || value instanceof Character) {
            return value.toString().trim().length() == 0;
        } else if (value instanceof Collection) {
            return ((Collection<?>) value).isEmpty();
        } else if (value instanceof Object[]) {
            return ((Object[]) value).length == 0;
        } else if (value instanceof Map) {
            return ((Map<?, ?>) value).isEmpty();
            // } else if (value instanceof Integer) {
            // return ((Integer) value) == 0;
            // } else if (value instanceof Long) {
            // return ((Long) value) == 0L;
            // } else if (value instanceof Double) {
            // return ((Double) value) == 0D;
        }
        return false;
    }

    public static void set(Object data, String fieldName, Object value) throws Exception {
        Field field = ReflectionUtils.getField(data, fieldName, true);
        if (value != null) {
            Class<?> type = field.getType();
            if (ValueUtils.isPrimitive(type)) {
                value = ValueUtils.toRequiredType(value, type);
            } else if (!type.isAssignableFrom(value.getClass())) {
                value = ValueUtils.populate(value, field.getType().newInstance());
            }
        }
        field.set(data, value);
    }

    public static String getString(Object data, String fieldName) throws Exception {
        Field field = ReflectionUtils.getField(data, fieldName, true);
        Object obj = field.get(data);
        String value = toString(obj);
        return value;
    }

    public static double getDouble(Object data, String fieldName) throws Exception {
        Field field = ReflectionUtils.getField(data, fieldName, true);
        Object obj = field.get(data);
        double value = toDouble(obj, 0D);
        return value;
    }

    /**
     * 현재의 시간을 반환합니다. (새로 값을 구해서 반환)
     * @return
     * @throws Exception
     */
    public static ZonedDateTime newZonedDateTime() throws Exception {
        Date date = newDate();
        ZonedDateTime zonedDateTime = toZonedDateTime(date);
        return zonedDateTime;
    }

    /**
     * 현재의 시간을 반환합니다. (같은 쓰레드에서 이미 조회한 시간 값이 있으면 그 값을 이용함)
     * @return
     */
    public static ZonedDateTime getZonedDateTime() {
        String name = "ValueUtils.zonedDateTime";
        ZonedDateTime value = (ZonedDateTime) ThreadUtils.getProp(name);
        if (value == null) {
            value = ZonedDateTime.now(AuthUtils.getZoneId());
            ThreadUtils.setProp(name, value);
        }
        return value;
    }

    public static ZonedDateTime toZonedDateTime(Date date) throws Exception {
        Instant inst = Instant.ofEpochMilli(date.getTime());
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(inst, AuthUtils.getZoneId());
        return zonedDateTime;
    }

    /**
     * 현재의 시간을 반환합니다. (새로 값을 구해서 반환)
     * @return
     * @throws Exception
     */
    private synchronized static Date newDate() throws Exception {
        Date dbDate = (Date) ThreadUtils.getProp("ValueUtils.dbDate");
        Date osDate = null;
        try {
            if (dbDate != null) {
                Date oldDate = (Date) ThreadUtils.getProp("ValueUtils.osDate");
                osDate = new Date();
                dbDate = new Date(dbDate.getTime() + (osDate.getTime() - oldDate.getTime()));
                return dbDate;
            }

            dbDate = DbUtils.select(DbUtils.toSqlPath(ValueUtils.class, "select_timestamp"), null, Date.class);
            dbDate = new Date(dbDate.getTime());
            osDate = new Date();
            return dbDate;

        } finally {
            if (osDate != null && dbDate != null) {
                ThreadUtils.setProp("ValueUtils.osDate", osDate);
                ThreadUtils.setProp("ValueUtils.dbDate", dbDate);
            }
        }
    }

    /**
     * 현재의 시간을 반환합니다. (같은 쓰레드에서 이미 조회한 시간 값이 있으면 그 값을 이용함)
     * @return
     * @throws Exception
     */
    public static Date getDate() throws Exception {
        Date date = (Date) ThreadUtils.getProp("ValueUtils.dbDate");
        return date == null ? newDate() : date;
    }

    public static String getDateStr() throws Exception {
        Date date = getDate();
        String str = toDateStr(date, "yyyyMMdd");
        return str;
    }

    public static <T> List<T> toList(@SuppressWarnings("unchecked") T... value) {
        if (isEmpty(value))
            return new ArrayList<T>(0);
        List<T> list = new ArrayList<T>(value.length);
        for (T v : value)
            list.add(v);
        return list;
    }

    public static <T> Set<T> toSet(@SuppressWarnings("unchecked") T... value) {
        if (isEmpty(value))
            return new HashSet<T>(0);
        Set<T> set = new HashSet<T>(value.length);
        for (T v : value)
            set.add(v);
        return set;
    }

    public static Map<String, String> toMap(String... keyValues) {
        Map<String, String> map = new HashMap<String, String>();
        if (isEmpty(keyValues))
            return map;
        for (String keyValue : keyValues) {
            if (isEmpty(keyValue))
                continue;
            if (keyValue.contains(":")) {
                int index = keyValue.indexOf(":");
                String key = keyValue.substring(0, index).trim();
                String value = keyValue.substring(index + 1).trim();
                map.put(key, value);
            } else {
                map.put(keyValue, null);
            }
        }
        return map;
    }

    public static String toCommaDelimitedString(String... array) {
        return StringUtils.arrayToCommaDelimitedString(array);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static final List PRIMITIVETYPE_LIST = toList(String.class, char.class, Character.class, byte.class,
                    Byte.class, byte[].class, Byte[].class, short.class, Short.class, int.class, Integer.class,
                    long.class, Long.class, float.class, Float.class, double.class, Double.class, BigDecimal.class,
                    boolean.class, Boolean.class, Date.class, LocalDateTime.class, ZonedDateTime.class);

    public static boolean isPrimitive(Object obj) {
        assertNotNull("obj", obj);
        Class<?> type = obj instanceof Class ? (Class<?>) obj : obj.getClass();
        return type.isPrimitive() || PRIMITIVETYPE_LIST.contains(type);
    }

    @SuppressWarnings("unchecked")
    public static <T> T toRequiredType(Object value, Class<T> requiredType) {
        assertNotNull("requiredType", requiredType);
        if (value == null)
            return null;
        if (requiredType.isAssignableFrom(value.getClass()))
            return (T) value;
        if (requiredType == String.class)
            return (T) toString(value);
        if (requiredType.equals(Character.class) || requiredType.equals(char.class))
            return (T) toCharacter(value);
        if (requiredType.equals(Date.class))
            return (T) toDate(value);
        if (requiredType.equals(Boolean.class) || requiredType.equals(boolean.class))
            return (T) (Boolean) toBoolean(value);
        if (requiredType.equals(BigDecimal.class))
            return (T) toBigDecimal(value);
        if (requiredType.equals(Integer.class) || requiredType.equals(int.class))
            return (T) toInteger(value);
        if (requiredType.equals(Long.class) || requiredType.equals(long.class))
            return (T) toLong(value);
        if (requiredType.equals(Double.class) || requiredType.equals(double.class))
            return (T) toDouble(value);
        if (requiredType.equals(Short.class) || requiredType.equals(short.class))
            return (T) toShort(value);
        if (requiredType.equals(Float.class) || requiredType.equals(float.class))
            return (T) toFloat(value);
        if (requiredType.equals(Byte.class) || requiredType.equals(byte.class))
            return (T) toByte(value);
        if (requiredType.equals(Byte[].class) || requiredType.equals(byte[].class))
            return (T) toBytes(value);
        throw new IllegalArgumentException("Unsupported requiredType: " + requiredType);
    }

    public static String toString(Object value) {
        return toString(value, null);
    }

    public static String toString(Object value, String defaultValue) {
        if (value == null)
            return defaultValue;
        if (!isEmpty(value))
            return toStringFromObject(value);
        if (defaultValue == null)
            return value == null ? null : value.toString();
        return isEmpty(defaultValue) ? toStringFromObject(value) : defaultValue;
    }

    private static String toStringFromObject(Object value) {
        if (value instanceof Date) {
            return toDateStr((Date) value, DATEPATTERN_XSDDATETIME);
        }
        if (value instanceof Double) {
            return BigDecimal.valueOf((Double) value).toString();
        }
        // if (value instanceof Float)
        // return new BigDecimal((Float) value).toString();
        return value.toString();
    }

    public static String toNull(String value) {
        return isEmpty(value) ? null : value;
    }

    public static String toNotNull(String value) {
        return isEmpty(value) ? "" : value;
    }

    public static boolean toBoolean(Object value) {
        return toBoolean(value, false);
    }

    private final static Set<String> TRUE_STRING_SET = toSet("true", "TRUE", "t", "T", "yes", "YES", "Yes", "y", "Y",
                    "on", "1");

    public static boolean toBoolean(Object value, boolean defaultValue) {
        if (value == null)
            return defaultValue;
        if (value instanceof Boolean)
            return (Boolean) value;
        String str = value.toString().trim();
        if (NumberUtils.isCreatable(str))
            return NumberUtils.toInt(str) > 0;
        return TRUE_STRING_SET.contains(str);
    }

    public static String toDateStr(Object value, String pattern) {
        assertNotEmpty("pattern", pattern);
        if (value instanceof ZonedDateTime) {
            value = toDate(value);
        }
        return value == null ? null : new SimpleDateFormat(pattern).format(value);
    }

    public static Date toDate(Object value) {
        return toDate(value, (Date) null);
    }

    public static Date toDate(Object value, Date defaultValue) {
        if (isEmpty(value)) {
            return defaultValue;
        }
        if (value instanceof Date) {
            return (Date) value;
        }
        if (value instanceof ZonedDateTime) {
            ZonedDateTime dateTime = (ZonedDateTime) value;
            Date date = new Date(dateTime.toInstant().toEpochMilli());
            return date;
        }
        for (String pattern : DATEPATTERN_LIST) {
            try {
                return new SimpleDateFormat(pattern).parse(value.toString());
            } catch (Exception e) {
            }
        }
        throw new IllegalArgumentException("Couldn't find date pattern for value: " + value);
    }

    public static Date toDate(String value, String pattern) {
        if (isEmpty(value))
            return null;
        try {
            return new SimpleDateFormat(pattern).parse(value);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Couldn't parse value: " + value + " for data pattern: " + pattern, e);
        }
    }

    public static Character toCharacter(Object value) {
        return toCharacter(value, null);
    }

    public static Character toCharacter(Object value, Character defaultValue) {
        if (value == null)
            return defaultValue;
        if (value instanceof Character)
            return (Character) value;
        String str = toString(value);
        if (str.length() == 0)
            return defaultValue;
        return str.charAt(0);
    }

    public static Short toShort(Object value) {
        return toShort(value, null);
    }

    public static Short toShort(Object value, Short defaultValue) {
        if (value == null)
            return defaultValue;
        if (value instanceof Short)
            return (Short) value;
        String str = value.toString();
        if (NumberUtils.isCreatable(str)) {
            int index = str.indexOf('.');
            if (index != -1)
                str = str.substring(0, index);
            return NumberUtils.toShort(str);
        }
        return defaultValue;
    }

    public static Integer toInteger(Object value) {
        return toInteger(value, null);
    }

    public static Integer toInteger(Object value, Integer defaultValue) {
        if (value == null)
            return defaultValue;
        if (value instanceof Integer)
            return (Integer) value;
        if (value instanceof Boolean)
            return (Boolean) value ? 1 : 0;
        String str = value.toString();
        if (NumberUtils.isCreatable(str)) {
            int index = str.indexOf('.');
            if (index != -1)
                str = str.substring(0, index);
            return NumberUtils.toInt(str);
        }
        return defaultValue;
    }

    public static Long toLong(Object value) {
        return toLong(value, null);
    }

    public static Long toLong(Object value, Long defaultValue) {
        if (value == null)
            return defaultValue;
        if (value instanceof Long)
            return (Long) value;
        if (value instanceof Date)
            return ((Date) value).getTime();
        String str = value.toString();
        if (NumberUtils.isCreatable(str)) {
            int index = str.indexOf('.');
            if (index != -1)
                str = str.substring(0, index);
            return NumberUtils.toLong(str);
        }
        return defaultValue;
    }

    public static Float toFloat(Object value) {
        return toFloat(value, null);
    }

    public static Float toFloat(Object value, Float defaultValue) {
        if (value == null)
            return defaultValue;
        if (value instanceof Float)
            return (Float) value;
        String str = value.toString();
        if (NumberUtils.isCreatable(str))
            return NumberUtils.toFloat(str);
        return defaultValue;
    }

    public static Double toDouble(Object value) {
        return toDouble(value, null);
    }

    public static Double toDouble(Object value, Double defaultValue) {
        if (value == null)
            return defaultValue;
        if (value instanceof Double)
            return (Double) value;
        String str = value.toString();
        if (NumberUtils.isCreatable(str))
            return NumberUtils.toDouble(str);
        return defaultValue;
    }

    public static BigDecimal toBigDecimal(Object value) {
        return toBigDecimal(value, null);
    }

    public static BigDecimal toBigDecimal(Object value, BigDecimal defaultValue) {
        if (value == null)
            return defaultValue;
        if (value instanceof BigDecimal)
            return (BigDecimal) value;
        String str = value.toString();
        return NumberUtils.isCreatable(str) ? new BigDecimal(str) : defaultValue;
    }

    public static Byte toByte(Object value) {
        return toByte(value, null);
    }

    public static Byte toByte(Object value, Byte defaultValue) {
        if (value == null)
            return defaultValue;
        if (value instanceof Byte)
            return (Byte) value;
        String str = value.toString();
        return NumberUtils.toByte(str, defaultValue);
    }

    public static byte[] toBytes(Object value) {
        return toBytes(value, null);
    }

    public static byte[] toBytes(Object value, byte[] defaultValue) {
        if (value == null)
            return defaultValue;
        if (value instanceof byte[])
            return (byte[]) value;
        String str = value.toString();
        return str.getBytes();
    }

    /**
     * from 객체에서 to 객체로 같은 이름의 필드 값을 복사합니다.
     * @param from 값을 가져올 객체
     * @param to 값을 담을 객체
     * @return to 객체
     */
    public static <T> T populate(Object from, T to) {
        return _populate(from, to);
    }

    /**
     * from 객체에서 to 객체로 같은 이름의 필드 값을 복사합니다.
     * @param from 값을 가져올 객체
     * @param to 값을 담을 객체
     * @param fieldNames
     * @return to 객체
     */
    public static <T> T populate(Object from, T to, String... fieldNames) {
        return _populate(from, to, fieldNames);
    }

    @SuppressWarnings("unchecked")
    private static <T> T _populate(Object from, T to, String... fieldNames) {
        assertNotNull("object", from);
        assertNotNull("to", to);

        boolean byFieldName = fieldNames != null && fieldNames.length != 0;
        try {
            // From and to class types are instances of Map
            if (from instanceof Map && to instanceof Map) {
                Map<?, ?> fromMap = (Map<?, ?>) from;
                Map<Object, Object> toMap = (Map<Object, Object>) to;
                if (byFieldName) {
                    for (String fieldName : fieldNames)
                        toMap.put(fieldName, fromMap.get(fieldName));
                } else {
                    toMap.putAll(fromMap);
                }
            }
            // From and to class types are similar or same each others
            else if (to.getClass().isAssignableFrom(from.getClass())) {
                if (byFieldName) {
                    Map<String, Field> toFieldByNameMap = ReflectionUtils.getFieldByNameMap(to, true);
                    for (String fieldName : fieldNames) {
                        if (!toFieldByNameMap.containsKey(fieldName))
                            throw new IllegalArgumentException(
                                            "Couldn't find a field: " + to.getClass().getName() + "." + fieldName);
                        Field field = toFieldByNameMap.get(fieldName);
                        field.set(to, field.get(from));
                    }
                } else {
                    for (Field field : ReflectionUtils.getFieldList(to, true))
                        field.set(to, field.get(from));
                }
            }
            // From object is an instance of HttpServletRequests
            else if (from instanceof HttpServletRequest) {
                HttpServletRequest request = (HttpServletRequest) from;
                Map<String, String[]> paramMap = request.getParameterMap();
                if (byFieldName) {
                    Map<String, Field> toFieldByNameMap = ReflectionUtils.getFieldByNameMap(to, true);
                    for (String fieldName : fieldNames) {
                        if (!toFieldByNameMap.containsKey(fieldName))
                            throw new IllegalArgumentException(
                                            "Couldn't find a field: " + to.getClass().getName() + "." + fieldName);
                        populate(paramMap, to, fieldName, toFieldByNameMap);
                    }
                } else {
                    Map<String, Field> fieldByNameMap = ReflectionUtils.getFieldByNameMap(to, true);
                    for (String fieldName : paramMap.keySet()) {
                        if (!fieldByNameMap.containsKey(fieldName))
                            continue;
                        populate(paramMap, to, fieldName, fieldByNameMap);
                    }
                }
            }
            // From object is an instance of Map
            else if (from instanceof Map) {
                Map<String, Object> fromMap = (Map<String, Object>) from;
                if (byFieldName) {
                    Map<String, Field> toFieldByNameMap = ReflectionUtils.getFieldByNameMap(to, true);
                    for (String fieldName : fieldNames) {
                        if (!toFieldByNameMap.containsKey(fieldName))
                            throw new IllegalArgumentException(
                                            "Couldn't find a field: " + to.getClass().getName() + "." + fieldName);
                        Field field = toFieldByNameMap.get(fieldName);
                        Object fromValue = fromMap.get(field.getName());
                        field.set(to, toRequiredType(fromValue, field.getType()));
                    }
                } else {
                    for (Field field : ReflectionUtils.getFieldList(to, true)) {
                        if (!fromMap.containsKey(field.getName()))
                            continue;
                        Object fromValue = fromMap.get(field.getName());
                        field.set(to, toRequiredType(fromValue, field.getType()));
                    }
                }
            }
            // To object is an instance of Map
            else if (to instanceof Map) {
                Map<String, Field> fromFieldByNameMap = ReflectionUtils.getFieldByNameMap(from, true);
                Map<String, Object> toMap = (Map<String, Object>) to;
                if (byFieldName) {
                    for (String fieldName : fieldNames) {
                        if (!fromFieldByNameMap.containsKey(fieldName))
                            throw new IllegalArgumentException(
                                            "Couldn't find a field: " + from.getClass().getName() + "." + fieldName);
                        Field field = fromFieldByNameMap.get(fieldName);
                        toMap.put(fieldName, field.get(from));
                    }
                } else {
                    for (String fieldName : fromFieldByNameMap.keySet()) {
                        Field field = fromFieldByNameMap.get(fieldName);
                        toMap.put(fieldName, field.get(from));
                    }
                }
            }
            // Else
            else {
                Map<String, Field> fromFieldByNameMap = ReflectionUtils.getFieldByNameMap(from, true);
                if (byFieldName) {
                    Map<String, Field> toFieldByNameMap = ReflectionUtils.getFieldByNameMap(to, true);
                    for (String fieldName : fieldNames) {
                        if (!fromFieldByNameMap.containsKey(fieldName))
                            throw new IllegalArgumentException(
                                            "Couldn't find a field: " + from.getClass().getName() + "." + fieldName);
                        if (!toFieldByNameMap.containsKey(fieldName))
                            throw new IllegalArgumentException(
                                            "Couldn't find a field: " + to.getClass().getName() + "." + fieldName);
                        Object fromValue = fromFieldByNameMap.get(fieldName).get(from);
                        Field toField = toFieldByNameMap.get(fieldName);
                        toField.set(to, toRequiredType(fromValue, toField.getType()));
                    }
                } else {
                    for (Field toField : ReflectionUtils.getFieldList(to, true)) {
                        if (!fromFieldByNameMap.containsKey(toField.getName()))
                            continue;
                        Object fromValue = fromFieldByNameMap.get(toField.getName()).get(from);
                        toField.set(to, toRequiredType(fromValue, toField.getType()));
                    }
                }
            }
        } catch (RuntimeException e) {
            throw e;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return to;
    }

    private static void populate(Map<String, String[]> fromParamMap, Object to, String fieldName,
                    Map<String, Field> toFieldByNameMap) throws IllegalArgumentException, IllegalAccessException {
        Field field = toFieldByNameMap.get(fieldName);
        String[] values = fromParamMap.get(fieldName);
        String value = isEmpty(values) ? null : values[0];
        value = value == null || value.length() == 0 ? null : value;
        // value length is more than 1
        if (values.length > 1) {
            if (field.getType().equals(String[].class)) {
                toFieldByNameMap.get(fieldName).set(to, values);
                return;
            }
            throw new RuntimeException("Not supported style, yet. fieldName: " + fieldName);
        }
        toFieldByNameMap.get(fieldName).set(to, toRequiredType(value, field.getType()));
    }

    public static String populate(String value, Map<String, ?> paramMap) {
        if (isEmpty(paramMap))
            return value;
        for (String key : paramMap.keySet()) {
            String propKey = "${" + key + "}";
            if (!value.contains(propKey))
                continue;
            value = StringUtils.replace(value, propKey, paramMap.get(key) + "");
        }
        return value;
    }

    /**
     * Convert camelCaseValue to another delmiterCaseValue such as underscores or hyphens
     * 
     * <pre>
     * toDelimiterCase123 -> to_delimiter_case123
     * </pre>
     * 
     * @param camelCaseValue
     * @param delimiter
     * @return delimited value
     */
    public static String toDelimited(String camelCaseValue, Character delimiter) {
        return toDelimited(camelCaseValue, delimiter, false);
    }

    /**
     * Convert camelsCaseValue to another delmiterCaseValue such as underscores or hyphens
     * 
     * <pre>
     * <b>numberAsUpperCase == false</b>:
     * toDelimiterCase123 -> to_delimiter_case123 (default)
     * 
     * <b>numberAsUpperCase == true</b>:  
     * toDelimiterCase123 -> to_delimiter_case_123
     * </pre>
     * 
     * @param camelCaseValue
     * @param delimiter
     * @param numberAsUpperCase
     * @return delimited value
     */
    public static String toDelimited(String camelCaseValue, Character delimiter, boolean numberAsUpperCase) {
        if (isEmpty(camelCaseValue))
            return camelCaseValue;
        if (delimiter == null)
            return camelCaseValue.toLowerCase();
        StringBuffer buf = new StringBuffer();
        boolean first = true;
        boolean wasNumber = false;
        for (char c : camelCaseValue.toCharArray()) {
            boolean isNumber = false;

            // UpperCase
            if (c > 64 && c < 91) {
                if (first)
                    first = false;
                else
                    buf.append(delimiter);
                buf.append((char) (c + 32));
            }
            // LowerCase
            else if (c > 96 && c < 123) {
                if (first)
                    first = false;
                buf.append(c);
            }
            // Number and number as UpperCase
            else if (c > 47 && c < 58 && numberAsUpperCase) {
                if (first)
                    first = false;
                else if (!wasNumber)
                    buf.append(delimiter);
                buf.append(c);
                isNumber = true;
            }
            // The others
            else {
                buf.append(c);
            }

            wasNumber = isNumber;
        }
        return buf.toString();
    }

    /**
     * Convert delimiterCaseValue(such as underscores or hyphens) to camelCaseValue
     * 
     * <pre>
     * <b>Underscores:</b> (all lower, all caps or both character cases)
     * to_camel_case123  -> toCamelCase123
     * to_camel_case_123 -> toCamelCase123
     * TO_CAMEL_CASE123  -> toCamelCase123
     * TO_CAMEL_CASE_123 -> toCamelCase123
     * To_Camel_Case123  -> toCamelCase123
     * To_Camel_Case_123 -> toCamelCase123
     * 
     * <b>Hyphens:</b>
     * to-camel-case123  -> toCamelCase123
     * to-camel-case-123 -> toCamelCase123
     * TO-CAMEL-CASE123  -> toCamelCase123
     * TO-CAMEL-CASE-123 -> toCamelCase123
     * To-Camel-Case123  -> toCamelCase123
     * To-Camel-Case-123 -> toCamelCase123
     * </pre>
     * 
     * @param delimiterCaseValue
     * @param delimiter
     * @return camel case value
     */
    public static String toCamelCase(String delimiterCaseValue, Character delimiter) {
        return toCamelCase(delimiterCaseValue, delimiter, false);
    }

    public static String toCamelCase(String delimiterCaseValue, Character delimiter, boolean upper) {
        if (isEmpty(delimiterCaseValue) || delimiter == null)
            return delimiterCaseValue;
        StringBuffer buf = new StringBuffer();
        boolean first = true;
        boolean wasDelimiter = false;
        for (char c : delimiterCaseValue.toCharArray()) {
            // Delimiter
            if (c == delimiter) {
                if (first || wasDelimiter)
                    buf.append(c);
                else
                    wasDelimiter = true;
            }
            // UpperCase
            else if (c > 64 && c < 91) {
                if (wasDelimiter) {
                    buf.append(c);
                    wasDelimiter = false;
                } else if (first) {
                    buf.append(upper ? c : (char) (c + 32));
                    first = false;
                } else {
                    buf.append((char) (c + 32));
                }
            }
            // LowerCase
            else if (c > 96 && c < 123) {
                if (wasDelimiter) {
                    buf.append((char) (c - 32));
                    wasDelimiter = false;
                } else if (first) {
                    buf.append(upper ? (char) (c - 32) : c);
                    first = false;
                } else {
                    buf.append(c);
                }
            }
            // The others
            else {
                if (wasDelimiter)
                    wasDelimiter = false;
                buf.append(c);
            }
        }
        return buf.toString();
    }

    public static String toLabel(String str) {
        if (str == null)
            return str;
        if (!str.contains("_")) {
            str = ValueUtils.toDelimited(str, '_');
        }
        StringBuffer buf = new StringBuffer();
        int i = 0;
        for (String word : StringUtils.tokenizeToStringArray(str, "_"))
            buf.append(i++ == 0 ? "" : " ").append(StringUtils.capitalize(word));
        return buf.toString();
    }

    public static Throwable unwrap(Throwable t) {
        while (t instanceof InvocationTargetException)
            t = ((InvocationTargetException) t).getTargetException();
        return t;
    }

    public static void adjust(Object data) throws Exception {
        _adjust(data);
    }

    public static void adjust(Object data, String... fieldNames) throws Exception {
        _adjust(data, fieldNames);
    }

    private static void _adjust(Object data, String... fieldNames) throws Exception {
        if (data == null) {
            return;
        }

        Set<String> set = ValueUtils.isEmpty(fieldNames) ? null : ValueUtils.toSet(fieldNames);

        for (Field field : ReflectionUtils.getFieldList(data, true)) {
            if (set != null && !set.contains(field.getName())) {
                continue;
            }
            if (String.class.equals(field.getType())) {
                String str = (String) field.get(data);
                if (str == null) {
                    continue;
                }
                if (str.trim().length() == 0) {
                    field.set(data, null);
                }
            }
        }
    }

    public static <T> T clone(T data) throws Exception {
        @SuppressWarnings("unchecked")
        T _data = (T) ValueUtils.populate(data, data.getClass().newInstance());
        return _data;
    }
}
