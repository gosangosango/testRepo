package kr.co.miracom.mes.bas.resource.simple.code_table.service.get_list;

import kr.co.miracom.dbist.dml.Page;
import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.bas.model.MGcmTblDef;
import kr.co.miracom.mes.bas.resource.simple.code_table.model.CodeTable;

/**
 * Get List Service: CodeTable
 * @author myjung.jung
 * @since 2018. 06. 05.
 */
public class GetCodeTableList {
    public GetListOut<CodeTable> getList(GetCodeTableListIn input) throws Exception {
        Query query = DbUtils.newQuery(input);
        DbUtils.addContains(query, "tableName", input.getTableName());
        DbUtils.addContains(query, "tableDesc", input.getTableDesc());
        // DbUtils.addEquas(query, "seqNoFlag", input.getSeqNoFlag());
        // DbUtils.addContains(query, "key1Name", input.getKey1Name());
        // DbUtils.addContains(query, "key1Type", input.getKey1Type());
        // DbUtils.addEquas(query, "key1Len", input.getKey1Len());
        // DbUtils.addContains(query, "key2Name", input.getKey2Name());
        // DbUtils.addContains(query, "key2Type", input.getKey2Type());
        // DbUtils.addEquas(query, "key2Len", input.getKey2Len());
        // DbUtils.addContains(query, "key3Name", input.getKey3Name());
        // DbUtils.addContains(query, "key3Type", input.getKey3Type());
        // DbUtils.addEquas(query, "key3Len", input.getKey3Len());
        // DbUtils.addContains(query, "key4Name", input.getKey4Name());
        // DbUtils.addContains(query, "key4Type", input.getKey4Type());
        // DbUtils.addEquas(query, "key4Len", input.getKey4Len());
        // DbUtils.addContains(query, "key5Name", input.getKey5Name());
        // DbUtils.addContains(query, "key5Type", input.getKey5Type());
        // DbUtils.addEquas(query, "key5Len", input.getKey5Len());
        // DbUtils.addContains(query, "data1Name", input.getData1Name());
        // DbUtils.addContains(query, "data1Type", input.getData1Type());
        // DbUtils.addEquas(query, "data1Len", input.getData1Len());
        // DbUtils.addContains(query, "data2Name", input.getData2Name());
        // DbUtils.addContains(query, "data2Type", input.getData2Type());
        // DbUtils.addEquas(query, "data2Len", input.getData2Len());
        // DbUtils.addContains(query, "data3Name", input.getData3Name());
        // DbUtils.addContains(query, "data3Type", input.getData3Type());
        // DbUtils.addEquas(query, "data3Len", input.getData3Len());
        // DbUtils.addContains(query, "data4Name", input.getData4Name());
        // DbUtils.addContains(query, "data4Type", input.getData4Type());
        // DbUtils.addEquas(query, "data4Len", input.getData4Len());
        // DbUtils.addContains(query, "data5Name", input.getData5Name());
        // DbUtils.addContains(query, "data5Type", input.getData5Type());
        // DbUtils.addEquas(query, "data5Len", input.getData5Len());
        // DbUtils.addContains(query, "data6Name", input.getData6Name());
        // DbUtils.addContains(query, "data6Type", input.getData6Type());
        // DbUtils.addEquas(query, "dat6Len", input.getDat6Len());
        // DbUtils.addContains(query, "data7Name", input.getData7Name());
        // DbUtils.addContains(query, "data7Type", input.getData7Type());
        // DbUtils.addEquas(query, "data7Len", input.getData7Len());
        // DbUtils.addContains(query, "data8Name", input.getData8Name());
        // DbUtils.addContains(query, "data8Type", input.getData8Type());
        // DbUtils.addEquas(query, "data8Len", input.getData8Len());
        // DbUtils.addContains(query, "data9Name", input.getData9Name());
        // DbUtils.addContains(query, "data9Type", input.getData9Type());
        // DbUtils.addEquas(query, "data9Len", input.getData9Len());
        // DbUtils.addContains(query, "data10Name", input.getData10Name());
        // DbUtils.addContains(query, "data10Type", input.getData10Type());
        // DbUtils.addEquas(query, "data10Len", input.getData10Len());
        // DbUtils.addContains(query, "createUserId", input.getCreateUserId());
        // DbUtils.addEquas(query, "createTime", input.getCreateTime());
        // DbUtils.addContains(query, "updateUserId", input.getUpdateUserId());
        // DbUtils.addEquas(query, "updateTime", input.getUpdateTime());
        query.addOrder("tableName", true);
        Page<CodeTable> page = DbUtils.selectPage(MGcmTblDef.class, query, CodeTable.class);
        GetListOut<CodeTable> output = new GetListOut<>();
        ValueUtils.populate(page, output);
        return output;
    }
}
