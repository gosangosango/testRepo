package kr.co.miracom.mes.a19.resource.simple.a19operation.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.mes.a19.resource.simple.a19operation.model.A19operation;
import kr.co.miracom.mes.a19.resource.simple.a19operation.model.A19operationDetail;
import kr.co.miracom.mes.a19.resource.simple.a19operation.service.get_list.GetA19operationListIn;
import kr.co.miracom.mes.config.MesConst;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Setup Controller: A19operation
 * @author Zotac023
 * @since 2018. 07. 17.
 */
@Api(value = "Creating, Retrieving, Updating and Deleting A19operations.")
@RequestMapping(path = MesConst.VER_PATH)
public interface A19operationController {
    static final String RESOURCE = "a19/a19operations";

    @GetMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Get A19operation")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "select", value = "Field Names of A19operation for Select", allowMultiple = true),
                    @ApiImplicitParam(name = "unselect", value = "Field Names of A19operation for Unselect", allowMultiple = true) })
    A19operationDetail get(@PathVariable String id, SelectOptions options) throws Exception;

    @PutMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Update A19operation")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "data", value = "A19operation Json", dataType = "A19operationDetail", required = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of A19operation for Update", allowMultiple = true) })
    void put(@PathVariable String id, @RequestBody A19operationDetail data, @RequestParam(required = false) String[] fieldName) throws Exception;

    @GetMapping(path = RESOURCE)
    @ApiOperation("Get A19operation List")
    GetListOut<A19operation> getList(GetA19operationListIn input) throws Exception;

    @PostMapping(path = RESOURCE + "/save")
    @ApiOperation("Save A19operation List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "list", value = "A19operation List", dataType = "A19operation", required = true, allowMultiple = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of A19operation for Save", allowMultiple = true) })
    void saveList(@RequestBody List<A19operation> list, @RequestParam(required = false) String[] fieldName) throws Exception;

    @DeleteMapping(path = RESOURCE)
    @ApiOperation("Delete A19operation List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "list", value = "A19operation List", dataType = "A19operation", required = true, allowMultiple = true) })
    void deleteList(@RequestBody List<A19operation> list) throws Exception;

}
