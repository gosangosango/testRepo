package kr.co.miracom.mes.wip.resource.simple.vendor.controller;

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
import kr.co.miracom.mes.wip.resource.simple.vendor.model.Vendor;
import kr.co.miracom.mes.wip.resource.simple.vendor.model.VendorDetail;
import kr.co.miracom.mes.wip.resource.simple.vendor.service.get_list.GetVendorListIn;

/**
 * Setup Controller: Vendor
 * @author myjung.jung
 * @since 2018. 06. 14.
 */
@Api(value = "Creating, Retrieving, Updating and Deleting Vendors.")
@RequestMapping(path = MesConst.VER_PATH)
public interface VendorController {
    static final String RESOURCE = "wip/vendors";

    @GetMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Get Vendor")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "select", value = "Field Names of Vendor for Select", allowMultiple = true),
                    @ApiImplicitParam(name = "unselect", value = "Field Names of Vendor for Unselect", allowMultiple = true) })
    VendorDetail get(@PathVariable String id, SelectOptions options) throws Exception;

    @PutMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Update Vendor")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "data", value = "Vendor Json", dataType = "VendorDetail", required = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of Vendor for Update", allowMultiple = true) })
    void put(@PathVariable String id, @RequestBody VendorDetail data,
                    @RequestParam(required = false) String[] fieldName) throws Exception;

    @GetMapping(path = RESOURCE)
    @ApiOperation("Get Vendor List")
    GetListOut<Vendor> getList(GetVendorListIn input) throws Exception;

    @PostMapping(path = RESOURCE + "/save")
    @ApiOperation("Save Vendor List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "list", value = "Vendor List", dataType = "Vendor", required = true, allowMultiple = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of Vendor for Save", allowMultiple = true) })
    void saveList(@RequestBody List<Vendor> list, @RequestParam(required = false) String[] fieldName) throws Exception;

    @DeleteMapping(path = RESOURCE)
    @ApiOperation("Delete Vendor List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "list", value = "Vendor List", dataType = "Vendor", required = true, allowMultiple = true) })
    void deleteList(@RequestBody List<Vendor> list) throws Exception;

}
