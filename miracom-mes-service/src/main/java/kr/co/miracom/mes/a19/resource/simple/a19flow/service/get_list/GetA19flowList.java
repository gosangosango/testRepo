package kr.co.miracom.mes.a19.resource.simple.a19flow.service.get_list;

import kr.co.miracom.dbist.dml.Page;
import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.a19.model.MA19FlwDef;
import kr.co.miracom.mes.a19.resource.simple.a19flow.model.A19flow;

/**
 * Get List Service: A19flow
 * @author Zotac023
 * @since 2018. 07. 17.
 */
public class GetA19flowList {
    public GetListOut<A19flow> getList(GetA19flowListIn input) throws Exception {
        Query query = DbUtils.newQuery(input);
        // DbUtils.addContains(query, "flowCode", input.getFlowCode());
        // DbUtils.addContains(query, "flowDesc", input.getFlowDesc());
        // DbUtils.addContains(query, "createUserId", input.getCreateUserId());
        // DbUtils.addEquals(query, "createTime", input.getCreateTime());
        // DbUtils.addContains(query, "UpdateUserId", input.getUpdateUserId());
        // DbUtils.addEquals(query, "UpdateTime", input.getUpdateTime());
        query.addOrder("flowCode", true);
        Page<A19flow> page = DbUtils.selectPage(MA19FlwDef.class, query, A19flow.class);
        GetListOut<A19flow> output = new GetListOut<>();
        ValueUtils.populate(page, output);
        return output;
    }
}
