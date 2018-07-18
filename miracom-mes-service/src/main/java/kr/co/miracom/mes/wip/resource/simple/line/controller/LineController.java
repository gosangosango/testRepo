package kr.co.miracom.mes.wip.resource.simple.line.controller;

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
import kr.co.miracom.mes.wip.resource.simple.line.model.Line;
import kr.co.miracom.mes.wip.resource.simple.line.model.LineDetail;
import kr.co.miracom.mes.wip.resource.simple.line.service.get_list.GetLineListIn;

/**
 * Setup Controller: Line
 * @author myjung.jung
 * @since 2018. 06. 05.
 */
@Api(value = "Creating, Retrieving, Updating and Deleting Lines.")
@RequestMapping(path = MesConst.VER_PATH)
public interface LineController {
    static final String RESOURCE = "wip/lines";

    @GetMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Get Line")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "select", value = "Field Names of Line for Select", allowMultiple = true),
                    @ApiImplicitParam(name = "unselect", value = "Field Names of Line for Unselect", allowMultiple = true) })
    LineDetail get(@PathVariable String id, SelectOptions options) throws Exception;

    @PutMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Update Line")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "data", value = "Line Data", dataType = "LineDetail", required = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field of Line for Update", allowMultiple = true) })
    void put(@PathVariable String id, @RequestBody LineDetail data, @RequestParam(required = false) String[] fieldName)
                    throws Exception;

    @GetMapping(path = RESOURCE)
    @ApiOperation("Get Line List")
    GetListOut<Line> getList(GetLineListIn input) throws Exception;

    @PostMapping(path = RESOURCE + "/save")
    @ApiOperation("Save Line List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "list", value = "Line List", dataType = "Line", required = true, allowMultiple = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of Line for Save", allowMultiple = true) })
    void saveList(@RequestBody List<Line> list, @RequestParam(required = false) String[] fieldName) throws Exception;

    @DeleteMapping(path = RESOURCE)
    @ApiOperation("Delete Line List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "list", value = "Line List", dataType = "Line", required = true, allowMultiple = true) })
    void deleteList(@RequestBody List<Line> list) throws Exception;

}
