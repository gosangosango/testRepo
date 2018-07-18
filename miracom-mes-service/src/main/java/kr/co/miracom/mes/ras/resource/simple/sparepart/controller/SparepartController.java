package kr.co.miracom.mes.ras.resource.simple.sparepart.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.mes.config.MesConst;
import kr.co.miracom.mes.ras.resource.simple.sparepart.model.Sparepart;
import kr.co.miracom.mes.ras.resource.simple.sparepart.model.SparepartDetail;
import kr.co.miracom.mes.ras.resource.simple.sparepart.service.get_list.GetSparepartListIn;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Setup Controller: Sparepart
 * @author hhk
 * @since 2018. 07. 02.
 */
@Api(value = "Creating, Retrieving, Updating and Deleting Spareparts.")
@RequestMapping(path = MesConst.VER_PATH)
public interface SparepartController {
    static final String RESOURCE = "ras/spareparts";

    @GetMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Get Sparepart")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "select", value = "Field Names of Sparepart for Select", allowMultiple = true),
                    @ApiImplicitParam(name = "unselect", value = "Field Names of Sparepart for Unselect", allowMultiple = true) })
    SparepartDetail get(@PathVariable String id, SelectOptions options) throws Exception;

    @PutMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Update Sparepart")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "data", value = "Sparepart Json", dataType = "SparepartDetail", required = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of Sparepart for Update", allowMultiple = true) })
    void put(@PathVariable String id, @RequestBody SparepartDetail data, @RequestParam(required = false) String[] fieldName) throws Exception;

    @GetMapping(path = RESOURCE)
    @ApiOperation("Get Sparepart List")
    GetListOut<Sparepart> getList(GetSparepartListIn input) throws Exception;

    @PostMapping(path = RESOURCE + "/save")
    @ApiOperation("Save Sparepart List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "list", value = "Sparepart List", dataType = "Sparepart", required = true, allowMultiple = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of Sparepart for Save", allowMultiple = true) })
    void saveList(@RequestBody List<Sparepart> list, @RequestParam(required = false) String[] fieldName) throws Exception;

    @DeleteMapping(path = RESOURCE)
    @ApiOperation("Delete Sparepart List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "list", value = "Sparepart List", dataType = "Sparepart", required = true, allowMultiple = true) })
    void deleteList(@RequestBody List<Sparepart> list) throws Exception;

}
