package kr.co.miracom.mes.wip.resource.simple.component.controller;

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
import kr.co.miracom.mes.wip.resource.simple.component.model.Component;
import kr.co.miracom.mes.wip.resource.simple.component.model.ComponentAlterMat;
import kr.co.miracom.mes.wip.resource.simple.component.model.ComponentDetail;
import kr.co.miracom.mes.wip.resource.simple.component.model.ComponentOper;
import kr.co.miracom.mes.wip.resource.simple.component.service.get_list.GetComponentListIn;
import kr.co.miracom.mes.wip.resource.simple.component.service.get_tree.GetComponentTreeIn;
import kr.co.miracom.mes.wip.resource.simple.component.service.get_tree.GetComponentTreeOut;

/**
 * Setup Controller: Component
 * @author myjung.jung
 * @since 2018. 06. 26.
 */
@Api(value = "Creating, Retrieving, Updating and Deleting Components.")
@RequestMapping(path = MesConst.VER_PATH)
public interface ComponentController {
    static final String RESOURCE = "wip/components";

    @GetMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Get Component")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "select", value = "Field Names of Component for Select", allowMultiple = true),
                    @ApiImplicitParam(name = "unselect", value = "Field Names of Component for Unselect", allowMultiple = true) })
    ComponentDetail get(@PathVariable String id, SelectOptions options) throws Exception;

    @PutMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Update Component")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "data", value = "Component Json", dataType = "ComponentDetail", required = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of Component for Update", allowMultiple = true) })
    void put(@PathVariable String id, @RequestBody ComponentDetail data,
                    @RequestParam(required = false) String[] fieldName) throws Exception;

    @GetMapping(path = RESOURCE)
    @ApiOperation("Get Component List")
    GetListOut<Component> getList(GetComponentListIn input) throws Exception;

    @GetMapping(path = RESOURCE + "/tree")
    @ApiOperation("Get Component Tree")
    GetComponentTreeOut getTree(GetComponentTreeIn input) throws Exception;

    @PostMapping(path = RESOURCE + "/save")
    @ApiOperation("Save Component List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "list", value = "Component List", dataType = "Component", required = true, allowMultiple = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of Component for Save", allowMultiple = true) })
    void saveList(@RequestBody List<Component> list, @RequestParam(required = false) String[] fieldName)
                    throws Exception;

    @DeleteMapping(path = RESOURCE)
    @ApiOperation("Delete Component List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "list", value = "Component List", dataType = "Component", required = true, allowMultiple = true) })
    void deleteList(@RequestBody List<Component> list) throws Exception;

    @GetMapping(path = RESOURCE + "/{id}/opers")
    @ApiOperation("Get Component Oper List")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID of Component", required = true) })
    GetListOut<ComponentOper> getOperList(@PathVariable String id) throws Exception;

    @PostMapping(path = RESOURCE + "/{id}/opers/save")
    @ApiOperation("Save Component Oper List")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID of Component", required = true),
                    @ApiImplicitParam(name = "list", value = "Component Oper List", dataType = "ComponentOper", allowMultiple = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of Oper for Save", allowMultiple = true) })
    void saveOperList(@PathVariable String id, @RequestBody List<ComponentOper> list,
                    @RequestParam(required = false) String[] fieldName) throws Exception;

    @GetMapping(path = RESOURCE + "/{id}/alterMats")
    @ApiOperation("Get Material Alter Mat List")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "Material ID", required = true) })
    GetListOut<ComponentAlterMat> getAlterMatList(@PathVariable String id) throws Exception;

    @PostMapping(path = RESOURCE + "/{id}/alterMats/save")
    @ApiOperation("Save Material Alter Mat List")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "Material ID", required = true),
                    @ApiImplicitParam(name = "list", value = "Material Alter Mat List", dataType = "ComponentAlterMat", allowMultiple = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of AlterMat for Save", allowMultiple = true) })
    void saveAlterMatList(@PathVariable String id, @RequestBody List<ComponentAlterMat> list,
                    @RequestParam(required = false) String[] fieldName) throws Exception;

}
