package kr.co.miracom.framework.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.util.StringUtils;

import kr.co.miracom.dbist.annotation.DbistRelation;
import kr.co.miracom.dbist.dml.Dml;
import kr.co.miracom.dbist.dml.Filter;
import kr.co.miracom.dbist.dml.Filters;
import kr.co.miracom.dbist.dml.Page;
import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.dbist.metadata.Column;
import kr.co.miracom.dbist.metadata.Table;
import kr.co.miracom.framework.annotation.DbData;
import kr.co.miracom.framework.annotation.ResRelation;
import kr.co.miracom.framework.exception.BizException;
import kr.co.miracom.framework.service.GetListIn;
import lombok.Data;

public class DbUtils {
	private static Dml dml;

	private static Dml getDml() {
		if (dml == null) {
			dml = BeanUtils.getContext().getBean("dml", Dml.class);
		}
		return dml;
	}

	public static String[] getPkFieldNames(Class<?> clazz) {
		return getDml().getTable(clazz).getPkFieldNames();
	}

	private static Object adjustPkCondition(Class<?> clazz, Object pkCondition) throws Exception {
		String[] pkFieldNames = getPkFieldNames(clazz);
		int len = pkFieldNames.length;
		if (len <= 1) {
			return pkCondition;
		}

		String[] pks;
		if (pkCondition instanceof String) {
			pks = StringUtils.tokenizeToStringArray((String) pkCondition, ",");
		} else if (pkCondition instanceof String[]) {
			pks = (String[]) pkCondition;
		} else if (!(pkCondition instanceof Query) && !ValueUtils.isPrimitive(pkCondition)) {
			List<String> pkList = new ArrayList<>();
			for (String pkFieldName : pkFieldNames) {
				if ("factoryCode".equals(pkFieldName)) {
					pkList.add(AuthUtils.getFactoryCode());
					continue;
				}
				Field field = ReflectionUtils.getField(pkCondition, pkFieldName);
				if (field == null) {
					break;
				}
				String value = ValueUtils.toString(field.get(pkCondition));
				pkList.add(value);
			}
			if (pkList.isEmpty()) {
				return pkCondition;
			}
			return pkList.toArray(new String[pkList.size()]);
		} else {
			return pkCondition;
		}

		if (len <= pks.length || !"factoryCode".equals(pkFieldNames[0])) {
			return pks;
		}

		String[] _pks = new String[len];
		_pks[0] = AuthUtils.getFactoryCode();
		for (int i = 1; i < len; i++) {
			_pks[i] = pks[i - 1];
		}

		return _pks;
	}

	/**
	 * GetList 서비스의 input 값으로 부터 Query 객체를 생성합니다.<br>
	 * select, unselect, pageNumber, pageSize 등이 한번에 반영됩니다.
	 * 
	 * @param input
	 * @return
	 */
	public static Query newQuery(GetListIn input) {
		if (input.getPageSize() <= 0) {
			// TODO 설정 값으로 변경 defaultPageSize
			input.setPageSize(100);
		}
		Query query = new Query(input.getPageNumber(), input.getPageSize());
		if (!ValueUtils.isEmpty(input.getSelect())) {
			query.addSelect(input.getSelect());
		} else if (!ValueUtils.isEmpty(input.getUnselect())) {
			query.addUnselect(input.getUnselect());
		}
		return query;
	}

	/**
	 * clazz DB 테이블에 pkCondition 으로 조회했을 때에 데이터가 있는지 확인합니다.<br>
	 * 데이터가 없을 시에 에러가 발생합니다.
	 * 
	 * @param clazz
	 * @param pkCondition
	 * @throws Exception
	 */
	public static void checkFound(Class<?> clazz, Object pkCondition) throws Exception {
		if (ValueUtils.isEmpty(pkCondition)) {
			return;
		}
		String[] pks = DbUtils.getPkFieldNames(clazz);
		_select(clazz, pkCondition, new SelectOptions().addSelect(pks), clazz, true, false);
	}

	/**
	 * clazz DB 테이블에 condition 조건으로 조회한 결과 Row 갯수를 반환합니다.
	 * 
	 * @param clazz
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public static int selectSize(Class<?> clazz, Object condition) throws Exception {
		return getDml().selectSize(clazz, condition);
	}

	/**
	 * clazz DB 테이블에서 pkCondition 조건으로 단일 데이터를 조회합니다.
	 * 
	 * @param clazz
	 * @param pkCondition
	 * @return
	 * @throws Exception
	 */
	public static <T> T select(Class<T> clazz, Object pkCondition) throws Exception {
		T data = _select(clazz, pkCondition, null, clazz, false, false);
		return data;
	}

	/**
	 * clazz DB 테이블에서 pkCondition 조건으로 단일 데이터를 잠근 상태로 조회합니다.
	 * 
	 * @param clazz
	 * @param pkCondition
	 * @return
	 * @throws Exception
	 */
	public static <T> T selectWithLock(Class<T> clazz, Object pkCondition) throws Exception {
		T data = _select(clazz, pkCondition, null, clazz, false, true);
		return data;
	}

