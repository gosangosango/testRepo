package kr.co.miracom.mes.wip.resource.simple.line_mf.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.mes.config.MesConst;
import kr.co.miracom.mes.wip.resource.simple.line_mf.model.LineMf;
import kr.co.miracom.mes.wip.resource.simple.line_mf.model.LineMfDetail;
import kr.co.miracom.mes.wip.resource.simple.line_mf.service.get_list.GetLineMfListIn;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Setup Controller: LineMf
 * @author mo21.kim
 * @since 2018. 07. 03.
 */
@Api(value = "Creating, Retrieving, Updating and Deleting LineMfs.")
@RequestMapping(path = MesConst.VER_PATH)
public interface LineMfController {
    static final String RESOURCE = "wip/lineMfs";

    @GetMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Get LineMf")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "select", value = "Field Names of LineMf for Select", allowMultiple = true),
                    @ApiImplicitParam(name = "unselect", value = "Field Names of LineMf for Unselect", allowMultiple = true) })
    LineMfDetail get(@PathVariable String id, SelectOptions options) throws Exception;

    @PutMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Update LineMf")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "data", value = "LineMf Json", dataType = "LineMfDetail", required = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of LineMf for Update", allowMultiple = true) })
    void put(@PathVariable String id, @RequestBody LineMfDetail data, @RequestParam(required = false) String[] fieldName) throws Exception;

    @GetMapping(path = RESOURCE)
    @ApiOperation("Get LineMf List")
    GetListOut<LineMf> getList(GetLineMfListIn input) throws Exception;

    @PostMapping(path = RESOURCE + "/save")
    @ApiOperation("Save LineMf List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "list", value = "LineMf List", dataType = "LineMf", required = true, allowMultiple = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of LineMf for Save", allowMultiple = true) })
    void saveList(@RequestBody List<LineMf> list, @RequestParam(required = false) String[] fieldName) throws Exception;

    @DeleteMapping(path = RESOURCE)
    @ApiOperation("Delete LineMf List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "list", value = "LineMf List", dataType = "LineMf", required = true, allowMultiple = true) })
    void deleteList(@RequestBody List<LineMf> list) throws Exception;

}
