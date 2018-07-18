package kr.co.miracom.mes.inv.resource.simple.inv_lot.service.get_list;

import kr.co.miracom.dbist.dml.Page;
import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.inv.model.MInvLotSts;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.model.InvLot;

/**
 * Get List Service: InvLot
 * @author myjung.jung
 * @since 2018. 06. 21.
 */
public class GetInvLotList {
    public GetListOut<InvLot> getList(GetInvLotListIn input) throws Exception {
        Query query = DbUtils.newQuery(input);
        query.addFilter("factoryCode", AuthUtils.getFactoryCode());
        if(input.isPositiveQty()) {
            query.addFilter("qty", ">",0);
        }
        DbUtils.addEquals(query, "invLotId", input.getInvLotId());
        DbUtils.addEquals(query, "operCode", input.getOperCode());
        DbUtils.addEquals(query, "matCode", input.getMatCode());
        // DbUtils.addEquals(query, "invFlag", input.getInvFlag());
//         DbUtils.addEquals(query, "qty", input.getQty());
        // DbUtils.addEquals(query, "inOperFlag", input.getInOperFlag());
         DbUtils.addContains(query, "inputOrderNo", input.getInputOrderNo());
//         DbUtils.addEquals(query, "qty", input.getQty());
        // DbUtils.addContains(query, "recvDate", input.getRecvDate());
        // DbUtils.addEquals(query, "recvQty", input.getRecvQty());
        // DbUtils.addContains(query, "lastTranCode", input.getLastTranCode());
        // DbUtils.addEquals(query, "lastTranTime", input.getLastTranTime());
        // DbUtils.addEquals(query, "holdFlag", input.getHoldFlag());
        // DbUtils.addContains(query, "holdCode", input.getHoldCode());
        // DbUtils.addContains(query, "fromToType", input.getFromToType());
        // DbUtils.addContains(query, "fromToLotId", input.getFromToLotId());
        // DbUtils.addContains(query, "vendorCode", input.getVendorCode());
        // DbUtils.addContains(query, "locNo", input.getLocNo());
        // DbUtils.addEquals(query, "lotDelFlag", input.getLotDelFlag());
        // DbUtils.addContains(query, "lotDelCode", input.getLotDelCode());
        // DbUtils.addEquals(query, "lotDelTime", input.getLotDelTime());
        // DbUtils.addContains(query, "createUserId", input.getCreateUserId());
        // DbUtils.addEquals(query, "createTime", input.getCreateTime());
        // DbUtils.addContains(query, "updateUserId", input.getUpdateUserId());
        // DbUtils.addEquals(query, "updateTime", input.getUpdateTime());
        query.addOrder("invLotId", true);
        Page<InvLot> page = DbUtils.selectPage(MInvLotSts.class, query, InvLot.class);
        GetListOut<InvLot> output = new GetListOut<>();
        ValueUtils.populate(page, output);
        return output;
    }
}
