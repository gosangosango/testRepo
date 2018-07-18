package kr.co.miracom.mes.bas.resource.simple.code_data.service.get_list;

import kr.co.miracom.dbist.dml.Page;
import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.bas.model.MGcmTblDat;
import kr.co.miracom.mes.bas.model.MGcmTblDef;
import kr.co.miracom.mes.bas.resource.simple.code_data.model.CodeData;

/**
 * Get List Service: CodeData
 * @author myjung.jung
 * @since 2018. 06. 05.
 */
public class GetCodeDataList {
    public GetListOut<CodeData> getList(GetCodeDataListIn input) throws Exception {
        if (ValueUtils.isEmpty(input.getTableName())) {
            return new GetListOut<>();
        }

        MGcmTblDef table = DbUtils.select(MGcmTblDef.class, input.getTableName(),
                        new SelectOptions().addSelect("seqNoFlag"), MGcmTblDef.class, true);

        Query query = DbUtils.newQuery(input);
        query.addFilter("factoryCode", AuthUtils.getFactoryCode());
        DbUtils.addEquals(query, "tableName", input.getTableName());
        DbUtils.addEquals(query, "key1", input.getKey1());
        DbUtils.addEquals(query, "key2", input.getKey2());
        DbUtils.addEquals(query, "key3", input.getKey3());
        DbUtils.addEquals(query, "key4", input.getKey4());
        DbUtils.addEquals(query, "key5", input.getKey5());
        DbUtils.addEquals(query, "keyDesc", input.getKeyDesc());
        // DbUtils.addEquals(query, "seqNo", input.getSeqNo());
        DbUtils.addEquals(query, "data1", input.getData1());
        DbUtils.addEquals(query, "data2", input.getData2());
        DbUtils.addEquals(query, "data3", input.getData3());
        DbUtils.addEquals(query, "data4", input.getData4());
        DbUtils.addEquals(query, "data5", input.getData5());
        DbUtils.addEquals(query, "data6", input.getData6());
        DbUtils.addEquals(query, "data7", input.getData7());
        DbUtils.addEquals(query, "data8", input.getData8());
        DbUtils.addEquals(query, "data9", input.getData9());
        DbUtils.addEquals(query, "data10", input.getData10());
        DbUtils.addContains(query, "key1", input.getKey1Like());
        DbUtils.addContains(query, "key2", input.getKey2Like());
        DbUtils.addContains(query, "key3", input.getKey3Like());
        DbUtils.addContains(query, "key4", input.getKey4Like());
        DbUtils.addContains(query, "key5", input.getKey5Like());
        DbUtils.addContains(query, "keyDesc", input.getKeyDescLike());
        DbUtils.addContains(query, "data1", input.getData1Like());
        DbUtils.addContains(query, "data2", input.getData2Like());
        DbUtils.addContains(query, "data3", input.getData3Like());
        DbUtils.addContains(query, "data4", input.getData4Like());
        DbUtils.addContains(query, "data5", input.getData5Like());
        DbUtils.addContains(query, "data6", input.getData6Like());
        DbUtils.addContains(query, "data7", input.getData7Like());
        DbUtils.addContains(query, "data8", input.getData8Like());
        DbUtils.addContains(query, "data9", input.getData9Like());
        DbUtils.addContains(query, "data10", input.getData10Like());
        // DbUtils.addContains(query, "createUserId", input.getCreateUserId());
        // DbUtils.addEquals(query, "createTime", input.getCreateTime());
        // DbUtils.addContains(query, "updateUserId", input.getUpdateUserId());
        // DbUtils.addEquals(query, "updateTime", input.getUpdateTime());
        if (table.isSeqNoFlag()) {
            query.addOrder("seqNo, key1, key2, key3, key4, key5", true);
        } else {
            query.addOrder("key1, key2, key3, key4, key5", true);
        }

        Page<CodeData> page = DbUtils.selectPage(MGcmTblDat.class, query, CodeData.class);
        GetListOut<CodeData> output = new GetListOut<>();
        ValueUtils.populate(page, output);
        return output;
    }
}
