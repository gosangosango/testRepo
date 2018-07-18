package kr.co.miracom.mes.inv.resource.complex.inv_lot_shp.controller;

import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_shp.service.get.GetInvLotShp;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_shp.service.get.GetInvLotShpIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_shp.service.get.GetInvLotShpOut;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_shp.service.get_list.GetInvLotShpList;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_shp.service.get_list.GetInvLotShpListIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_shp.service.get_list.GetInvLotShpListOut;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_shp.service.post.PostInvLotShp;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_shp.service.post.PostInvLotShpIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_shp.service.post.PostInvLotShpOut;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_shp.service.post_list.PostInvLotShpList;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_shp.service.post_list.PostInvLotShpListIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_shp.service.post_list.PostInvLotShpListOut;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_shp.service.ship_order.ShipInvLotShpOrder;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_shp.service.ship_order.ShipInvLotShpOrderIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_shp.service.ship_order.ShipInvLotShpOrderOut;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_shp.service.ship_order_list.ShipInvLotShpOrderList;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_shp.service.ship_order_list.ShipInvLotShpOrderListIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_shp.service.ship_order_list.ShipInvLotShpOrderListOut;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Service Controller: InvLotShp
 * @author gom
 * @since 2018. 07. 10.
 */
@CrossOrigin
@RestController
public class InvLotShpControllerImpl implements InvLotShpController {

    @Override
    public GetInvLotShpOut get(@PathVariable String id, GetInvLotShpIn input) throws Exception {
        input.setId(id);
        return BeanUtils.get(GetInvLotShp.class).get(input);
    }

    @Override
    public PostInvLotShpOut post(@PathVariable String id, @RequestBody PostInvLotShpIn input) throws Exception {
        input.setId(id);
        return BeanUtils.get(PostInvLotShp.class).post(input);
    }

    @Override
    public GetInvLotShpListOut getList(GetInvLotShpListIn input) throws Exception {
        return BeanUtils.get(GetInvLotShpList.class).getList(input);
    }

    @Override
    public PostInvLotShpListOut postList(@RequestBody PostInvLotShpListIn input) throws Exception {
        return BeanUtils.get(PostInvLotShpList.class).postList(input);
    }

    @Override
    public ShipInvLotShpOrderListOut shipOrderList(@PathVariable String id, ShipInvLotShpOrderListIn input) throws Exception {
        input.setId(id);
        return BeanUtils.get(ShipInvLotShpOrderList.class).shipOrderList(input);
    }
    
    @Override
    public ShipInvLotShpOrderOut shipOrder(@PathVariable String id, ShipInvLotShpOrderIn input) throws Exception {
        input.setShipOrdNo(id);
        return BeanUtils.get(ShipInvLotShpOrder.class).shipOrder(input);
    }
}
