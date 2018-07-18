package kr.co.miracom.mes.wip.resource.simple.component.service.get_list;

import kr.co.miracom.dbist.dml.Page;
import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.wip.model.MWipBomDef;
import kr.co.miracom.mes.wip.resource.simple.component.model.Component;

/**
 * Get List Service: Component
 * @author myjung.jung
 * @since 2018. 06. 26.
 */
public class GetComponentList {
    public GetListOut<Component> getList(GetComponentListIn input) throws Exception {
        ValueUtils.checkNotEmpty(input, "matCode");

        Query query = DbUtils.newQuery(input);
        query.addFilter("factoryCode", AuthUtils.getFactoryCode());
        DbUtils.addEquals(query, "matCode", input.getMatCode());
        // DbUtils.addContains(query, "childMatCode", input.getChildMatCode());
        // DbUtils.addEquals(query, "seqNo", input.getSeqNo());
        // DbUtils.addEquals(query, "componentQty", input.getComponentQty());
        // DbUtils.addContains(query, "applyStartDate", input.getApplyStartDate());
        // DbUtils.addContains(query, "applyEndDate", input.getApplyEndDate());
        if (input.isApplyOnlyFlag()) {
            String date = ValueUtils.toDateStr(ValueUtils.getDate(), "yyyyMMddHH");
            query.addFilter("applyStartDate", "<=", date);
            query.addFilter("applyEndDate", ">=", date);
        }
        // DbUtils.addContains(query, "createUserId", input.getCreateUserId());
        // DbUtils.addEquals(query, "createTime", input.getCreateTime());
        // DbUtils.addContains(query, "updateUserId", input.getUpdateUserId());
        // DbUtils.addEquals(query, "updateTime", input.getUpdateTime());
        query.addOrder("childMatCode", true);
        Page<Component> page = DbUtils.selectPage(MWipBomDef.class, query, Component.class);
        GetListOut<Component> output = new GetListOut<>();
        ValueUtils.populate(page, output);
        return output;
    }
}