	public static <T, R> R select(Class<T> clazz, Object pkCondition, Class<R> requiredType) throws Exception {
		R data = _select(clazz, pkCondition, null, requiredType, false, false);
		return data;
	}

	public static <T, R> R selectWithLock(Class<T> clazz, Object pkCondition, Class<R> requiredType) throws Exception {
		R data = _select(clazz, pkCondition, null, requiredType, false, true);
		return data;
	}

	/**
	 * clazz DB 테이블에서 pkCondition 조건으로 단일 데이터를 조회합니다.<br>
	 * checkFound가 true 이면 데이터가 없을 시에 에러가 발생합니다.
	 * 
	 * @param clazz
	 * @param pkCondition
	 * @param checkFound
	 * @return
	 * @throws Exception
	 */
	public static <T> T select(Class<T> clazz, Object pkCondition, boolean checkFound) throws Exception {
		T data = _select(clazz, pkCondition, null, clazz, checkFound, false);
		return data;
	}

	/**
	 * clazz DB 테이블에서 pkCondition 조건으로 단일 데이터를 잠근 상태로 조회합니다.<br>
	 * checkFound가 true 이면 데이터가 없을 시에 에러가 발생합니다.
	 * 
	 * @param clazz
	 * @param pkCondition
	 * @param checkFound
	 * @return
	 * @throws Exception
	 */
	public static <T> T selectWithLock(Class<T> clazz, Object pkCondition, boolean checkFound) throws Exception {
		T data = _select(clazz, pkCondition, null, clazz, checkFound, true);
		return data;
	}

	/**
	 * clazz DB 테이블에서 pkCondition 조건으로 단일 데이터를 조회합니다.
	 * 
	 * @param clazz
	 * @param pkCondition
	 * @param options
	 * @return
	 * @throws Exception
	 */
	public static <T> T select(Class<T> clazz, Object pkCondition, SelectOptions options) throws Exception {
		T data = _select(clazz, pkCondition, options, clazz, false, false);
		return data;
	}

	/**
	 * clazz DB 테이블에서 pkCondition 조건으로 단일 데이터를 조회합니다.
	 * 
	 * @param clazz
	 * @param pkCondition
	 * @param options
	 * @return
	 * @throws Exception
	 */
	public static <T> T selectWithLock(Class<T> clazz, Object pkCondition, SelectOptions options) throws Exception {
		T data = _select(clazz, pkCondition, options, clazz, false, true);
		return data;
	}

	/**
	 * clazz DB 테이블에서 pkCondition 조건으로 단일 데이터를 조회합니다.
	 * 
	 * @param clazz
	 * @param pkCondition
	 * @param options
	 * @param checkFound
	 * @return
	 * @throws Exception
	 */
	public static <T> T select(Class<T> clazz, Object pkCondition, SelectOptions options, boolean checkFound)
			throws Exception {
		return _select(clazz, pkCondition, options, clazz, checkFound, false);
	}

	/**
	 * clazz DB 테이블에서 pkCondition 조건으로 단일 데이터를 조회합니다.
	 * 
	 * @param clazz
	 * @param pkCondition
	 * @param options
	 * @param checkFound
	 * @return
	 * @throws Exception
	 */
	public static <T> T selectWithLock(Class<T> clazz, Object pkCondition, SelectOptions options, boolean checkFound)
			throws Exception {
		return _select(clazz, pkCondition, options, clazz, checkFound, true);
	}

	public static <T, R> R select(Class<T> clazz, Object pkCondition, SelectOptions options, Class<R> requiredType)
			throws Exception {
		return _select(clazz, pkCondition, options, requiredType, false, false);
	}

	public static <T, R> R selectWithLock(Class<T> clazz, Object pkCondition, SelectOptions options,
			Class<R> requiredType) throws Exception {
		return _select(clazz, pkCondition, options, requiredType, false, true);
	}

	/**
	 * clazz DB 테이블에서 pkCondition 조건으로 단일 데이터를 조회합니다.
	 * 
	 * @param clazz
	 * @param pkCondition
	 * @param options
	 * @param requiredType
	 * @param checkFound
	 * @return
	 * @throws Exception
	 */
	public static <T, R> R select(Class<T> clazz, Object pkCondition, SelectOptions options, Class<R> requiredType,
			boolean checkFound) throws Exception {
		return _select(clazz, pkCondition, options, requiredType, checkFound, false);
	}

	/**
	 * clazz DB 테이블에서 pkCondition 조건으로 단일 데이터를 조회합니다.
	 * 
	 * @param clazz
	 * @param pkCondition
	 * @param options
	 * @param requiredType
	 * @param checkFound
	 * @return
	 * @throws Exception
	 */
	public static <T, R> R selectWithLock(Class<T> clazz, Object pkCondition, SelectOptions options,
			Class<R> requiredType, boolean checkFound) throws Exception {
		return _select(clazz, pkCondition, options, requiredType, checkFound, true);
	}

