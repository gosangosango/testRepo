package kr.co.miracom.mes.a19.resource.simple.a19lot.service.get_list;

import kr.co.miracom.dbist.dml.Page;
import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.a19.resource.simple.a19lot.model.A19lot;
import kr.co.miracom.mes.wip.model.MWipLotSts;

/**
 * Get List Service: A19lot
 * @author Zotac023
 * @since 2018. 07. 18.
 */
public class GetA19lotList {
    public GetListOut<A19lot> getList(GetA19lotListIn input) throws Exception {
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
        // DbUtils.addEquals(query, "lotType", input.getLotType());
        // DbUtils.addEquals(query, "createTime", input.getCreateTime());
        // DbUtils.addEquals(query, "createQty", input.getCreateQty());
        // DbUtils.addEquals(query, "qty", input.getQty());
        // DbUtils.addContains(query, "lotComment", input.getLotComment());
        // DbUtils.addEquals(query, "lotStatus", input.getLotStatus());
        // DbUtils.addEqualsIfTrue(query, "holdFlag", input.isHoldFlag());
        // DbUtils.addContains(query, "holdCode", input.getHoldCode());
        // DbUtils.addEquals(query, "lotPriority", input.getLotPriority());
        // DbUtils.addEqualsIfTrue(query, "startFlag", input.isStartFlag());
        // DbUtils.addEquals(query, "startTime", input.getStartTime());
        // DbUtils.addContains(query, "startEquipCode", input.getStartEquipCode());
        // DbUtils.addEqualsIfTrue(query, "endFlag", input.isEndFlag());
        // DbUtils.addEquals(query, "endTime", input.getEndTime());
        // DbUtils.addContains(query, "endEquipCode", input.getEndEquipCode());
        // DbUtils.addEquals(query, "fromToType", input.getFromToType());
        // DbUtils.addContains(query, "fromToLotId", input.getFromToLotId());
        // DbUtils.addContains(query, "shipCode", input.getShipCode());
        // DbUtils.addEquals(query, "shipTime", input.getShipTime());
        // DbUtils.addContains(query, "lastTranCode", input.getLastTranCode());
        // DbUtils.addEquals(query, "lastTranTime", input.getLastTranTime());
        // DbUtils.addEquals(query, "lastHistSeq", input.getLastHistSeq());
        // DbUtils.addEqualsIfTrue(query, "lotDelFlag", input.isLotDelFlag());
        // DbUtils.addContains(query, "lotDelCode", input.getLotDelCode());
        // DbUtils.addEquals(query, "lotDelTime", input.getLotDelTime());
        // DbUtils.addEqualsIfTrue(query, "prodCompleteFlag", input.isProdCompleteFlag());
        query.addOrder("lotId", true);
        Page<A19lot> page = DbUtils.selectPage(MWipLotSts.class, query, A19lot.class);
        GetListOut<A19lot> output = new GetListOut<>();
        ValueUtils.populate(page, output);
        return output;
    }
}
