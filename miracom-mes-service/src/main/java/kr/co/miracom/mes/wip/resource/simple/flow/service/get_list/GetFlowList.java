package kr.co.miracom.mes.wip.resource.simple.flow.service.get_list;

import kr.co.miracom.dbist.dml.Page;
import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.wip.model.MWipFlwDef;
import kr.co.miracom.mes.wip.resource.simple.flow.model.Flow;

/**
 * Get List Service: Flow
 * @author myjung.jung
 * @since 2018. 06. 05.
 */
public class GetFlowList {
    public GetListOut<Flow> getList(GetFlowListIn input) throws Exception {
        Query query = DbUtils.newQuery(input);
        query.addFilter("factoryCode", AuthUtils.getFactoryCode());
        DbUtils.addContains(query, "flowCode", input.getFlowCode());
        DbUtils.addContains(query, "flowDesc", input.getFlowDesc());
        // DbUtils.addContains(query, "firstOperCode", input.getFirstOperCode());
        // DbUtils.addContains(query, "lastOperCode", input.getLastOperCode());
        // DbUtils.addContains(query, "createUserId", input.getCreateUserId());
        // DbUtils.addEquas(query, "createTime", input.getCreateTime());
        // DbUtils.addContains(query, "updateUserId", input.getUpdateUserId());
        // DbUtils.addEquas(query, "updateTime", input.getUpdateTime());
        query.addOrder("flowCode", true);
        Page<Flow> page = DbUtils.selectPage(MWipFlwDef.class, query, Flow.class);
        GetListOut<Flow> output = new GetListOut<>();
        ValueUtils.populate(page, output);
        return output;
    }
}
