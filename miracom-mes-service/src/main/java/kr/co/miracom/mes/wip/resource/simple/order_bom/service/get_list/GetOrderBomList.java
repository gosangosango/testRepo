package kr.co.miracom.mes.wip.resource.simple.order_bom.service.get_list;

import kr.co.miracom.dbist.dml.Page;
import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.wip.model.MWipOrdBom;
import kr.co.miracom.mes.wip.model.MWipOrdSts;
import kr.co.miracom.mes.wip.resource.simple.order_bom.model.OrderBom;

/**
 * Get List Service: OrderBom
 * @author myjung.jung
 * @since 2018. 06. 26.
 */
public class GetOrderBomList {
    public GetListOut<OrderBom> getList(GetOrderBomListIn input) throws Exception {
        if (ValueUtils.isEmpty(input.getOrderNo())) {
            ValueUtils.checkNotEmpty(input, "fromDate", "toDate");
        }

        Page<OrderBom> page;
        {
            Query query = DbUtils.newQuery(input);
            query.addFilter("factoryCode", AuthUtils.getFactoryCode());
            DbUtils.addContains(query, "orderNo", input.getOrderNo());
            // DbUtils.addContains(query, "orderDesc", input.getOrderDesc());
            // DbUtils.addContains(query, "lineCode", input.getLineCode());
            // DbUtils.addContains(query, "matCode", input.getMatCode());
            // DbUtils.addContains(query, "planMonth", input.getPlanMonth());
            // DbUtils.addContains(query, "flowCode", input.getFlowCode());
            // DbUtils.addEquals(query, "ordQty", input.getOrdQty());
            // DbUtils.addEquals(query, "ordInQty", input.getOrdInQty());
            // DbUtils.addEquals(query, "ordOutQty", input.getOrdOutQty());
            // DbUtils.addEquals(query, "rcvGoodQty", input.getRcvGoodQty());
            // DbUtils.addEquals(query, "rcvLossQty", input.getRcvLossQty());
            // DbUtils.addContains(query, "ordDate", input.getOrdDate());
            DbUtils.addFilter(query, "ordDate", ">=", input.getFromDate());
            DbUtils.addFilter(query, "ordDate", "<=", input.getToDate());
            // DbUtils.addContains(query, "ordStatus", input.getOrdStatus());
            // DbUtils.addContains(query, "ordPriority", input.getOrdPriority());
            // DbUtils.addContains(query, "ordType", input.getOrdType());
            // DbUtils.addEquals(query, "ordStartTime", input.getOrdStartTime());
            // DbUtils.addEquals(query, "ordEndTime", input.getOrdEndTime());
            // DbUtils.addContains(query, "ordComment", input.getOrdComment());
            // DbUtils.addContains(query, "createUserId", input.getCreateUserId());
            // DbUtils.addEquals(query, "createTime", input.getCreateTime());
            // DbUtils.addContains(query, "updateUserId", input.getUpdateUserId());
            // DbUtils.addEquals(query, "updateTime", input.getUpdateTime());
            query.addOrder("orderNo", true);
            page = DbUtils.selectPage(MWipOrdSts.class, query, OrderBom.class);
        }
        GetListOut<OrderBom> output = new GetListOut<>();
        ValueUtils.populate(page, output);
        for (OrderBom item : output.getList()) {
            Query query = new Query(0, 1);
            query.addFilter("factoryCode", AuthUtils.getFactoryCode());
            query.addFilter("orderNo", item.getOrderNo());
            MWipOrdBom comp = DbUtils.select(MWipOrdBom.class, query);
            if (comp != null) {
                item.setApplyDate(comp.getApplyDate());
            }
        }
        return output;
    }
}
