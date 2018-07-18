package kr.co.miracom.mes.wip.resource.complex.lot_opr.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import kr.co.miracom.mes.config.MesConst;
import kr.co.miracom.mes.wip.resource.complex.lot_opr.service.get.GetLotOprIn;
import kr.co.miracom.mes.wip.resource.complex.lot_opr.service.get.GetLotOprOut;
import kr.co.miracom.mes.wip.resource.complex.lot_opr.service.get_list.GetLotOprListIn;
import kr.co.miracom.mes.wip.resource.complex.lot_opr.service.get_list.GetLotOprListOut;
import kr.co.miracom.mes.wip.resource.complex.lot_opr.service.get_summary.GetLotOprSummaryIn;
import kr.co.miracom.mes.wip.resource.complex.lot_opr.service.get_summary.GetLotOprSummaryOut;
import kr.co.miracom.mes.wip.resource.complex.lot_opr.service.load_list.LoadLotOprListIn;
import kr.co.miracom.mes.wip.resource.complex.lot_opr.service.load_list.LoadLotOprListOut;
import kr.co.miracom.mes.wip.resource.complex.lot_opr.service.post.PostLotOprIn;
import kr.co.miracom.mes.wip.resource.complex.lot_opr.service.post.PostLotOprOut;
import kr.co.miracom.mes.wip.resource.complex.lot_opr.service.post_list.PostLotOprListIn;
import kr.co.miracom.mes.wip.resource.complex.lot_opr.service.post_list.PostLotOprListOut;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Service Controller: Lot_opr
 * @author gom
 * @since 2018. 06. 25.
 */
@Api(value = "Processing Lot_opr.")
@RequestMapping(path = MesConst.VER_PATH)
public interface LotOprController {
    static final String RESOURCE = "wip/lotOpr";

    @GetMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Get Lot_opr")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true) })
    GetLotOprOut get(@PathVariable String id, GetLotOprIn input) throws Exception;

    // @PostMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Post Lot_opr")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true) })
    PostLotOprOut post(@PathVariable String id, @RequestBody PostLotOprIn input) throws Exception;

    // @GetMapping(path = RESOURCE)
    @ApiOperation("Get Lot_opr")
    GetLotOprListOut getList(GetLotOprListIn input) throws Exception;

    // @PostMapping(path = RESOURCE)
    @ApiOperation("Post Lot_opr")
    PostLotOprListOut postList(@RequestBody PostLotOprListIn input) throws Exception;

    @GetMapping(path = RESOURCE + "/{id}/summary")
    @ApiOperation("Get Summary Lot_opr ")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true) })
    GetLotOprSummaryOut getSummary(@PathVariable String id, GetLotOprSummaryIn input) throws Exception;
    
    @GetMapping(path = RESOURCE + "/{id}/loadMatList")
    @ApiOperation("Get Lot_opr")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true) })
    LoadLotOprListOut loadList(@PathVariable String id, LoadLotOprListIn input) throws Exception;
    
}
