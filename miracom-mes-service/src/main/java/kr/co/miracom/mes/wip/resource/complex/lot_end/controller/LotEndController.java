package kr.co.miracom.mes.wip.resource.complex.lot_end.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import kr.co.miracom.mes.config.MesConst;
import kr.co.miracom.mes.wip.resource.complex.lot_end.service.deduct_inv.DeductLotEndInvIn;
import kr.co.miracom.mes.wip.resource.complex.lot_end.service.deduct_inv.DeductLotEndInvOut;
import kr.co.miracom.mes.wip.resource.complex.lot_end.service.get.GetLotEndIn;
import kr.co.miracom.mes.wip.resource.complex.lot_end.service.get.GetLotEndOut;
import kr.co.miracom.mes.wip.resource.complex.lot_end.service.get_list.GetLotEndListIn;
import kr.co.miracom.mes.wip.resource.complex.lot_end.service.get_list.GetLotEndListOut;
import kr.co.miracom.mes.wip.resource.complex.lot_end.service.post.PostLotEndIn;
import kr.co.miracom.mes.wip.resource.complex.lot_end.service.post.PostLotEndOut;
import kr.co.miracom.mes.wip.resource.complex.lot_end.service.post_list.PostLotEndListIn;
import kr.co.miracom.mes.wip.resource.complex.lot_end.service.post_list.PostLotEndListOut;
import kr.co.miracom.mes.wip.resource.complex.lot_end.service.valid_inv.ValidLotEndInvIn;
import kr.co.miracom.mes.wip.resource.complex.lot_end.service.valid_inv.ValidLotEndInvOut;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Service Controller: LotEnd
 * @author gom
 * @since 2018. 06. 25.
 */
@Api(value = "Processing LotEnd.")
@RequestMapping(path = MesConst.VER_PATH)
public interface LotEndController {
    static final String RESOURCE = "wip/lotEnd";

    @GetMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Get LotEnd")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true) })
    GetLotEndOut get(@PathVariable String id, GetLotEndIn input) throws Exception;

    @PostMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Post LotEnd")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true) })
    PostLotEndOut post(@PathVariable String id, @RequestBody PostLotEndIn input) throws Exception;

    //@GetMapping(path = RESOURCE)
    @ApiOperation("Get LotEnd")
    GetLotEndListOut getList(GetLotEndListIn input) throws Exception;

    // @PostMapping(path = RESOURCE)
    @ApiOperation("Post LotEnd")
    PostLotEndListOut postList(@RequestBody PostLotEndListIn input) throws Exception;

    @GetMapping(path = RESOURCE + "/{id}/validLot")
    @ApiOperation("Valid InvLot LotEnd")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true) })
    ValidLotEndInvOut validInv(@PathVariable String id, ValidLotEndInvIn input) throws Exception;
    
    @PostMapping(path = RESOURCE + "/{id}/deductLot")
    @ApiOperation("Material Deduction LotEnd")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true) })
    DeductLotEndInvOut deductInv(@PathVariable String id, @RequestBody DeductLotEndInvIn input) throws Exception;
    
}
