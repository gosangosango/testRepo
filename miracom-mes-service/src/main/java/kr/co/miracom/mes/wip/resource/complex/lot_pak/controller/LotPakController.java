package kr.co.miracom.mes.wip.resource.complex.lot_pak.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import kr.co.miracom.mes.config.MesConst;
import kr.co.miracom.mes.wip.resource.complex.lot_pak.service.get.GetLotPakIn;
import kr.co.miracom.mes.wip.resource.complex.lot_pak.service.get.GetLotPakOut;
import kr.co.miracom.mes.wip.resource.complex.lot_pak.service.get_list.GetLotPakListIn;
import kr.co.miracom.mes.wip.resource.complex.lot_pak.service.get_list.GetLotPakListOut;
import kr.co.miracom.mes.wip.resource.complex.lot_pak.service.label_list.LabelLotPakListIn;
import kr.co.miracom.mes.wip.resource.complex.lot_pak.service.label_list.LabelLotPakListOut;
import kr.co.miracom.mes.wip.resource.complex.lot_pak.service.pack_qty.PackLotPakQtyIn;
import kr.co.miracom.mes.wip.resource.complex.lot_pak.service.pack_qty.PackLotPakQtyOut;
import kr.co.miracom.mes.wip.resource.complex.lot_pak.service.post.PostLotPakIn;
import kr.co.miracom.mes.wip.resource.complex.lot_pak.service.post.PostLotPakOut;
import kr.co.miracom.mes.wip.resource.complex.lot_pak.service.post_list.PostLotPakListIn;
import kr.co.miracom.mes.wip.resource.complex.lot_pak.service.post_list.PostLotPakListOut;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Service Controller: LotPak
 * @author gom
 * @since 2018. 07. 02.
 */
@Api(value = "Processing LotPak.")
@RequestMapping(path = MesConst.VER_PATH)
public interface LotPakController {
    static final String RESOURCE = "wip/lotPak";

    @GetMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Get LotPak")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true) })
    GetLotPakOut get(@PathVariable String id, GetLotPakIn input) throws Exception;

    @PostMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Post LotPak")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true) })
    PostLotPakOut post(@PathVariable String id, @RequestBody PostLotPakIn input) throws Exception;

    // @GetMapping(path = RESOURCE)
    @ApiOperation("Get LotPak")
    GetLotPakListOut getList(GetLotPakListIn input) throws Exception;

    // @PostMapping(path = RESOURCE)
    @ApiOperation("Post LotPak")
    PostLotPakListOut postList(@RequestBody PostLotPakListIn input) throws Exception;

    @GetMapping(path = RESOURCE + "/{id}/packQty")
    @ApiOperation("Get Pack Qty LotPak")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true) })
    PackLotPakQtyOut packQty(@PathVariable String id, PackLotPakQtyIn input) throws Exception;

    @GetMapping(path = RESOURCE + "/{id}/labelList")
    @ApiOperation("Get LabelList LotPak")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true) })
    LabelLotPakListOut labelList(@PathVariable String id, LabelLotPakListIn input) throws Exception;
}
