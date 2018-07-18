package kr.co.miracom.mes.inv.resource.simple.ret_mat.controller;

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
import kr.co.miracom.mes.inv.resource.simple.ret_mat.model.RetMat;
import kr.co.miracom.mes.inv.resource.simple.ret_mat.model.RetMatDetail;
import kr.co.miracom.mes.inv.resource.simple.ret_mat.service.get_list.GetRetMatListIn;
import kr.co.miracom.mes.inv.resource.simple.ret_mat.service.ret_list.RetMatListIn;
import kr.co.miracom.mes.inv.resource.simple.ret_mat.service.ret_list.RetMatListOut;

/**
 * Setup Controller: RetMat
 * @author mo21.kim
 * @since 2018. 07. 06.
 */
@Api(value = "Creating, Retrieving, Updating and Deleting RetMats.")
@RequestMapping(path = MesConst.VER_PATH)
public interface RetMatController {
    static final String RESOURCE = "inv/retMats";

    @GetMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Get RetMat")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "select", value = "Field Names of RetMat for Select", allowMultiple = true),
                    @ApiImplicitParam(name = "unselect", value = "Field Names of RetMat for Unselect", allowMultiple = true) })
    RetMatDetail get(@PathVariable String id, SelectOptions options) throws Exception;

    @PutMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Update RetMat")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "data", value = "RetMat Json", dataType = "RetMatDetail", required = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of RetMat for Update", allowMultiple = true) })
    void put(@PathVariable String id, @RequestBody RetMatDetail data, @RequestParam(required = false) String[] fieldName) throws Exception;

    @GetMapping(path = RESOURCE)
    @ApiOperation("Get RetMat List")
    GetListOut<RetMat> getList(GetRetMatListIn input) throws Exception;

    @DeleteMapping(path = RESOURCE)
    @ApiOperation("Delete RetMat List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "list", value = "RetMat List", dataType = "RetMat", required = true, allowMultiple = true) })
    void deleteList(@RequestBody List<RetMat> list) throws Exception;
    
    @PostMapping(path = RESOURCE + "/ret")
    @ApiOperation("return Material List")
    @ApiImplicitParam(name = "list", value = "RetMat List", dataType = "RetMat", required = true, allowMultiple = true)
    RetMatListOut retMatList(@RequestBody RetMatListIn list) throws Exception;
}
