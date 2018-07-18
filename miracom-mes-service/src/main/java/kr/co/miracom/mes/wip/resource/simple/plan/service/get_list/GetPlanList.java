package kr.co.miracom.mes.wip.resource.simple.plan.service.get_list;

import kr.co.miracom.dbist.dml.Page;
import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.wip.model.MWipPlnDef;
import kr.co.miracom.mes.wip.resource.simple.plan.model.Plan;

/**
 * Get List Service: Plan
 * @author myjung.jung
 * @since 2018. 06. 19.
 */
public class GetPlanList {
    public GetListOut<Plan> getList(GetPlanListIn input) throws Exception {
        Query query = DbUtils.newQuery(input);
        query.addFilter("factoryCode", AuthUtils.getFactoryCode());
        DbUtils.addEquals(query, "planMonth", input.getPlanMonth());
        DbUtils.addContains(query, "matCode", input.getMatCode());
        DbUtils.addContains(query, "mat.matDesc", input.getMatDesc());
        // DbUtils.addEquals(query, "planQty", input.getPlanQty());
        // DbUtils.addContains(query, "createUserId", input.getCreateUserId());
        // DbUtils.addEquals(query, "createTime", input.getCreateTime());
        // DbUtils.addContains(query, "updateUserId", input.getUpdateUserId());
        // DbUtils.addEquals(query, "updateTime", input.getUpdateTime());
        query.addOrder("planMonth,matCode", true);
        Page<Plan> page = DbUtils.selectPage(MWipPlnDef.class, query, Plan.class);
        GetListOut<Plan> output = new GetListOut<>();
        ValueUtils.populate(page, output);
        return output;
    }
}
