package kr.co.miracom.mes.ras.resource.simple.tool.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.mes.config.MesConst;
import kr.co.miracom.mes.ras.resource.simple.tool.model.Tool;
import kr.co.miracom.mes.ras.resource.simple.tool.model.ToolDetail;
import kr.co.miracom.mes.ras.resource.simple.tool.service.get_list.GetToolListIn;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Setup Controller: Tool
 * @author hhk
 * @since 2018. 07. 03.
 */
@Api(value = "Creating, Retrieving, Updating and Deleting Tools.")
@RequestMapping(path = MesConst.VER_PATH)
public interface ToolController {
    static final String RESOURCE = "ras/tools";

    @GetMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Get Tool")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "select", value = "Field Names of Tool for Select", allowMultiple = true),
                    @ApiImplicitParam(name = "unselect", value = "Field Names of Tool for Unselect", allowMultiple = true) })
    ToolDetail get(@PathVariable String id, SelectOptions options) throws Exception;

    @PutMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Update Tool")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "data", value = "Tool Json", dataType = "ToolDetail", required = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of Tool for Update", allowMultiple = true) })
    void put(@PathVariable String id, @RequestBody ToolDetail data, @RequestParam(required = false) String[] fieldName) throws Exception;

    @GetMapping(path = RESOURCE)
    @ApiOperation("Get Tool List")
    GetListOut<Tool> getList(GetToolListIn input) throws Exception;

    @PostMapping(path = RESOURCE + "/save")
    @ApiOperation("Save Tool List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "list", value = "Tool List", dataType = "Tool", required = true, allowMultiple = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of Tool for Save", allowMultiple = true) })
    void saveList(@RequestBody List<Tool> list, @RequestParam(required = false) String[] fieldName) throws Exception;

    @DeleteMapping(path = RESOURCE)
    @ApiOperation("Delete Tool List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "list", value = "Tool List", dataType = "Tool", required = true, allowMultiple = true) })
    void deleteList(@RequestBody List<Tool> list) throws Exception;

}
