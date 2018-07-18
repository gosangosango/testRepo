package kr.co.miracom.mes.inv.resource.simple.inv_request.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.mes.config.MesConst;
import kr.co.miracom.mes.inv.resource.simple.inv_request.model.InvRequest;
import kr.co.miracom.mes.inv.resource.simple.inv_request.model.InvRequestDetail;
import kr.co.miracom.mes.inv.resource.simple.inv_request.model.InvRequestMat;
import kr.co.miracom.mes.inv.resource.simple.inv_request.service.get_list.GetInvRequestListIn;
import kr.co.miracom.mes.inv.resource.simple.inv_request.service.send.SendInvRequestInvLotIn;
import kr.co.miracom.mes.inv.resource.simple.inv_request.service.send.SendInvRequestInvLotOut;

/**
 * Setup Controller: InvRequest
 * @author User
 * @since 2018. 07. 03.
 */
@Api(value = "Creating, Retrieving, Updating and Deleting InvRequests.")
@RequestMapping(path = MesConst.VER_PATH)
public interface InvRequestController {
    static final String RESOURCE = "inv/invRequests";

    @GetMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Get InvRequest")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "select", value = "Field Names of InvRequest for Select", allowMultiple = true),
                    @ApiImplicitParam(name = "unselect", value = "Field Names of InvRequest for Unselect", allowMultiple = true) })
    InvRequestDetail get(@PathVariable String id, SelectOptions options) throws Exception;

    @PutMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Update InvRequest")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "data", value = "InvRequest Json", dataType = "InvRequestDetail", required = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of InvRequest for Update", allowMultiple = true) })
    void put(@PathVariable String id, @RequestBody InvRequestDetail data, @RequestParam(required = false) String[] fieldName) throws Exception;

    @GetMapping(path = RESOURCE)
    @ApiOperation("Get InvRequest List")
    GetListOut<InvRequest> getList(GetInvRequestListIn input) throws Exception;

    @PostMapping(path = RESOURCE + "/save")
    @ApiOperation("Save InvRequest List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "list", value = "InvRequest List", dataType = "InvRequest", required = true, allowMultiple = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of InvRequest for Save", allowMultiple = true) })
    void saveList(@RequestBody List<InvRequest> list, @RequestParam(required = false) String[] fieldName) throws Exception;

    @DeleteMapping(path = RESOURCE)
    @ApiOperation("Delete InvRequest List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "list", value = "InvRequest List", dataType = "InvRequest", required = true, allowMultiple = true) })
    void deleteList(@RequestBody List<InvRequest> list) throws Exception;

    @GetMapping(path = RESOURCE + "/{id}/mats")
    @ApiOperation("Get InvRequest Mat List")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID of InvRequest", required = true) })
    GetListOut<InvRequestMat> getMatList(@PathVariable String id) throws Exception;

    @PostMapping(path = RESOURCE + "/{id}/mats/save")
    @ApiOperation("Save InvRequest Mat List")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID of InvRequest", required = true),
                    @ApiImplicitParam(name = "list", value = "InvRequest Mat List", dataType = "InvRequestMat", allowMultiple = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of Mat for Save", allowMultiple = true) })
    void saveMatList(@PathVariable String id, @RequestBody List<InvRequestMat> list, @RequestParam(required = false) String[] fieldName) throws Exception;

    @PostMapping(path = RESOURCE + "/{id}/send")
    @ApiOperation("Send InvRequest Inv Lot List")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID of InvRequest", required = true) })
    SendInvRequestInvLotOut send(@PathVariable String id, @RequestBody SendInvRequestInvLotIn input) throws Exception;
}