	@SuppressWarnings("unchecked")
	private static <T, R> R _select(Class<T> clazz, Object pkCondition, SelectOptions options, Class<R> requiredType,
			boolean checkFound, boolean lock) throws Exception {
		ValueUtils.assertNotEmpty("pkCondition", pkCondition);
		pkCondition = adjustPkCondition(clazz, pkCondition);

		T data = null;
		if (!lock && !(pkCondition instanceof Query)) {
			Object obj = CacheUtils.get(clazz, pkCondition);
			data = CacheUtils.isNull(obj) ? null : (T) obj;
		}

		if (data == null) {
			data = lock ? getDml().selectWithLock(clazz, pkCondition, options)
					: getDml().select(clazz, pkCondition, options);

			if (data != null) {
				Set<String> selects = options == null || ValueUtils.isEmpty(options.getSelect()) ? null
						: ValueUtils.toSet(options.getSelect());
				Set<String> unselects = options == null || ValueUtils.isEmpty(options.getUnselect()) ? null
						: ValueUtils.toSet(options.getUnselect());
				populateRel(data, selects, unselects);

				boolean cacheable = !(pkCondition instanceof Query) && (options == null
						|| (ValueUtils.isEmpty(options.getSelect()) && ValueUtils.isEmpty(options.getUnselect())));
				if (cacheable) {
					CacheUtils.put(clazz, pkCondition, data);
				}
			}
		}

		if (data == null) {
			if (checkFound) {
				DbData ann = clazz.getAnnotation(DbData.class);
				String code = ann == null || ValueUtils.isEmpty(ann.notFoundCode()) ? "XXXXXX" : ann.notFoundCode();
				String dataType = requiredType == null ? clazz.getSimpleName() : requiredType.getSimpleName();
				throw new BizException(code, "데이터를 찾을 수 없습니다.", new Property("DataType", dataType));
			}
			return null;
		}

		R vo = requiredType.isAssignableFrom(clazz) ? (R) data : populate(data, requiredType, null);
		return vo;
	}

	private static String adjustSqlPath(String sqlPath) {
		if (!sqlPath.endsWith(".sql") && !sqlPath.endsWith("/")) {
			sqlPath += "/";
		}
		return sqlPath;
	}

	public static <R> R select(String sqlPath, Query query, Class<R> requiredType) throws Exception {
		sqlPath = adjustSqlPath(sqlPath);
		Map<String, ?> paramMap = toParamMap(query);
		R data = getDml().selectBySqlPath(sqlPath, paramMap, requiredType);
		return data;
	}

	private static void populate4Insert(Object from, Object to, String[] fieldNames) throws Exception {
		ValueUtils.populate(from, to, fieldNames);
		populate4Insert(to, fieldNames);
	}

	private static void populate4Insert(Object data, String[] fieldNames) throws Exception {
		ValueUtils.adjust(data, fieldNames);
		if (data.getClass().getName().endsWith("His")) {
			return;
		}
		Map<String, Field> fields = ReflectionUtils.getFieldByNameMap(data, true);
		if (fields.containsKey("createUserId")) {
			fields.get("createUserId").set(data, AuthUtils.getUserId());
		}
		if (fields.containsKey("createTime")) {
			fields.get("createTime").set(data, ValueUtils.getZonedDateTime());
		}
		if (fields.containsKey("updateUserId")) {
			fields.get("updateUserId").set(data, AuthUtils.getUserId());
		}
		if (fields.containsKey("updateTime")) {
			fields.get("updateTime").set(data, ValueUtils.getZonedDateTime());
		}
	}

	private static void populate4Update(Object from, Object to, String[] fieldNames) throws Exception {
		ValueUtils.populate(from, to, fieldNames);
		ValueUtils.adjust(to, fieldNames);

		if (to.getClass().getName().endsWith("His")) {
			return;
		}
		Map<String, Field> fields = ReflectionUtils.getFieldByNameMap(to, true);
		if (fields.containsKey("updateUserId")) {
			fields.get("updateUserId").set(to, AuthUtils.getUserId());
		}
		if (fields.containsKey("updateTime")) {
			fields.get("updateTime").set(to, ValueUtils.getZonedDateTime());
		}
	}

	public static void insert(Object data) throws Exception {
		insert(data.getClass(), data, data, null);
	}

	public static void insert(Object data, String[] fieldNames) throws Exception {
		insert(data.getClass(), data, data, fieldNames);
	}

	public static void insert(Class<?> clazz, Object pkCondition, Object data, String[] fieldNames) throws Exception {
		ValueUtils.assertNotEmpty("pkCondition", pkCondition);
		pkCondition = adjustPkCondition(clazz, pkCondition);

		Object _data;
		String[] pkFieldNames = getPkFieldNames(clazz);
		SelectOptions options = new SelectOptions().addSelect(pkFieldNames);
		_data = select(clazz, data, options, clazz, false);
		if (_data != null) {
			throw new BizException("XXXXXX", "이미 저장된 데이터 입니다.");
		}
		_data = clazz.newInstance();
		populateFactoryCode(_data);
		populate4Insert(data, _data, fieldNames);

		getDml().insert(_data, fieldNames);

		CacheUtils.remove(clazz, _data);
	}

	public static void update(Object data) throws Exception {
		update(data.getClass(), data, data, null);
	}

	public static void update(Object data, String[] fieldNames) throws Exception {
		update(data.getClass(), data, data, fieldNames);
	}

