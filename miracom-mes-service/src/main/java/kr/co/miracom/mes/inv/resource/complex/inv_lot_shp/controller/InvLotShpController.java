package kr.co.miracom.mes.inv.resource.complex.inv_lot_shp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import kr.co.miracom.mes.config.MesConst;
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

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Service Controller: InvLotShp
 * @author gom
 * @since 2018. 07. 10.
 */
@Api(value = "Processing InvLotShp.")
@RequestMapping(path = MesConst.VER_PATH)
public interface InvLotShpController {
    static final String RESOURCE = "inv/invLotShp";

    @GetMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Get InvLotShp")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true) })
    GetInvLotShpOut get(@PathVariable String id, GetInvLotShpIn input) throws Exception;

    // @PostMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Post InvLotShp")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true) })
    PostInvLotShpOut post(@PathVariable String id, @RequestBody PostInvLotShpIn input) throws Exception;

    // @GetMapping(path = RESOURCE)
    @ApiOperation("Get InvLotShp")
    GetInvLotShpListOut getList(GetInvLotShpListIn input) throws Exception;

    // @PostMapping(path = RESOURCE)
    @ApiOperation("Post InvLotShp")
    PostInvLotShpListOut postList(@RequestBody PostInvLotShpListIn input) throws Exception;

    @GetMapping(path = RESOURCE + "/{id}/shipOrderList")
    @ApiOperation("Get Ship Order List InvLotShp")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true) })
    ShipInvLotShpOrderListOut shipOrderList(@PathVariable String id, ShipInvLotShpOrderListIn input) throws Exception;
    
    @GetMapping(path = RESOURCE + "/{id}/shipOrder")
    @ApiOperation("Get Ship Order InvLotShp")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true) })
    ShipInvLotShpOrderOut shipOrder(@PathVariable String id, ShipInvLotShpOrderIn input) throws Exception;
    
}
