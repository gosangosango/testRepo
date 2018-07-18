package kr.co.miracom.mes.wip.resource.complex.lot_req_ins.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import kr.co.miracom.mes.config.MesConst;
import kr.co.miracom.mes.wip.resource.complex.lot_req_ins.service.get.GetLotReqInsIn;
import kr.co.miracom.mes.wip.resource.complex.lot_req_ins.service.get.GetLotReqInsOut;
import kr.co.miracom.mes.wip.resource.complex.lot_req_ins.service.get_list.GetLotReqInsListIn;
import kr.co.miracom.mes.wip.resource.complex.lot_req_ins.service.get_list.GetLotReqInsListOut;
import kr.co.miracom.mes.wip.resource.complex.lot_req_ins.service.post.PostLotReqInsIn;
import kr.co.miracom.mes.wip.resource.complex.lot_req_ins.service.post.PostLotReqInsOut;
import kr.co.miracom.mes.wip.resource.complex.lot_req_ins.service.post_list.PostLotReqInsListIn;
import kr.co.miracom.mes.wip.resource.complex.lot_req_ins.service.post_list.PostLotReqInsListOut;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Service Controller: LotReqIns
 * @author gom
 * @since 2018. 07. 05.
 */
@Api(value = "Processing LotReqIns.")
@RequestMapping(path = MesConst.VER_PATH)
public interface LotReqInsController {
    static final String RESOURCE = "wip/lotReqIns";

    @GetMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Get LotReqIns")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true) })
    GetLotReqInsOut get(@PathVariable String id, GetLotReqInsIn input) throws Exception;

    @PostMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Post LotReqIns")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true) })
    PostLotReqInsOut post(@PathVariable String id, @RequestBody PostLotReqInsIn input) throws Exception;

    // @GetMapping(path = RESOURCE)
    @ApiOperation("Get LotReqIns")
    GetLotReqInsListOut getList(GetLotReqInsListIn input) throws Exception;

    // @PostMapping(path = RESOURCE)
    @ApiOperation("Post LotReqIns")
    PostLotReqInsListOut postList(@RequestBody PostLotReqInsListIn input) throws Exception;

}
