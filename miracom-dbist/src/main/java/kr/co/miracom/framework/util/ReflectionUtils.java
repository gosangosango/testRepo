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

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

/**
 * @author Steve M. Jung
 * @since 2011. 6. 9. (version 0.0.1)
 */
public class ReflectionUtils {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionUtils.class);

    @SuppressWarnings("unused")
    private static final String NULL = null;
    public static final Field NULL_FIELD;
    static {
        Field nullField = null;
        try {
            nullField = ReflectionUtils.class.getDeclaredField("NULL");
        } catch (SecurityException e) {
            nullField = null;
            logger.warn(e.getMessage(), e);
        } catch (NoSuchFieldException e) {
            nullField = null;
            logger.warn(e.getMessage(), e);
        } finally {
            NULL_FIELD = nullField;
        }
    }

    public static void clear() {
        logger.info("Clearing ReflectionUtils...");
        fieldListCache.clear();
        fieldByNameCache.clear();
    }

    private static final ResourcePatternResolver RESOURCE_PATTERN_RESOLVER = new PathMatchingResourcePatternResolver();
    private static final MetadataReaderFactory METADATA_READER_FACTORY = new SimpleMetadataReaderFactory();
    private static final List<String> ORDERBY_LIST = ValueUtils.toList("name", "simpleName");

    public static List<Class<?>> getClassList(String locationPattern, String orderBy) {
        ValueUtils.assertNotEmpty("locationPattern", locationPattern);
        final String _orderBy = ValueUtils.isEmpty(orderBy) ? "name" : orderBy;
        if (!ORDERBY_LIST.contains(_orderBy))
            throw new IllegalArgumentException("Unsupported orderBy: " + _orderBy);

        locationPattern = locationPattern.trim();
        if (!locationPattern.endsWith(".class"))
            locationPattern = locationPattern + ".class";

        Resource[] resources;
        try {
            resources = RESOURCE_PATTERN_RESOLVER.getResources(locationPattern);
        } catch (IOException e) {
            throw new IllegalArgumentException("Maybe invalid locationPattern: " + locationPattern);
        }

        if (ValueUtils.isEmpty(resources))
            return new ArrayList<Class<?>>(0);

        Map<String, Class<?>> map = new TreeMap<String, Class<?>>();
        for (Resource resource : resources) {
            if (!resource.isReadable() || !resource.getFilename().endsWith(".class"))
                continue;
            MetadataReader metadataReader;
            try {
                metadataReader = METADATA_READER_FACTORY.getMetadataReader(resource);
            } catch (IOException e) {
                logger.warn(e.getMessage());
                continue;
            }
            ClassMetadata classMetadata = metadataReader.getClassMetadata();
            if (classMetadata == null || !classMetadata.isConcrete())
                continue;
            Class<?> clazz;
            try {
                clazz = ClassUtils.forName(classMetadata.getClassName(), null);
            } catch (ClassNotFoundException e) {
                logger.warn(e.getMessage());
                continue;
            } catch (LinkageError e) {
                logger.warn(e.getMessage());
                continue;
            }
            map.put("name".equals(_orderBy) ? clazz.getName() : clazz.getSimpleName(), clazz);
        }
        List<Class<?>> list = new ArrayList<Class<?>>(map.values());

        return list;
    }

    public static List<String> getClassNameList(final String locationPattern, String orderBy) {
        final String _orderBy = ValueUtils.isEmpty(orderBy) ? "name" : orderBy;
        if (!ORDERBY_LIST.contains(_orderBy))
            throw new IllegalArgumentException("Unsupported orderBy: " + _orderBy);

        Resource[] resources;
        try {
            resources = RESOURCE_PATTERN_RESOLVER.getResources(locationPattern);
        } catch (IOException e) {
            throw new IllegalArgumentException("Maybe invalid locationPattern: " + locationPattern);
        }

        if (ValueUtils.isEmpty(resources))
            return new ArrayList<String>(0);

        Map<String, String> map = new TreeMap<String, String>();
        for (Resource resource : resources) {
            if (!resource.isReadable() || !resource.getFilename().endsWith(".class"))
                continue;
            MetadataReader metadataReader;
            try {
                metadataReader = METADATA_READER_FACTORY.getMetadataReader(resource);
            } catch (IOException e) {
                logger.warn(e.getMessage());
                continue;
            }
            ClassMetadata classMetadata = metadataReader.getClassMetadata();
            if (classMetadata == null || !classMetadata.isConcrete())
                continue;
            String className = classMetadata.getClassName();
            if (className.contains("."))
                map.put("name".equals(_orderBy) ? className : className.substring(className.indexOf("." + 1)),
                                className);
            else
                map.put(className, className);
        }
        List<String> list = new ArrayList<String>(map.values());

        return list;
    }

    public static Method getGetter(Object obj, String fieldName, Class<?> requiredType) {
        ValueUtils.assertNotNull("obj", obj);
        ValueUtils.assertNotNull("fieldName", fieldName);
        Class<?> clazz = obj instanceof Class ? (Class<?>) obj : obj.getClass();
        String getterName = toGetterName(fieldName);
        String isName = toIsName(fieldName);
        Method isMethod = null;
        for (Method method : clazz.getMethods()) {
            if (!method.getName().equals(getterName)) {
                if (method.getName().equals(isName) && boolean.class.isAssignableFrom(method.getReturnType())
                                && (requiredType == null || method.getReturnType().isAssignableFrom(requiredType)))
                    isMethod = method;
                continue;
            }
            if (!void.class.equals(method.getReturnType()) && ValueUtils.isEmpty(method.getParameterTypes())
                            && (requiredType == null || method.getReturnType().isAssignableFrom(requiredType)))
                return method;
        }
        return isMethod;
    }

    public static Method getSetter(Object obj, String fieldName, Class<?> requiredType) {
        ValueUtils.assertNotNull("obj", obj);
        ValueUtils.assertNotNull("fieldName", fieldName);
        Class<?> clazz = obj instanceof Class ? (Class<?>) obj : obj.getClass();
        String setterName = toSetterName(fieldName);
        for (Method method : clazz.getMethods()) {
            if (!method.getName().equals(setterName))
                continue;
            if (void.class.equals(method.getReturnType()) && !ValueUtils.isEmpty(method.getParameterTypes())
                            && method.getParameterTypes().length == 1
                            && (requiredType == null || method.getParameterTypes()[0].isAssignableFrom(requiredType)))
                return method;
        }
        return null;
    }

    private static String toIsName(String fieldName) {
        return "is" + toMethodName(fieldName);
    }

    private static String toGetterName(String fieldName) {
        return "get" + toMethodName(fieldName);
    }

    private static String toSetterName(String fieldName) {
        return "set" + toMethodName(fieldName);
    }

    private static String toMethodName(String fieldName) {
        if (fieldName.length() == 1)
            return fieldName.toUpperCase();
        char c = fieldName.charAt(1);
        if (c > 96 && c < 123)
            return StringUtils.capitalize(fieldName);
        return fieldName;
    }

    private static final Map<Class<?>, Map<String, Field>> fieldByClassCache = new ConcurrentHashMap<Class<?>, Map<String, Field>>();
    @SuppressWarnings("unused")
    private String nullString;
    private static Field FIELD_NULL;
    static {
        try {
            FIELD_NULL = ReflectionUtils.class.getDeclaredField("nullString");
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
        }
    }

    public static Field getField(Object obj, String name, boolean checkFound) {
        Field field = getField(obj, name);
        if (checkFound && field == null) {
            throw new IllegalArgumentException("Undeclared Field: " + obj.getClass() + "." + name);
        }
        return field;
    }

    public static Field getField(Object obj, String name) {
        ValueUtils.assertNotNull("obj", obj);
        ValueUtils.assertNotNull("name", name);
        Class<?> clazz = obj instanceof Class ? (Class<?>) obj : obj.getClass();

        Map<String, Field> fieldCache = fieldByClassCache.containsKey(clazz) ? fieldByClassCache.get(clazz)
                        : SyncCtrlUtils.wrap("ReflectionUtils.fieldByClassCache." + clazz.getName(), fieldByClassCache,
                                        clazz, new Closure<Map<String, Field>, RuntimeException>() {
                                            public Map<String, Field> execute() {
                                                return new ConcurrentHashMap<String, Field>();
                                            }
                                        });

        Field field = fieldCache.containsKey(name) ? fieldCache.get(name)
                        : SyncCtrlUtils.wrap("ReflectionUtils.fieldByClassCache." + clazz.getName() + "." + name,
                                        fieldCache, name, new Closure<Field, RuntimeException>() {
                                            public Field execute() {
                                                Field field = _getField(clazz, name);
                                                return field == null ? FIELD_NULL : field;
                                            }
                                        });

        return FIELD_NULL.equals(field) ? null : field;
    }

    private static Field _getField(Object obj, String name) {
        ValueUtils.assertNotNull("obj", obj);
        ValueUtils.assertNotNull("name", name);
        Class<?> clazz = obj instanceof Class ? (Class<?>) obj : obj.getClass();
        if (clazz.equals(Object.class)) {
            return null;
        }
        Field field = null;
        try {
            field = clazz.getDeclaredField(name);
            return field;
        } catch (SecurityException e) {
            return null;
        } catch (NoSuchFieldException e) {
            field = getField(clazz.getSuperclass(), name);
            return field;
        } finally {
            if (field != null && !field.isAccessible())
                field.setAccessible(true);

        }
    }

    private static Map<Class<?>, List<Field>> fieldListCache = new ConcurrentHashMap<Class<?>, List<Field>>();

    public static List<Field> getFieldList(Object obj, boolean cache) {
        ValueUtils.assertNotNull("obj", obj);
        Class<?> clazz = obj instanceof Class ? (Class<?>) obj : obj.getClass();
        if (fieldListCache.containsKey(clazz))
            return fieldListCache.get(clazz);
        if (cache) {
            loadFieldCache(clazz);
            return fieldListCache.get(clazz);
        }

        List<Field> list = new ArrayList<Field>();
        populateFieldInfo(list, null, clazz);
        return list;
    }

    private static Map<Class<?>, Map<String, Field>> fieldByNameCache = new ConcurrentHashMap<Class<?>, Map<String, Field>>();

    public static Map<String, Field> getFieldByNameMap(Object obj, boolean cache) {
        ValueUtils.assertNotNull("obj", obj);
        Class<?> clazz = obj instanceof Class ? (Class<?>) obj : obj.getClass();
        if (fieldByNameCache.containsKey(clazz))
            return fieldByNameCache.get(clazz);

        if (cache) {
            loadFieldCache(clazz);
            return fieldByNameCache.get(clazz);
        }

        Map<String, Field> nameFieldMap = new HashMap<String, Field>();
        populateFieldInfo(null, nameFieldMap, clazz);
        return nameFieldMap;
    }

    private static void loadFieldCache(final Class<?> clazz) {
        SyncCtrlUtils.wrap("ReflectionUtils.fieldList." + clazz.getName(), fieldListCache, clazz,
                        new Closure<List<Field>, RuntimeException>() {
                            public List<Field> execute() {
                                List<Field> fieldList = new ArrayList<Field>();
                                Map<String, Field> nameFieldMap = new HashMap<String, Field>();
                                populateFieldInfo(fieldList, nameFieldMap, clazz);
                                fieldByNameCache.put(clazz, nameFieldMap);
                                return fieldList;
                            }
                        });
    }

    private static void populateFieldInfo(List<Field> fieldList, Map<String, Field> nameFieldMap, Class<?> clazz) {
        if (clazz == null || clazz.equals(Object.class))
            return;
        populateFieldInfo(fieldList, nameFieldMap, clazz.getSuperclass());
        for (Field field : clazz.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers()))
                continue;
            if (!field.isAccessible())
                field.setAccessible(true);
            if (fieldList != null)
                fieldList.add(field);
            if (nameFieldMap != null)
                nameFieldMap.put(field.getName(), field);
        }
    }

    public static List<Type> getActualTypeList(Type genericType) {
        ValueUtils.assertNotNull("genericType", genericType);
        if (genericType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericType;
            return ValueUtils.toList(parameterizedType.getActualTypeArguments());
        }
        throw new IllegalArgumentException("Couldn't extract actual types of genericType: " + genericType);
    }

    /**
     * clazz 의 패키지 경로를 반환합니다.<br>
     * 경로의 끝은 '/' 문자로 끝납니다.<br>
     * @param clazz 패키지 경로를 얻을 대상 클래스
     * @return clazz 의 패키지 경로
     */
    public static String getPackagePath(Class<?> clazz) {
        return StringUtils.replace(clazz.getPackage().getName(), ".", "/") + "/";
    }

}
