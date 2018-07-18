package kr.co.miracom.mes.a19.resource.simple.a19flow.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.mes.a19.resource.simple.a19flow.model.A19flow;
import kr.co.miracom.mes.a19.resource.simple.a19flow.model.A19flowA19oper;
import kr.co.miracom.mes.a19.resource.simple.a19flow.model.A19flowDetail;
import kr.co.miracom.mes.a19.resource.simple.a19flow.service.get_list.GetA19flowListIn;
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
 * Setup Controller: A19flow
 * @author Zotac023
 * @since 2018. 07. 17.
 */
@Api(value = "Creating, Retrieving, Updating and Deleting A19flows.")
@RequestMapping(path = MesConst.VER_PATH)
public interface A19flowController {
    static final String RESOURCE = "a19/a19flows";

    @GetMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Get A19flow")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "select", value = "Field Names of A19flow for Select", allowMultiple = true),
                    @ApiImplicitParam(name = "unselect", value = "Field Names of A19flow for Unselect", allowMultiple = true) })
    A19flowDetail get(@PathVariable String id, SelectOptions options) throws Exception;

    @PutMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Update A19flow")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "data", value = "A19flow Json", dataType = "A19flowDetail", required = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of A19flow for Update", allowMultiple = true) })
    void put(@PathVariable String id, @RequestBody A19flowDetail data, @RequestParam(required = false) String[] fieldName) throws Exception;

    @GetMapping(path = RESOURCE)
    @ApiOperation("Get A19flow List")
    GetListOut<A19flow> getList(GetA19flowListIn input) throws Exception;

    @PostMapping(path = RESOURCE + "/save")
    @ApiOperation("Save A19flow List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "list", value = "A19flow List", dataType = "A19flow", required = true, allowMultiple = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of A19flow for Save", allowMultiple = true) })
    void saveList(@RequestBody List<A19flow> list, @RequestParam(required = false) String[] fieldName) throws Exception;

    @DeleteMapping(path = RESOURCE)
    @ApiOperation("Delete A19flow List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "list", value = "A19flow List", dataType = "A19flow", required = true, allowMultiple = true) })
    void deleteList(@RequestBody List<A19flow> list) throws Exception;

    @GetMapping(path = RESOURCE + "/{id}/a19opers")
    @ApiOperation("Get A19flow A19oper List")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID of A19flow", required = true) })
    GetListOut<A19flowA19oper> getA19operList(@PathVariable String id) throws Exception;

    @PostMapping(path = RESOURCE + "/{id}/a19opers/save")
    @ApiOperation("Save A19flow A19oper List")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID of A19flow", required = true),
                    @ApiImplicitParam(name = "list", value = "A19flow A19oper List", dataType = "A19flowA19oper", allowMultiple = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of A19oper for Save", allowMultiple = true) })
    void saveA19operList(@PathVariable String id, @RequestBody List<A19flowA19oper> list, @RequestParam(required = false) String[] fieldName) throws Exception;

}
