package kr.co.miracom.mes.wip.resource.simple.lot.service.get_list;

import kr.co.miracom.dbist.dml.Page;
import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.wip.model.MWipLotSts;
import kr.co.miracom.mes.wip.resource.simple.lot.model.Lot;

/**
 * Get List Service: Lot
 * @author gom
 * @since 2018. 06. 21.
 */
public class GetLotList {
    public GetListOut<Lot> getList(GetLotListIn input) throws Exception {
        Query query = DbUtils.newQuery(input);
        query.addFilter("factoryCode", AuthUtils.getFactoryCode());
        // DbUtils.addContains(query, "lotId", input.getLotId());
        // DbUtils.addContains(query, "lotDesc", input.getLotDesc());
        // DbUtils.addContains(query, "lineCode", input.getLineCode());
        // DbUtils.addContains(query, "matCode", input.getMatCode());
        // DbUtils.addContains(query, "flowCode", input.getFlowCode());
        // DbUtils.addEquals(query, "flowSeqNo", input.getFlowSeqNo());
        // DbUtils.addContains(query, "operCode", input.getOperCode());
        // DbUtils.addContains(query, "orderNo", input.getOrderNo());
        // DbUtils.addContains(query, "lotType", input.getLotType());
        // DbUtils.addEquals(query, "createTime", input.getCreateTime());
        // DbUtils.addEquals(query, "createQty", input.getCreateQty());
        // DbUtils.addEquals(query, "qty", input.getQty());
        // DbUtils.addContains(query, "lotComment", input.getLotComment());
        // DbUtils.addContains(query, "lotStatus", input.getLotStatus());
        // DbUtils.addEquals(query, "holdFlag", input.getHoldFlag());
        // DbUtils.addContains(query, "holdCode", input.getHoldCode());
        // DbUtils.addEquals(query, "lotPriority", input.getLotPriority());
        // DbUtils.addEquals(query, "startFlag", input.getStartFlag());
        // DbUtils.addEquals(query, "startTime", input.getStartTime());
        // DbUtils.addContains(query, "startEquipCode", input.getStartEquipCode());
        // DbUtils.addEquals(query, "endFlag", input.getEndFlag());
        // DbUtils.addEquals(query, "endTime", input.getEndTime());
        // DbUtils.addContains(query, "endEquipCode", input.getEndEquipCode());
        // DbUtils.addContains(query, "fromToType", input.getFromToType());
        // DbUtils.addContains(query, "fromToLotId", input.getFromToLotId());
        // DbUtils.addContains(query, "shipCode", input.getShipCode());
        // DbUtils.addEquals(query, "shipTime", input.getShipTime());
        // DbUtils.addContains(query, "lastTranCode", input.getLastTranCode());
        // DbUtils.addEquals(query, "lastTranTime", input.getLastTranTime());
        // DbUtils.addEquals(query, "lastHistSeq", input.getLastHistSeq());
        // DbUtils.addEquals(query, "lastActiveHistSeq", input.getLastActiveHistSeq());
        // DbUtils.addEquals(query, "lotDelFlag", input.getLotDelFlag());
        // DbUtils.addContains(query, "lotDelCode", input.getLotDelCode());
        // DbUtils.addEquals(query, "lotDelTime", input.getLotDelTime());
        query.addOrder("lotId", true);
        Page<Lot> page = DbUtils.selectPage(MWipLotSts.class, query, Lot.class);
        GetListOut<Lot> output = new GetListOut<>();
        ValueUtils.populate(page, output);
        return output;
    }
}
