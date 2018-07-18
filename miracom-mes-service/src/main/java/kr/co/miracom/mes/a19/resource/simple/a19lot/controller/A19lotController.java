package kr.co.miracom.mes.a19.resource.simple.a19lot.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.mes.a19.resource.simple.a19lot.model.A19lot;
import kr.co.miracom.mes.a19.resource.simple.a19lot.model.A19lotDetail;
import kr.co.miracom.mes.a19.resource.simple.a19lot.service.get_list.GetA19lotListIn;
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
 * Setup Controller: A19lot
 * @author Zotac023
 * @since 2018. 07. 18.
 */
@Api(value = "Creating, Retrieving, Updating and Deleting A19lots.")
@RequestMapping(path = MesConst.VER_PATH)
public interface A19lotController {
    static final String RESOURCE = "a19/a19lots";

    @GetMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Get A19lot")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "select", value = "Field Names of A19lot for Select", allowMultiple = true),
                    @ApiImplicitParam(name = "unselect", value = "Field Names of A19lot for Unselect", allowMultiple = true) })
    A19lotDetail get(@PathVariable String id, SelectOptions options) throws Exception;

    @PutMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Update A19lot")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "data", value = "A19lot Json", dataType = "A19lotDetail", required = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of A19lot for Update", allowMultiple = true) })
    void put(@PathVariable String id, @RequestBody A19lotDetail data, @RequestParam(required = false) String[] fieldName) throws Exception;

    @GetMapping(path = RESOURCE)
    @ApiOperation("Get A19lot List")
    GetListOut<A19lot> getList(GetA19lotListIn input) throws Exception;

    @PostMapping(path = RESOURCE + "/save")
    @ApiOperation("Save A19lot List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "list", value = "A19lot List", dataType = "A19lot", required = true, allowMultiple = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of A19lot for Save", allowMultiple = true) })
    void saveList(@RequestBody List<A19lot> list, @RequestParam(required = false) String[] fieldName) throws Exception;

    @DeleteMapping(path = RESOURCE)
    @ApiOperation("Delete A19lot List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "list", value = "A19lot List", dataType = "A19lot", required = true, allowMultiple = true) })
    void deleteList(@RequestBody List<A19lot> list) throws Exception;

}