	public static void update(Class<?> clazz, Object pkCondition, Object data, String[] fieldNames) throws Exception {
		ValueUtils.assertNotEmpty("pkCondition", pkCondition);
		pkCondition = adjustPkCondition(clazz, pkCondition);

		Object _data;
		{
			String[] pkFieldNames = getPkFieldNames(clazz);
			List<String> selects = ValueUtils.toList(pkFieldNames);
			SelectOptions options = new SelectOptions().addSelect(selects.toArray(new String[selects.size()]));
			_data = select(clazz, pkCondition, options, clazz, true);
		}

		fieldNames = adjustFieldNames(data, clazz, fieldNames, false);

		populate4Update(data, _data, fieldNames);
		getDml().update(_data, fieldNames);

		CacheUtils.remove(clazz, _data);
	}

	private static String[] adjustFieldNames(Object from, Class<?> toClass, String[] fieldNames, boolean insert)
			throws Exception {
		Map<String, Field> fields = ReflectionUtils.getFieldByNameMap(toClass, true);
		List<String> fieldNameList;
		if (!ValueUtils.isEmpty(fieldNames)) {
			fieldNameList = ValueUtils.toList(fieldNames);
		} else {
			fieldNameList = new ArrayList<>();
			String[] pkFieldNames = getPkFieldNames(toClass);
			Set<String> pkFieldNameSet = ValueUtils.toSet(pkFieldNames);
			for (Field field : ReflectionUtils.getFieldList(from, true)) {
				String fieldName = field.getName();
				if (pkFieldNameSet.contains(fieldName) || !fields.containsKey(fieldName)
						|| !ValueUtils.isPrimitive(field.getType())) {
					continue;
				}
				fieldNameList.add(fieldName);
			}
		}
		if (insert) {
			if (fields.containsKey("createUserId")) {
				if (!fieldNameList.contains("createUserId")) {
					fieldNameList.add("createUserId");
				}
			}
			if (fields.containsKey("createTime")) {
				if (!fieldNameList.contains("createTime")) {
					fieldNameList.add("createTime");
				}
			}
		} else {
			if (fieldNameList.contains("createUserId")) {
				fieldNameList.remove("createUserId");
			}
			if (fieldNameList.contains("createTime")) {
				fieldNameList.remove("createTime");
			}
		}
		if (fields.containsKey("updateUserId")) {
			if (!fieldNameList.contains("updateUserId")) {
				fieldNameList.add("updateUserId");
			}
		}
		if (fields.containsKey("updateTime")) {
			if (!fieldNameList.contains("updateTime")) {
				fieldNameList.add("updateTime");
			}
		}
		fieldNames = fieldNameList.toArray(new String[fieldNameList.size()]);

		return fieldNames;
	}

	public static void delete(Class<?> clazz, Object pkCondition, Object data) throws Exception {
		ValueUtils.assertNotEmpty("pkCondition", pkCondition);
		pkCondition = adjustPkCondition(clazz, pkCondition);

		Object _data;
		String[] pkFieldNames = getPkFieldNames(clazz);
		SelectOptions options = new SelectOptions().addSelect(pkFieldNames);
		_data = select(clazz, data, options, clazz, false);
		if (_data != null) {
			throw new BizException("XXXXXX");
		}
		_data = clazz.newInstance();
		getDml().delete(_data);

		CacheUtils.remove(clazz, _data);
	}

	public static void deleteList(Class<?> clazz, Filters filters) throws Exception {
		getDml().deleteList(clazz, filters);
		CacheUtils.removeAll(clazz);
	}

	public static void insertBatch(List<?> list) throws Exception {
		insertBatch(list, null);
	}

	public static void insertBatch(List<?> list, String[] fieldNames) throws Exception {
		for (Object data : list) {
			populate4Insert(data, fieldNames);
		}
		getDml().insertBatch(list, fieldNames);

		for (Object data : list) {
			CacheUtils.remove(data.getClass(), data);
		}
	}

	public static void insertBatch(Class<?> clazz, List<?> list, String[] fieldNames) throws Exception {
		if (ValueUtils.isEmpty(list)) {
			return;
		}

		fieldNames = adjustFieldNames(list.get(0), clazz, fieldNames, true);

		List<Object> ilist = new ArrayList<>();
		String[] pkFieldNames = getPkFieldNames(clazz);
		for (Object data : list) {
			Object _data = clazz.newInstance();
			populatePks(data, _data, pkFieldNames);
			populate4Insert(data, _data, fieldNames);
			ilist.add(_data);
		}
		getDml().insertBatch(ilist, fieldNames);

		for (Object data : ilist) {
			CacheUtils.remove(data.getClass(), data);
		}
	}

	public static void updateBatch(List<?> list) throws Exception {
		getDml().updateBatch(list);
	}

	public static void updateBatch(List<?> list, String[] fieldNames) throws Exception {
		getDml().updateBatch(list, fieldNames);
	}

