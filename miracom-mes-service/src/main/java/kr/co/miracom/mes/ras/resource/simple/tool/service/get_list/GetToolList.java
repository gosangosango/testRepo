package kr.co.miracom.mes.ras.resource.simple.tool.service.get_list;

import kr.co.miracom.dbist.dml.Page;
import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.ras.model.MRasTolDef;
import kr.co.miracom.mes.ras.resource.simple.tool.model.Tool;

/**
 * Get List Service: Tool
 * @author hhk
 * @since 2018. 07. 03.
 */
public class GetToolList {
    public GetListOut<Tool> getList(GetToolListIn input) throws Exception {
        Query query = DbUtils.newQuery(input);
        query.addFilter("factoryCode", AuthUtils.getFactoryCode());
         DbUtils.addContains(query, "toolCode", input.getToolCode());
         DbUtils.addContains(query, "toolDesc", input.getToolDesc());
         DbUtils.addEquals(query, "toolType", input.getToolType());
         DbUtils.addEquals(query, "matCode", input.getMatCode());
        // DbUtils.addEquals(query, "oneTimeUsageQty", input.getOneTimeUsageQty());
        // DbUtils.addEquals(query, "usageQty", input.getUsageQty());
        // DbUtils.addEquals(query, "totalLifeQty", input.getTotalLifeQty());
        // DbUtils.addContains(query, "periodCode", input.getPeriodCode());
        // DbUtils.addContains(query, "createUserId", input.getCreateUserId());
        // DbUtils.addEquals(query, "createTime", input.getCreateTime());
        // DbUtils.addContains(query, "updateUserId", input.getUpdateUserId());
        // DbUtils.addEquals(query, "updateTime", input.getUpdateTime());
        query.addOrder("toolCode", true);
        Page<Tool> page = DbUtils.selectPage(MRasTolDef.class, query, Tool.class);
        GetListOut<Tool> output = new GetListOut<>();
        ValueUtils.populate(page, output);
        return output;
    }
}
