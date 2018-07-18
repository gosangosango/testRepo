/**
 * Copyright 2011-2012 the original author or authors.
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
package kr.co.miracom.dbist.dml.impl;

import java.util.List;
import java.util.Map;

import kr.co.miracom.dbist.dml.AbstractDml;
import kr.co.miracom.dbist.dml.Dml;
import kr.co.miracom.dbist.dml.Page;
import kr.co.miracom.dbist.metadata.Table;

/**
 * @author Steve M. Jung
 * @since 2011. 6. 2. (version 0.0.1)
 */
public class DmlJpa extends AbstractDml implements Dml {

	public Class<?> getClass(String tableName) {
		// TODO Auto-generated method stub
		return null;
	}

	public Table getTable(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	public void insert(Object data) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void insertBatch(List<?> list) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void insert(Object data, String... fieldNames) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void insertBatch(List<?> list, String... fieldNames) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void update(Object data) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void updateBatch(List<?> list) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void update(Object data, String... fieldNames) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void updateBatch(List<?> list, String... fieldNames) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void delete(Object data) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void deleteBatch(List<?> list) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public int selectSize(Class<?> clazz, Object condition) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public <T> List<T> selectList(Class<T> clazz, Object condition) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> List<T> selectListWithLock(Class<T> clazz, Object condition) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> List<T> selectListByQl(String ql, Map<String, ?> paramMap, Class<T> requiredType, int pageIndex, int pageSize, int firstResultIndex,
			int maxResultSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public int selectSizeByQl(String ql, Map<String, ?> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public <T> List<T> selectListByQlPath(String qlPath, Map<String, ?> paramMap, Class<T> requiredType, int pageIndex, int pageSize,
			int firstResultIndex, int maxResultSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> Page<T> selectPageByQlPath(String qlPath, Map<String, ?> paramMap, Class<T> requiredType, int pageIndex, int pageSize,
			int firstResultIndex, int maxResultSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public int selectSizeByQlPath(String qlPath, Map<String, ?> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deleteList(Class<?> clazz, Object condition) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public int executeByQl(String ql, Map<String, ?> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public int executeByQlPath(String qlPath, Map<String, ?> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
}