	public static void updateBatch(Class<?> clazz, List<?> list, String[] fieldNames) throws Exception {
		if (ValueUtils.isEmpty(list)) {
			return;
		}

		fieldNames = adjustFieldNames(list.get(0), clazz, fieldNames, false);

		List<Object> ulist = new ArrayList<>();
		String[] pkFieldNames = getPkFieldNames(clazz);
		for (Object data : list) {
			Object _data = clazz.newInstance();
			populatePks(data, _data, pkFieldNames);
			populate4Update(data, _data, fieldNames);
			ulist.add(_data);
		}
		getDml().updateBatch(ulist, fieldNames);

		for (Object data : ulist) {
			CacheUtils.remove(data.getClass(), data);
		}
	}

	public static void deleteBatch(List<?> list) throws Exception {
		getDml().deleteBatch(list);
	}

	public static void upsertBatch(Class<?> clazz, List<?> list) throws Exception {
		upsertBatch(clazz, list, null);
	}

	public static void upsertBatch(Class<?> clazz, List<?> list, String[] fieldNames) throws Exception {
		if (ValueUtils.isEmpty(list)) {
			return;
		}

		List<Object> ilist = new ArrayList<>();
		List<Object> ulist = new ArrayList<>();
		Field idField = ReflectionUtils.getField(list.get(0), "id");
		if (idField != null) {
			for (Object data : list) {
				Object idValue = idField.get(data);
				if (ValueUtils.isEmpty(idValue)) {
					ilist.add(data);
				} else {
					ulist.add(data);
				}
			}
		} else {
			String[] pkFieldNames = getPkFieldNames(clazz);
			SelectOptions options = new SelectOptions().addSelect(pkFieldNames);
			for (Object data : list) {
				Object _data = select(clazz, data, options, clazz, false);
				if (_data == null) {
					ilist.add(data);
				} else {
					ulist.add(data);
				}
			}
		}

		insertBatch(clazz, ilist, fieldNames);
		updateBatch(clazz, ulist, fieldNames);
	}

	public static void deleteBatch(Class<?> clazz, List<?> list) throws Exception {
		String[] pkFieldNames = getPkFieldNames(clazz);
		if (pkFieldNames.length > 1 && "factoryCode".equals(pkFieldNames[0])) {
			List<String> fieldList = ValueUtils.toList(pkFieldNames);
			fieldList.remove(0);
			pkFieldNames = fieldList.toArray(new String[fieldList.size()]);
		}
		List<Object> dlist = new ArrayList<>();
		for (Object data : list) {
			Object _data = clazz.newInstance();
			populateFactoryCode(_data);
			ValueUtils.populate(data, _data, pkFieldNames);
			dlist.add(_data);
		}
		getDml().deleteBatch(dlist);

		for (Object data : dlist) {
			CacheUtils.remove(data.getClass(), data);
		}
	}

	private static void populateFactoryCode(Object data) throws Exception {
		Field factoryField = ReflectionUtils.getField(data, "factoryCode");
		if (factoryField == null) {
			return;
		}
		factoryField.set(data, AuthUtils.getFactoryCode());
	}

	public static <T> List<T> selectList(Class<T> clazz, Query query) throws Exception {
		return selectList(clazz, query, null, null);
	}

	public static <T, R> List<R> selectList(Class<T> clazz, Query query, Class<R> requiredType) throws Exception {
		List<R> list = selectList(clazz, query, null, requiredType);
		return list;
	}

	@SuppressWarnings("unchecked")
	private static <T, R> List<R> selectList(Class<T> clazz, Query query, Set<String> idEscapeFields,
			Class<R> requiredType) throws Exception {
		List<T> itemList = getDml().selectList(clazz, query);
		if (requiredType == null || clazz.equals(requiredType)) {
			return (List<R>) itemList;
		}
		List<R> list = new ArrayList<>(itemList.size());
		Set<String> selects = ValueUtils.isEmpty(query.getSelect()) ? null : new HashSet<>(query.getSelect());
		Set<String> unselects = ValueUtils.isEmpty(query.getUnselect()) ? null : new HashSet<>(query.getUnselect());
		for (T item : itemList) {
			populateRel(item, selects, unselects);
			R _data = populate(item, requiredType, idEscapeFields);
			list.add(_data);
		}
		return list;
	}

	public static <R> List<R> selectList(String sqlPath, Query query, Class<R> requiredType) throws Exception {
		sqlPath = adjustSqlPath(sqlPath);
		Map<String, ?> paramMap = toParamMap(query);
		List<R> list = getDml().selectListBySqlPath(sqlPath, paramMap, requiredType, query.getPageIndex(),
				query.getPageSize());
		return list;
	}

	public static <T, R> List<R> selectOneToManyList(Class<T> clazz, Object parent, Class<R> requiredType)
			throws Exception {
		return selectOneToManyList(clazz, parent, requiredType, null);
	}

	public static <T, R> List<R> selectOneToManyList(Class<T> clazz, Object parent, Class<R> requiredType,
			Query options) throws Exception {
		Query query;
		if (options == null) {
			query = new Query();
			if (ReflectionUtils.getField(clazz, "seqNo") != null) {
				query.addOrder("seqNo", true);
			}
		} else {
			query = options;
			if (ValueUtils.isEmpty(query.getOrder()) && ReflectionUtils.getField(clazz, "seqNo") != null) {
				query.addOrder("seqNo", true);
			}
		}

		Table table = getDml().getTable(clazz);
		String[] pkFieldNames = table.getPkFieldNames();
		Set<String> idEscapeFields = new HashSet<>();
		for (String pkFieldName : pkFieldNames) {
			if ("factoryCode".equals(pkFieldName)) {
				query.addFilter("factoryCode", AuthUtils.getFactoryCode());
				continue;
			}
			Field field = ReflectionUtils.getField(parent, pkFieldName);
			if (field == null) {
				query.addOrder(pkFieldName, true);
				continue;
			}
			Object value = field.get(parent);
			query.addFilter(pkFieldName, value);
			idEscapeFields.add(pkFieldName);
		}
		List<R> list = DbUtils.selectList(clazz, query, idEscapeFields, requiredType);
		return list;
	}

