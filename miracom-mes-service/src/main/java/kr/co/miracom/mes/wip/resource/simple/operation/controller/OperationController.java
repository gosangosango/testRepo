package kr.co.miracom.mes.wip.resource.simple.operation.controller;

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
import kr.co.miracom.mes.wip.resource.simple.operation.model.Operation;
import kr.co.miracom.mes.wip.resource.simple.operation.model.OperationDetail;
import kr.co.miracom.mes.wip.resource.simple.operation.model.OperationEquip;
import kr.co.miracom.mes.wip.resource.simple.operation.service.get_list.GetOperationListIn;

/**
 * Setup Controller: Operation
 * @author myjung.jung
 * @since 2018. 06. 05.
 */
@Api(value = "Creating, Retrieving, Updating and Deleting Operations.")
@RequestMapping(path = MesConst.VER_PATH)
public interface OperationController {
    static final String RESOURCE = "wip/operations";

    @GetMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Get Operation")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "select", value = "Field Names of Operation for Select", allowMultiple = true),
                    @ApiImplicitParam(name = "unselect", value = "Field Names of Operation for Unselect", allowMultiple = true) })
    OperationDetail get(@PathVariable String id, SelectOptions options) throws Exception;

    @PutMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Update Operation")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "data", value = "Operation Json", dataType = "OperationDetail", required = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of Operation for Update", allowMultiple = true) })
    void put(@PathVariable String id, @RequestBody OperationDetail data,
                    @RequestParam(required = false) String[] fieldName) throws Exception;

    @GetMapping(path = RESOURCE)
    @ApiOperation("Get Operation List")
    GetListOut<Operation> getList(GetOperationListIn input) throws Exception;

    @PostMapping(path = RESOURCE + "/save")
    @ApiOperation("Save Operation List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "list", value = "Operation List", dataType = "Operation", required = true, allowMultiple = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of Operation for Save", allowMultiple = true) })
    void saveList(@RequestBody List<Operation> list, @RequestParam(required = false) String[] fieldName)
                    throws Exception;

    @DeleteMapping(path = RESOURCE)
    @ApiOperation("Delete Operation List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "list", value = "Operation List", dataType = "Operation", required = true, allowMultiple = true) })
    void deleteList(@RequestBody List<Operation> list) throws Exception;

    @GetMapping(path = RESOURCE + "/{id}/equips")
    @ApiOperation("Get Operation Equip List")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID of Operation", required = true) })
    GetListOut<OperationEquip> getEquipList(@PathVariable String id) throws Exception;

    @PostMapping(path = RESOURCE + "/{id}/equips/save")
    @ApiOperation("Save Operation Equip List")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID of Operation", required = true),
                    @ApiImplicitParam(name = "list", value = "Operation Equip List", dataType = "OperationEquip", allowMultiple = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of Equip for Save", allowMultiple = true) })
    void saveEquipList(@PathVariable String id, @RequestBody List<OperationEquip> list,
                    @RequestParam(required = false) String[] fieldName) throws Exception;

}
