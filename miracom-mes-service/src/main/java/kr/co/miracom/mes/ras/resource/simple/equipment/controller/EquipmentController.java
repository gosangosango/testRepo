package kr.co.miracom.mes.ras.resource.simple.equipment.controller;

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
import kr.co.miracom.mes.ras.resource.simple.equipment.model.Equipment;
import kr.co.miracom.mes.ras.resource.simple.equipment.model.EquipmentDetail;
import kr.co.miracom.mes.ras.resource.simple.equipment.model.EquipmentOper;
import kr.co.miracom.mes.ras.resource.simple.equipment.service.get_list.GetEquipmentListIn;

/**
 * Setup Controller: Equipment
 * @author myjung.jung
 * @since 2018. 06. 11.
 */
@Api(value = "Creating, Retrieving, Updating and Deleting Equipments.")
@RequestMapping(path = MesConst.VER_PATH)
public interface EquipmentController {
    static final String RESOURCE = "ras/equipments";

    @GetMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Get Equipment")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "select", value = "Field Names of Equipment for Select", allowMultiple = true),
                    @ApiImplicitParam(name = "unselect", value = "Field Names of Equipment for Unselect", allowMultiple = true) })
    EquipmentDetail get(@PathVariable String id, SelectOptions options) throws Exception;

    @PutMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Update Equipment")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "data", value = "Equipment Json", dataType = "EquipmentDetail", required = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of Equipment for Update", allowMultiple = true) })
    void put(@PathVariable String id, @RequestBody EquipmentDetail data,
                    @RequestParam(required = false) String[] fieldName) throws Exception;

    @GetMapping(path = RESOURCE)
    @ApiOperation("Get Equipment List")
    GetListOut<Equipment> getList(GetEquipmentListIn input) throws Exception;

    @PostMapping(path = RESOURCE + "/save")
    @ApiOperation("Save Equipment List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "list", value = "Equipment List", dataType = "Equipment", required = true, allowMultiple = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of Equipment for Save", allowMultiple = true) })
    void saveList(@RequestBody List<Equipment> list, @RequestParam(required = false) String[] fieldName)
                    throws Exception;

    @DeleteMapping(path = RESOURCE)
    @ApiOperation("Delete Equipment List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "list", value = "Equipment List", dataType = "Equipment", required = true, allowMultiple = true) })
    void deleteList(@RequestBody List<Equipment> list) throws Exception;

    @GetMapping(path = RESOURCE + "/{id}/opers")
    @ApiOperation("Get Equipment Oper List")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID of Equipment", required = true) })
    GetListOut<EquipmentOper> getOperList(@PathVariable String id) throws Exception;

    @PostMapping(path = RESOURCE + "/{id}/opers/save")
    @ApiOperation("Save Equipment Oper List")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID of Equipment", required = true),
                    @ApiImplicitParam(name = "list", value = "Equipment Oper List", dataType = "EquipmentOper", allowMultiple = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of Oper for Save", allowMultiple = true) })
    void saveOperList(@PathVariable String id, @RequestBody List<EquipmentOper> list,
                    @RequestParam(required = false) String[] fieldName) throws Exception;

}