	public static <T, R> Page<R> selectPage(Class<T> clazz, Query query, Class<R> requiredType) throws Exception {
		Page<T> itemPage = getDml().selectPage(clazz, query);
		List<R> list = new ArrayList<>(itemPage.getSize());
		Set<String> selects = ValueUtils.isEmpty(query.getSelect()) ? null : new HashSet<>(query.getSelect());
		Set<String> unselects = ValueUtils.isEmpty(query.getUnselect()) ? null : new HashSet<>(query.getUnselect());
		for (T item : itemPage.getList()) {
			populateRel(item, selects, unselects);
			R _data = populate(item, requiredType, null);
			list.add(_data);
		}
		Page<R> page = ValueUtils.populate(itemPage, new Page<>(), "index", "lastIndex", "size", "firstResultIndex",
				"maxResultSize", "totalSize");
		page.setList(list);
		return page;
	}

	public static <R> Page<R> selectPage(String sqlPath, Query query, Class<R> requiredType) throws Exception {
		sqlPath = adjustSqlPath(sqlPath);
		Map<String, ?> paramMap = toParamMap(query);
		Page<R> page = getDml().selectPageBySqlPath(sqlPath, paramMap, requiredType, query.getPageIndex(),
				query.getPageSize());
		return page;
	}

	public static String toSqlPath(Class<?> clazz, String sqlName) throws Exception {
		String sqlPath = ReflectionUtils.getPackagePath(clazz) + sqlName;
		return sqlPath;
	}

	private static VmUtils utils = new VmUtils();

	private static Map<String, ?> toParamMap(Object condition) {
		@SuppressWarnings("unchecked")
		Map<String, Object> paramMap = condition instanceof Map ? (Map<String, Object>) condition
				: new HashMap<String, Object>();
		paramMap.put("Utils", utils);
		if (condition instanceof Query) {
			Query query = (Query) condition;
			if (!ValueUtils.isEmpty(query.getFilter())) {
				for (Filter filter : query.getFilter()) {
					String name = filter.getLeftOperand();
					Object value = ValueUtils.isEmpty(filter.getRightOperand()) ? null
							: (filter.getRightOperand().size() > 1 ? filter.getRightOperand()
									: filter.getRightOperand().get(0));
					paramMap.put(name, value);
				}
			}
		}
		return paramMap;
	}

	private static void populatePks(Object from, Object to, String[] pkFieldNames) throws Exception {
		Map<String, Field> fromFields = ReflectionUtils.getFieldByNameMap(from, true);
		Map<String, Field> toFields = ReflectionUtils.getFieldByNameMap(to, true);
		for (String pkFieldName : pkFieldNames) {
			if (!toFields.containsKey(pkFieldName)) {
				throw new IllegalArgumentException(
						"Couldn't find a field: " + to.getClass().getName() + "." + pkFieldName);
			}
			if (!fromFields.containsKey(pkFieldName)) {
				if ("factoryCode".equals(pkFieldName)) {
					toFields.get(pkFieldName).set(to, AuthUtils.getFactoryCode());
					continue;
				}
				throw new IllegalArgumentException(
						"Couldn't find a field: " + to.getClass().getName() + "." + pkFieldName);
			}

			toFields.get(pkFieldName).set(to, fromFields.get(pkFieldName).get(from));
		}
	}

	private static final Map<String, String> CODE_DESC_NAMES = new ConcurrentHashMap<>();

