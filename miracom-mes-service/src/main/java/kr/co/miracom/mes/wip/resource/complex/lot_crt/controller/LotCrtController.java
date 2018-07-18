package kr.co.miracom.mes.wip.resource.complex.lot_crt.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import kr.co.miracom.mes.config.MesConst;
import kr.co.miracom.mes.wip.resource.complex.lot_crt.service.get.GetLotCrtIn;
import kr.co.miracom.mes.wip.resource.complex.lot_crt.service.get.GetLotCrtOut;
import kr.co.miracom.mes.wip.resource.complex.lot_crt.service.get_list.GetLotCrtListIn;
import kr.co.miracom.mes.wip.resource.complex.lot_crt.service.get_list.GetLotCrtListOut;
import kr.co.miracom.mes.wip.resource.complex.lot_crt.service.post.PostLotCrtIn;
import kr.co.miracom.mes.wip.resource.complex.lot_crt.service.post.PostLotCrtOut;
import kr.co.miracom.mes.wip.resource.complex.lot_crt.service.post_list.PostLotCrtListIn;
import kr.co.miracom.mes.wip.resource.complex.lot_crt.service.post_list.PostLotCrtListOut;

/**
 * Service Controller: LotCrt
 * @author myjung.jung
 * @since 2018. 06. 25.
 */
@Api(value = "Processing LotCrt.")
@RequestMapping(path = MesConst.VER_PATH)
public interface LotCrtController {
    static final String RESOURCE = "wip/lotCrt";

    // @GetMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Get LotCrt")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true) })
    GetLotCrtOut get(@PathVariable String id, GetLotCrtIn input) throws Exception;

    @PostMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Post LotCrt")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true) })
    PostLotCrtOut post(@PathVariable String id, @RequestBody PostLotCrtIn input) throws Exception;

    // @GetMapping(path = RESOURCE)
    @ApiOperation("Get LotCrt")
    GetLotCrtListOut getList(GetLotCrtListIn input) throws Exception;

    // @PostMapping(path = RESOURCE)
    @ApiOperation("Post LotCrt")
    PostLotCrtListOut postList(@RequestBody PostLotCrtListIn input) throws Exception;

}
