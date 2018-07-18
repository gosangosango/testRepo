package kr.co.miracom.mes.wip.resource.simple.order.service.get_list;

import kr.co.miracom.dbist.dml.Page;
import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.wip.model.MWipOrdSts;
import kr.co.miracom.mes.wip.resource.simple.order.model.Order;

/**
 * Get List Service: Order
 * @author myjung.jung
 * @since 2018. 06. 12.
 */
public class GetOrderList {
    public GetListOut<Order> getList(GetOrderListIn input) throws Exception {
        Query query = DbUtils.newQuery(input);
        query.addFilter("factoryCode", AuthUtils.getFactoryCode());
        DbUtils.addContains(query, "orderNo", input.getOrderNo());
        // DbUtils.addContains(query, "orderDesc", input.getOrderDesc());
        DbUtils.addEquals(query, "lineCode", input.getLineCode());
        DbUtils.addEquals(query, "matCode", input.getMatCode());
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
        DbUtils.addEquals(query, "ordStatus", input.getOrdStatus());
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
        Page<Order> page = DbUtils.selectPage(MWipOrdSts.class, query, Order.class);
        GetListOut<Order> output = new GetListOut<>();
        ValueUtils.populate(page, output);
        return output;
    }
}