	private static <R> R populate(Object from, Class<R> requiredType, Set<String> idEscapeFields) throws Exception {
		R to = requiredType.newInstance();
		Table table = getDml().getTable(from);

		if (table.containsLinkedTable()) {
			Map<String, Field> fields = ReflectionUtils.getFieldByNameMap(requiredType, true);
			for (Column col : table.getColumnList()) {
				DbistRelation relation = col.getRelation();
				if (relation == null) {
					continue;
				}

				Field field = col.getField();
				Object subValue = field.get(from);
				if (subValue == null) {
					continue;
				}

				String prefix = field.getName();
				String cand1;
				int cand1len;
				String cand2;
				int cand2len;
				{
					String ud = ValueUtils.toDelimited(prefix, '_');
					if (!ud.contains("_")) {
						cand1 = ud;
						cand1len = cand1.length();
						cand2 = null;
						cand2len = 0;
					} else {
						String[] strs = StringUtils.tokenizeToStringArray(ud, "_");
						int len = strs.length;
						cand1 = strs[len - 1];
						cand1len = cand1.length();
						cand2 = strs[len - 2] + StringUtils.capitalize(cand1);
						cand2len = cand2.length();
					}
				}

				Table likedTable = col.getTable();
				if (likedTable != null && "mgcmtbldat".equals(likedTable.getName()) && relation.field().length > 2) {
					final String parentFieldName = relation.field()[2];
					String descFieldName = SyncCtrlUtils.wrap("DbUtils.CODE_DESC_NAMES", CODE_DESC_NAMES,
							parentFieldName, new Closure<String, Exception>() {
								@Override
								public String execute() throws Exception {
									String fieldName = parentFieldName;
									if (fieldName.endsWith("Code")) {
										fieldName = parentFieldName.substring(0, parentFieldName.length() - 4);
									}
									fieldName += "Desc";
									return fieldName;
								}
							});
					if (fields.containsKey(descFieldName)) {
						Field keyDescField = ReflectionUtils.getField(subValue, "keyDesc");
						if (keyDescField != null) {
							Object value = keyDescField.get(subValue);
							if (value != null) {
								fields.get(descFieldName).set(to, value);
							}
						}
					}
					// String tableName = relation.field()[1];
					// if (tableName.startsWith("value:")) {
					// tableName = tableName.substring(6);
					// Map gcmtable = getDml().select("MGCMTBLDAT", new String[] { "", tableName },
					// null, Map.class);
					// }

					continue;
				}

				for (Field subField : ReflectionUtils.getFieldList(subValue, true)) {
					String subFieldName = subField.getName();
					if (subFieldName.startsWith(cand1)) {
						subFieldName = subFieldName.substring(cand1len);
					} else if (cand2 != null && subFieldName.startsWith(cand2)) {
						subFieldName = subFieldName.substring(cand2len);
					}
					subFieldName = prefix + StringUtils.capitalize(subFieldName);
					if (fields.containsKey(subFieldName)) {
						Object value = subField.get(subValue);
						if (value != null) {
							fields.get(subFieldName).set(to, value);
						}
					}
				}
			}
		}

		List<ResField> relations = getResFields(from.getClass());
		if (!relations.isEmpty()) {
			Map<String, Field> fields = ReflectionUtils.getFieldByNameMap(requiredType, true);
			for (ResField relation : relations) {
				Field field = relation.getField();
				Object subValue = field.get(from);
				if (subValue == null) {
					continue;
				}

				String prefix = field.getName();
				String cand1;
				int cand1len;
				String cand2;
				int cand2len;
				{
					String ud = ValueUtils.toDelimited(prefix, '_');
					if (!ud.contains("_")) {
						cand1 = ud;
						cand1len = cand1.length();
						cand2 = null;
						cand2len = 0;
					} else {
						String[] strs = StringUtils.tokenizeToStringArray(ud, "_");
						int len = strs.length;
						cand1 = strs[len - 1];
						cand1len = cand1.length();
						cand2 = strs[len - 2] + StringUtils.capitalize(cand1);
						cand2len = cand2.length();
					}
				}

				for (Field subField : ReflectionUtils.getFieldList(subValue, true)) {
					String subFieldName = subField.getName();
					if (subFieldName.startsWith(cand1)) {
						subFieldName = subFieldName.substring(cand1len);
					} else if (cand2 != null && subFieldName.startsWith(cand2)) {
						subFieldName = subFieldName.substring(cand2len);
					}
					subFieldName = prefix + StringUtils.capitalize(subFieldName);
					if (fields.containsKey(subFieldName)) {
						Object value = subField.get(subValue);
						if (value != null) {
							fields.get(subFieldName).set(to, value);
						}
					}
				}
			}
		}

		ValueUtils.populate(from, to);

		if (ReflectionUtils.getField(from, "id") != null) {
			return to;
		}

		_populateId(from, to, table.getPkFieldNames(), idEscapeFields);

		return to;
	}

	private static void _populateId(Object from, Object to, String[] pkFieldNames, Set<String> idEscapeFields)
			throws Exception {
		Field idField = ReflectionUtils.getField(to, "id");
		if (ReflectionUtils.getField(to, "id") == null) {
			return;
		}

		StringBuffer buf = new StringBuffer();
		int i = 0;
		for (String pkFieldName : pkFieldNames) {
			if (i == 0 && "factoryCode".equals(pkFieldName) && pkFieldNames.length != 1) {
				continue;
			}
			if (idEscapeFields != null && idEscapeFields.contains(pkFieldName)) {
				continue;
			}
			if (i++ != 0) {
				buf.append(",");
			}
			buf.append(ReflectionUtils.getField(from, pkFieldName).get(from));
		}
		idField.set(to, buf.toString());
	}

	public static void addEquals(Query query, String fieldName, Object value) {
		if (ValueUtils.isEmpty(value)) {
			return;
		}
		query.addFilter(fieldName, value);
	}

	public static void addContains(Query query, String fieldName, String value) {
		if (ValueUtils.isEmpty(value)) {
			return;
		}
		query.addFilter(fieldName, "like", "%" + value + "%");
	}

	public static void addContainsOr(Query query, String fieldNames, String value) {
		if (ValueUtils.isEmpty(value)) {
			return;
		}
		Filters filters = new Filters("or");
		query.addFilters(filters);
		for (String fieldName : StringUtils.tokenizeToStringArray(fieldNames, ",")) {
			filters.addFilter(fieldName, "like", "%" + value + "%");
		}
	}

