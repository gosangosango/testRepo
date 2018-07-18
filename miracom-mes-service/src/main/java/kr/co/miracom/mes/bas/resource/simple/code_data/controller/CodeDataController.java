package kr.co.miracom.mes.bas.resource.simple.code_data.controller;

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
import kr.co.miracom.mes.bas.resource.simple.code_data.model.CodeData;
import kr.co.miracom.mes.bas.resource.simple.code_data.model.CodeDataDetail;
import kr.co.miracom.mes.bas.resource.simple.code_data.service.get_list.GetCodeDataListIn;
import kr.co.miracom.mes.config.MesConst;

/**
 * Setup Controller: CodeData
 * @author myjung.jung
 * @since 2018. 06. 05.
 */
@Api(value = "Creating, Retrieving, Updating and Deleting CodeData.")
@RequestMapping(path = MesConst.VER_PATH)
public interface CodeDataController {
    static final String RESOURCE = "bas/codeData";

    @GetMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Get CodeData")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "select", value = "Field Names of CodeData for Select", allowMultiple = true),
                    @ApiImplicitParam(name = "unselect", value = "Field Names of CodeData for Unselect", allowMultiple = true) })
    CodeDataDetail get(@PathVariable String id, SelectOptions options) throws Exception;

    @PutMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Update CodeData")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "data", value = "CodeData Json", dataType = "CodeDataDetail", required = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of CodeData for Update", allowMultiple = true) })
    void put(@PathVariable String id, @RequestBody CodeDataDetail data,
                    @RequestParam(required = false) String[] fieldName) throws Exception;

    @GetMapping(path = RESOURCE)
    @ApiOperation("Get CodeData List")
    GetListOut<CodeData> getList(GetCodeDataListIn input) throws Exception;

    @PostMapping(path = RESOURCE + "/save")
    @ApiOperation("Save CodeData List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "list", value = "CodeData List", dataType = "CodeData", required = true, allowMultiple = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of CodeData for Save", allowMultiple = true) })
    void saveList(@RequestBody List<CodeData> list, @RequestParam(required = false) String[] fieldName)
                    throws Exception;

    @DeleteMapping(path = RESOURCE)
    @ApiOperation("Delete CodeData List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "list", value = "CodeData List", dataType = "CodeData", required = true, allowMultiple = true) })
    void deleteList(@RequestBody List<CodeData> list) throws Exception;

}
