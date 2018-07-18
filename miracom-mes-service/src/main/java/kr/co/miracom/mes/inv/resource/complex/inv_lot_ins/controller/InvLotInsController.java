package kr.co.miracom.mes.inv.resource.complex.inv_lot_ins.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import kr.co.miracom.mes.config.MesConst;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_ins.service.get.GetInvLotInsIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_ins.service.get.GetInvLotInsOut;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_ins.service.get_list.GetInvLotInsListIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_ins.service.get_list.GetInvLotInsListOut;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_ins.service.post.PostInvLotInsIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_ins.service.post.PostInvLotInsOut;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_ins.service.post_list.PostInvLotInsListIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_ins.service.post_list.PostInvLotInsListOut;

/**
 * Service Controller: InvLotIns
 * @author myjung.jung
 * @since 2018. 06. 25.
 */
@Api(value = "Processing InvLotIns.")
@RequestMapping(path = MesConst.VER_PATH)
public interface InvLotInsController {
    static final String RESOURCE = "inv/invLotIns";

    @GetMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Get InvLotIns")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true) })
    GetInvLotInsOut get(@PathVariable String id, GetInvLotInsIn input) throws Exception;

    @PostMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Post InvLotIns")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true) })
    PostInvLotInsOut post(@PathVariable String id, @RequestBody PostInvLotInsIn input) throws Exception;

    // @GetMapping(path = RESOURCE)
    @ApiOperation("Get InvLotIns")
    GetInvLotInsListOut getList(GetInvLotInsListIn input) throws Exception;

    // @PostMapping(path = RESOURCE)
    @ApiOperation("Post InvLotIns")
    PostInvLotInsListOut postList(@RequestBody PostInvLotInsListIn input) throws Exception;

}
