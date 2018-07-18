package kr.co.miracom.mes.a19.resource.simple.a19operation.service.get_list;

import kr.co.miracom.dbist.dml.Page;
import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.a19.model.MA19OprDef;
import kr.co.miracom.mes.a19.resource.simple.a19operation.model.A19operation;

/**
 * Get List Service: A19operation
 * @author Zotac023
 * @since 2018. 07. 17.
 */
public class GetA19operationList {
    public GetListOut<A19operation> getList(GetA19operationListIn input) throws Exception {
        Query query = DbUtils.newQuery(input);
        // DbUtils.addContains(query, "operCode", input.getOperCode());
        // DbUtils.addContains(query, "operDesc", input.getOperDesc());
        // DbUtils.addContains(query, "createUserId", input.getCreateUserId());
        // DbUtils.addEquals(query, "createTime", input.getCreateTime());
        // DbUtils.addContains(query, "UpdateUserId", input.getUpdateUserId());
        // DbUtils.addEquals(query, "UpdateTime", input.getUpdateTime());
        query.addOrder("operCode", true);
        Page<A19operation> page = DbUtils.selectPage(MA19OprDef.class, query, A19operation.class);
        GetListOut<A19operation> output = new GetListOut<>();
        ValueUtils.populate(page, output);
        return output;
    }
}
