package kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import kr.co.miracom.mes.config.MesConst;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.backflush_list.BackflushInvLotInpListIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.backflush_list.BackflushInvLotInpListOut;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.get.GetInvLotInpIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.get.GetInvLotInpOut;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.get_list.GetInvLotInpListIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.get_list.GetInvLotInpListOut;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.load_list.LoadInvLotInpListIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.load_list.LoadInvLotInpListOut;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.post.PostInvLotInpIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.post.PostInvLotInpOut;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.post_list.PostInvLotInpListIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.post_list.PostInvLotInpListOut;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.unload.UnloadInvLotInpIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.unload.UnloadInvLotInpOut;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.valid_lot.ValidInvLotInpLotIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.valid_lot.ValidInvLotInpLotOut;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Service Controller: InvLotInp
 * @author gom
 * @since 2018. 07. 04.
 */
@Api(value = "Processing InvLotInp")
@RequestMapping(path = MesConst.VER_PATH)
public interface InvLotInpController {
    static final String RESOURCE = "inv/invLotInp";

    // @GetMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Get InvLotInp")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true) })
    GetInvLotInpOut get(@PathVariable String id, GetInvLotInpIn input) throws Exception;

    @PostMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Post InvLotInp")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true) })
    PostInvLotInpOut post(@PathVariable String id, @RequestBody PostInvLotInpIn input) throws Exception;

    // @GetMapping(path = RESOURCE)
    @ApiOperation("Get InvLotInp")
    GetInvLotInpListOut getList(GetInvLotInpListIn input) throws Exception;

    // @PostMapping(path = RESOURCE)
    @ApiOperation("Post InvLotInp")
    PostInvLotInpListOut postList(@RequestBody PostInvLotInpListIn input) throws Exception;

    @GetMapping(path = RESOURCE + "/{id}/loadList")
    @ApiOperation("Get loadList InvLotInp")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true) })
    LoadInvLotInpListOut loadList(@PathVariable String id, LoadInvLotInpListIn input) throws Exception;
    
    @GetMapping(path = RESOURCE + "/{id}/backflushList")
    @ApiOperation("Get backflush List InvLotInp")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true) })
    BackflushInvLotInpListOut backflushList(@PathVariable String id, BackflushInvLotInpListIn input) throws Exception;
    
    @GetMapping(path = RESOURCE + "/{id}/validLot")
    @ApiOperation("Get validLot List InvLotInp")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true) })
    ValidInvLotInpLotOut validLot(@PathVariable String id, ValidInvLotInpLotIn input) throws Exception;
    
    @PostMapping(path = RESOURCE + "/{id}/unload")
    @ApiOperation("unload InvLotInp")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true) })
    UnloadInvLotInpOut unload(@PathVariable String id, @RequestBody UnloadInvLotInpIn input) throws Exception;
    
}
