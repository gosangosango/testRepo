package kr.co.miracom.mes.inv.resource.simple.inv_lot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.mes.config.MesConst;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.model.InvLot;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.model.InvLotDetail;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.service.get_list.GetInvLotListIn;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.service.input.InputInvLotIn;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.service.input.InputInvLotOut;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.service.instore.InstoreInvLotIn;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.service.instore.InstoreInvLotOut;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.service.unload.UnloadInvLotIn;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.service.unload.UnloadInvLotOut;

/**
 * Setup Controller: InvLot
 * @author myjung.jung
 * @since 2018. 06. 21.
 */
@Api(value = "Creating, Retrieving, Updating and Deleting InvLots.")
@RequestMapping(path = MesConst.VER_PATH)
public interface InvLotController {
    static final String RESOURCE = "inv/invLots";

    @GetMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Get InvLot")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "select", value = "Field Names of InvLot for Select", allowMultiple = true),
                    @ApiImplicitParam(name = "unselect", value = "Field Names of InvLot for Unselect", allowMultiple = true) })
    InvLotDetail get(@PathVariable String id, SelectOptions options) throws Exception;

    // @PutMapping(path = RESOURCE + "/{id}")
    // @ApiOperation("Update InvLot")
    // @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
    // @ApiImplicitParam(name = "data", value = "InvLot Json", dataType = "InvLotDetail", required = true),
    // @ApiImplicitParam(name = "fieldName", value = "Field Names of InvLot for Update", allowMultiple = true) })
    // void put(@PathVariable String id, @RequestBody InvLotDetail data,
    // @RequestParam(required = false) String[] fieldName) throws Exception;

    @GetMapping(path = RESOURCE)
    @ApiOperation("Get InvLot List")
    GetListOut<InvLot> getList(GetInvLotListIn input) throws Exception;

    // @PostMapping(path = RESOURCE + "/save")
    // @ApiOperation("Save InvLot List")
    // @ApiImplicitParams({
    // @ApiImplicitParam(name = "list", value = "InvLot List", dataType = "InvLot", required = true, allowMultiple =
    // true),
    // @ApiImplicitParam(name = "fieldName", value = "Field Names of InvLot for Save", allowMultiple = true) })
    // void saveList(@RequestBody List<InvLot> list, @RequestParam(required = false) String[] fieldName) throws
    // Exception;

    // @DeleteMapping(path = RESOURCE)
    // @ApiOperation("Delete InvLot List")
    // @ApiImplicitParams({
    // @ApiImplicitParam(name = "list", value = "InvLot List", dataType = "InvLot", required = true, allowMultiple =
    // true) })
    // void deleteList(@RequestBody List<InvLot> list) throws Exception;

    @PostMapping(path = RESOURCE + "/{id}/instore")
    @ApiOperation("Instore Lot")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true) })
    InstoreInvLotOut instore(@PathVariable String id, @RequestBody InstoreInvLotIn input) throws Exception;
    
    @PostMapping(path = RESOURCE + "/{id}/input")
    @ApiOperation("input Inv Lot")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true) })
    InputInvLotOut input(@PathVariable String id, @RequestBody InputInvLotIn input) throws Exception;
    
    @PostMapping(path = RESOURCE + "/{id}/unload")
    @ApiOperation("unload Inv Lot")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true) })
    UnloadInvLotOut unload(@PathVariable String id, @RequestBody UnloadInvLotIn input) throws Exception;

}
