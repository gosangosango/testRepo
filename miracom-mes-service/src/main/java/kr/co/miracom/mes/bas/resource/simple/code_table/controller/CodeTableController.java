package kr.co.miracom.mes.bas.resource.simple.code_table.controller;

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
import kr.co.miracom.mes.bas.resource.simple.code_table.model.CodeTable;
import kr.co.miracom.mes.bas.resource.simple.code_table.model.CodeTableDetail;
import kr.co.miracom.mes.bas.resource.simple.code_table.service.get_list.GetCodeTableListIn;
import kr.co.miracom.mes.config.MesConst;

/**
 * Setup Controller: CodeTable
 * @author myjung.jung
 * @since 2018. 06. 05.
 */
@Api(value = "Creating, Retrieving, Updating and Deleting CodeTables.")
@RequestMapping(path = MesConst.VER_PATH)
public interface CodeTableController {
    static final String RESOURCE = "bas/codeTables";

    @GetMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Get CodeTable")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "select", value = "Field Names of CodeTable for Select", allowMultiple = true),
                    @ApiImplicitParam(name = "unselect", value = "Field Names of CodeTable for Unselect", allowMultiple = true) })
    CodeTableDetail get(@PathVariable String id, SelectOptions options) throws Exception;

    @PutMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Update CodeTable")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "data", value = "CodeTable Json", dataType = "CodeTableDetail"),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of CodeTable for Update", allowMultiple = true) })
    void put(@PathVariable String id, @RequestBody CodeTableDetail data,
                    @RequestParam(required = false) String[] fieldName) throws Exception;

    @GetMapping(path = RESOURCE)
    @ApiOperation("Get CodeTable List")
    GetListOut<CodeTable> getList(GetCodeTableListIn input) throws Exception;

    @PostMapping(path = RESOURCE + "/save")
    @ApiOperation("Save CodeTable List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "list", value = "CodeTable List", dataType = "CodeTable", required = true, allowMultiple = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of CodeTable for Save", allowMultiple = true) })
    void saveList(@RequestBody List<CodeTable> list, @RequestParam(required = false) String[] fieldName)
                    throws Exception;

    @DeleteMapping(path = RESOURCE)
    @ApiOperation("Delete CodeTable List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "list", value = "CodeTable List", dataType = "CodeTable", required = true, allowMultiple = true) })
    void deleteList(@RequestBody List<CodeTable> list) throws Exception;

}
