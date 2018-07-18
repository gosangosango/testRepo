/**
 * Copyright 2011-2014 the original author or authors.
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
package kr.co.miracom.dbist.dml.jdbc;

import java.util.Map;

import kr.co.miracom.dbist.metadata.Sequence;

/**
 * @author Steve M. Jung
 * @since 2013. 9. 7. (version 2.0.3)
 */
public class QueryMapperPostgresql extends AbstractQueryMapper {

    public String getDbType() {
        return "postgresql";
    }

    public boolean isSupportedPaginationQuery() {
        return true;
    }

    public boolean isSupportedLockTimeout() {
        return false;
    }

    public String applyPagination(String sql, Map<String, ?> paramMap, int pageIndex, int pageSize,
                    int firstResultIndex, int maxResultSize) {
        boolean pagination = pageIndex >= 0 && pageSize > 0;
        boolean fragment = firstResultIndex > 0 || maxResultSize > 0;
        if (!pagination && !fragment)
            return sql;
        if (!pagination) {
            pageIndex = 0;
            pageSize = 0;
        }
        if (firstResultIndex < 0)
            firstResultIndex = 0;
        if (maxResultSize < 0)
            maxResultSize = 0;

        @SuppressWarnings("unchecked")
        Map<String, Object> _paramMap = (Map<String, Object>) paramMap;
        String subsql = null;
        int forUpdateIndex = sql.toLowerCase().lastIndexOf("for update");
        if (forUpdateIndex > -1) {
            subsql = sql.substring(forUpdateIndex - 1);
            sql = sql.substring(0, forUpdateIndex - 1);
        }

        StringBuffer buf = new StringBuffer();
        int pageFromIndex = pagination ? pageIndex * pageSize : 0;
        int offset = pageFromIndex + firstResultIndex;
        long limit = 0;
        if (pageSize > 0) {
            limit = pageSize - firstResultIndex;
            if (maxResultSize > 0)
                limit = Math.min(limit, maxResultSize);
        } else if (maxResultSize > 0) {
            limit = maxResultSize;
        } else if (limit == 0) {
            limit = Long.MAX_VALUE;
        }
        buf.append(sql);
        if (offset > 0 && limit > 0) {
            _paramMap.put("__offset", offset);
            _paramMap.put("__limit", limit);
            buf.append(" limit :__limit offset :__offset");
        } else if (limit > 0) {
            _paramMap.put("__limit", limit);
            buf.append(" limit :__limit");
        }

        if (subsql != null)
            buf.append(subsql);
        return buf.toString();
    }

    public String toNextval(Sequence sequence) {
        if (sequence.getName() == null || sequence.isAutoIncrement())
            return null;
        return "nextval('" + sequence.getName() + "')";
    }

    public String getFunctionLowerCase() {
        return "lower";
    }

    public String getQueryCountTable() {
        return "select count(*) from information_schema.tables where lower(table_schema) = '${domain}' and lower(table_name) = ?";
    }

    private static final String queryPkColumnNames = "select lower(col.column_name) as name"
                    + " from information_schema.table_constraints tbl," + " information_schema.key_column_usage col"
                    + " where lower(tbl.table_schema) = '${domain}' and lower(tbl.table_name) = ? and tbl.constraint_type = 'PRIMARY KEY'"
                    + " and col.constraint_name = tbl.constraint_name and col.constraint_schema = tbl.table_schema and col.table_name = tbl.table_name"
                    + " order by col.ordinal_position";

    public String getQueryPkColumnNames() {
        return queryPkColumnNames;
    }

    public String getQueryColumns() {
        return "select lower(column_name) as name, data_type as datatype from information_schema.columns where lower(table_schema) = '${domain}' and lower(table_name) = ? order by ordinal_position";
    }

    public String getQueryColumn() {
        return "select lower(column_name) as name, data_type as datatype from information_schema.columns where lower(table_schema) = '${domain}' and lower(table_name) = ? and lower(column_name) = ?";
    }

    public String getQueryCountIdentity() {
        return "";
    }

    public String getQueryCountSequence() {
        return "";// "select count(*) from information_schema.sequences where lower(sequence_schema) = '${domain}' and
                  // lower(sequence_name) = ?";
    }

}