	public static void addStartsWith(Query query, String fieldName, String value) {
		if (ValueUtils.isEmpty(value)) {
			return;
		}
		query.addFilter(fieldName, "like", value + "%");
	}

	public static void addEndsWith(Query query, String fieldName, String value) {
		if (ValueUtils.isEmpty(value)) {
			return;
		}
		query.addFilter(fieldName, "like", "%" + value);
	}

	public static void addEqualsIfTrue(Query query, String fieldName, boolean value) {
		if (!value) {
			return;
		}
		query.addFilter(fieldName, value);
	}

	public static void addEqualsIfFalse(Query query, String fieldName, boolean value) {
		if (value) {
			return;
		}
		query.addFilter(fieldName, value);
	}

	public static void addEqualsIfTrue(List<Filter> filter, String fieldName, boolean value) {
		if (!value) {
			return;
		}
		filter.add(new Filter(fieldName, value));
	}

	public static void addEqualsIfFalse(List<Filter> filter, String fieldName, boolean value) {
		if (value) {
			return;
		}
		filter.add(new Filter(fieldName, value));
	}

	public static void addFilter(Query query, String fieldName, String operator, Object value) {
		if (ValueUtils.isEmpty(value)) {
			return;
		}
		query.addFilter(fieldName, operator, value);
	}

	public static void addFiltersOr(Query query, Filter... filter) {
		if (ValueUtils.isEmpty(filter)) {
			return;
		} else if (filter.length == 1) {
			query.addFilter(filter);
			return;
		}
		Filters filters = new Filters("or");
		query.addFilters(filters);
		filters.addFilter(filter);
	}

	private static void populateRel(Object data, Set<String> selects, Set<String> unselects) throws Exception {
		Class<?> clazz = data.getClass();
		List<ResField> list = getResFields(clazz);
		if (list.isEmpty()) {
			return;
		}

		for (ResField resField : list) {
			boolean skip = false;
			Object[] args;
			{
				List<Object> argList = new ArrayList<>(resField.getMethod().getParameterCount());
				int i = 0;
				for (Parameter param : resField.getMethod().getParameters()) {
					try {
						if (!resField.getParams().containsKey(i) || !ValueUtils.isPrimitive(param.getType())) {
							argList.add(i, null);
							continue;
						}

						StringBuffer buf = new StringBuffer();
						List<String> fieldList = resField.getParams().get(i);
						int j = 0;
						for (String fieldName : fieldList) {
							if (selects != null && !selects.contains(fieldName)) {
								skip = true;
								continue;
							} else if (unselects != null && unselects.contains(fieldName)) {
								skip = true;
								continue;
							}

							String str = ValueUtils.getString(data, fieldName);
							if (ValueUtils.isEmpty(str)) {
								skip = true;
								break;
							}
							buf.append(j++ == 0 ? "" : ",").append(str);
						}

						if (skip) {
							// argList.add(i, null);
							// continue;
							break;
						}

						argList.add(i, buf.toString());

					} finally {
						i++;
					}
				}

				if (skip) {
					continue;
				}

				args = argList.toArray(new Object[argList.size()]);
			}

			Object bean = BeanUtils.get(resField.getCtrl());
			String fieldName = resField.getField().getName();
			try {
				Object value = resField.getMethod().invoke(bean, args);
				ValueUtils.set(data, fieldName, value);
			} catch (Exception e) {
				if (resField.isIgnoreError()) {
					continue;
				}
				throw e;
			}
		}
	}

	private static List<ResField> getResFields(Class<?> clazz) throws Exception {
		List<ResField> list = SyncCtrlUtils.wrap("DbUtils.REL." + clazz.getSimpleName(), REL, clazz,
				new Closure<List<ResField>, Exception>() {
					@Override
					public List<ResField> execute() throws Exception {
						List<ResField> list = new ArrayList<>();
						for (Field field : ReflectionUtils.getFieldList(clazz, true)) {
							ResRelation ann = field.getAnnotation(ResRelation.class);
							if (ann == null || ann.controller() == null || ann.field().length == 0) {
								continue;
							}

							Method method = null;
							for (Method item : ann.controller().getMethods()) {
								if (item.getName().equals(ann.methodName())) {
									method = item;
									break;
								}
							}
							if (method == null) {
								continue;
							}

							ResField resField = new ResField();
							resField.setField(field);
							resField.setCtrl(ann.controller());
							resField.setMethod(method);
							List<String> fields = new ArrayList<>();
							for (String fn : ann.field()) {
								fields.add(fn);
							}
							resField.params.put(0, fields);
							resField.setIgnoreError(ann.ignoreError());
							list.add(resField);
						}
						return list;
					}
				});
		return list;
	}

	private static final Map<Class<?>, List<ResField>> REL = new ConcurrentHashMap<>();

	@Data
	private static class ResField {
		private Field field;
		private Class<?> ctrl;
		private Method method;
		Map<Integer, List<String>> params = new LinkedHashMap<>();
		private boolean ignoreError;
	}
}
