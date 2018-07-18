package kr.co.miracom.mes.inv.resource.complex.inv_lot_shp.controller;

import kr.co.miracom.mes.inv.resource.complex.inv_lot_shp.service.get.GetInvLotShpIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_shp.service.get.GetInvLotShpOut;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_shp.service.get_list.GetInvLotShpListIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_shp.service.get_list.GetInvLotShpListOut;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_shp.service.post.PostInvLotShpIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_shp.service.post.PostInvLotShpOut;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_shp.service.post_list.PostInvLotShpListIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_shp.service.post_list.PostInvLotShpListOut;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_shp.service.ship_order.ShipInvLotShpOrderIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_shp.service.ship_order.ShipInvLotShpOrderOut;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_shp.service.ship_order_list.ShipInvLotShpOrderListIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_shp.service.ship_order_list.ShipInvLotShpOrderListOut;

/**
 * Service Controller: InvLotShp
 * @author gom
 * @since 2018. 07. 10.
 */
public class InvLotShpControllerStub implements InvLotShpController {

    @Override
    public GetInvLotShpOut get(String id, GetInvLotShpIn input) throws Exception {
        return null;
    }

    @Override
    public PostInvLotShpOut post(String id, PostInvLotShpIn input) throws Exception {
        return null;
    }

    @Override
    public GetInvLotShpListOut getList(GetInvLotShpListIn input) throws Exception {
        return null;
    }

    @Override
    public PostInvLotShpListOut postList(PostInvLotShpListIn input) throws Exception {
        return null;
    }
    
    @Override
    public ShipInvLotShpOrderListOut shipOrderList(String id, ShipInvLotShpOrderListIn input) throws Exception {
        return null;
    }
    
    @Override
    public ShipInvLotShpOrderOut shipOrder(String id, ShipInvLotShpOrderIn input) throws Exception {
        return null;
    }
}
