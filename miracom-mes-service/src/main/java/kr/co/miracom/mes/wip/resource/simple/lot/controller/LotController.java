package kr.co.miracom.mes.wip.resource.simple.lot.controller;

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
import kr.co.miracom.mes.wip.resource.simple.lot.model.Lot;
import kr.co.miracom.mes.wip.resource.simple.lot.model.LotDetail;
import kr.co.miracom.mes.wip.resource.simple.lot.service.create.CreateLotIn;
import kr.co.miracom.mes.wip.resource.simple.lot.service.create.CreateLotOut;
import kr.co.miracom.mes.wip.resource.simple.lot.service.end.EndLotIn;
import kr.co.miracom.mes.wip.resource.simple.lot.service.end.EndLotOut;
import kr.co.miracom.mes.wip.resource.simple.lot.service.get_list.GetLotListIn;
import kr.co.miracom.mes.wip.resource.simple.lot.service.pack.PackLotIn;
import kr.co.miracom.mes.wip.resource.simple.lot.service.pack.PackLotOut;
import kr.co.miracom.mes.wip.resource.simple.lot.service.request_inspection.RequestInspectionLotIn;
import kr.co.miracom.mes.wip.resource.simple.lot.service.request_inspection.RequestInspectionLotOut;

/**
 * Setup Controller: Lot
 * @author gom
 * @since 2018. 06. 21.
 */
@Api(value = "Creating, Retrieving, Updating and Deleting Lots.")
@RequestMapping(path = MesConst.VER_PATH)
public interface LotController {
    static final String RESOURCE = "wip/lots";

    @GetMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Get Lot")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "select", value = "Field Names of Lot for Select", allowMultiple = true),
                    @ApiImplicitParam(name = "unselect", value = "Field Names of Lot for Unselect", allowMultiple = true) })
    LotDetail get(@PathVariable String id, SelectOptions options) throws Exception;

    @PutMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Update Lot")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "data", value = "Lot Json", dataType = "LotDetail", required = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of Lot for Update", allowMultiple = true) })
    void put(@PathVariable String id, @RequestBody LotDetail data, @RequestParam(required = false) String[] fieldName)
                    throws Exception;

    @GetMapping(path = RESOURCE)
    @ApiOperation("Get Lot List")
    GetListOut<Lot> getList(GetLotListIn input) throws Exception;

    @PostMapping(path = RESOURCE + "/save")
    @ApiOperation("Save Lot List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "list", value = "Lot List", dataType = "Lot", required = true, allowMultiple = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of Lot for Save", allowMultiple = true) })
    void saveList(@RequestBody List<Lot> list, @RequestParam(required = false) String[] fieldName) throws Exception;

    @DeleteMapping(path = RESOURCE)
    @ApiOperation("Delete Lot List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "list", value = "Lot List", dataType = "Lot", required = true, allowMultiple = true) })
    void deleteList(@RequestBody List<Lot> list) throws Exception;

    @PostMapping(path = RESOURCE + "/{id}/create")
    @ApiOperation("Lot End")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true) })
    CreateLotOut create(@PathVariable String id, @RequestBody CreateLotIn input) throws Exception;

    @PostMapping(path = RESOURCE + "/{id}/end")
    @ApiOperation("Lot End")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true) })
    EndLotOut end(@PathVariable String id, @RequestBody EndLotIn input) throws Exception;
    
    @PostMapping(path = RESOURCE + "/{id}/pack")
    @ApiOperation("Lot End")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true) })
    PackLotOut pack(@PathVariable String id, @RequestBody PackLotIn input) throws Exception;
    
    @PostMapping(path = RESOURCE + "/{id}/requestInpection")
    @ApiOperation("Lot End")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true) })
    RequestInspectionLotOut requestInspection(@PathVariable String id, @RequestBody RequestInspectionLotIn input) throws Exception;

}
