package kr.co.miracom.mes.wip.resource.simple.material.controller;

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
import kr.co.miracom.mes.wip.resource.simple.material.model.Material;
import kr.co.miracom.mes.wip.resource.simple.material.model.MaterialCustomer;
import kr.co.miracom.mes.wip.resource.simple.material.model.MaterialDetail;
import kr.co.miracom.mes.wip.resource.simple.material.model.MaterialFlow;
import kr.co.miracom.mes.wip.resource.simple.material.model.MaterialVendor;
import kr.co.miracom.mes.wip.resource.simple.material.service.get_list.GetMaterialListIn;

/**
 * Setup Controller: Material
 * @author myjung.jung
 * @since 2018. 06. 05.
 */
@Api(value = "Creating, Retrieving, Updating and Deleting Materials.")
@RequestMapping(path = MesConst.VER_PATH)
public interface MaterialController {
    static final String RESOURCE = "wip/materials";

    @GetMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Get Material")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "select", value = "Field Names of Material for Select", allowMultiple = true) })
    MaterialDetail get(@PathVariable String id, SelectOptions options) throws Exception;

    @PutMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Update Material")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "data", value = "Material Json", dataType = "MaterialDetail", required = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of Material for Update", allowMultiple = true) })
    void put(@PathVariable String id, @RequestBody MaterialDetail data,
                    @RequestParam(required = false) String[] fieldName) throws Exception;

    @GetMapping(path = RESOURCE)
    @ApiOperation("Get Material List")
    GetListOut<Material> getList(GetMaterialListIn input) throws Exception;

    @PostMapping(path = RESOURCE + "/save")
    @ApiOperation("Save Material List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "list", value = "Material List", dataType = "Material", required = true, allowMultiple = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of Material for Save", allowMultiple = true) })
    void saveList(@RequestBody List<Material> list, @RequestParam(required = false) String[] fieldName)
                    throws Exception;

    @DeleteMapping(path = RESOURCE)
    @ApiOperation("Delete Material List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "list", value = "Material List", dataType = "Material", required = true, allowMultiple = true) })
    void deleteList(@RequestBody List<Material> list) throws Exception;

    @GetMapping(path = RESOURCE + "/{id}/vendors")
    @ApiOperation("Get Material Vendor List")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "Material ID", required = true) })
    GetListOut<MaterialVendor> getVendorList(@PathVariable String id) throws Exception;

    @PostMapping(path = RESOURCE + "/{id}/vendors/save")
    @ApiOperation("Save Material Vendor List")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "Material ID", required = true),
                    @ApiImplicitParam(name = "list", value = "Material Vendor List", dataType = "MaterialVendor", allowMultiple = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of Vendor for Save", allowMultiple = true) })
    void saveVendorList(@PathVariable String id, @RequestBody List<MaterialVendor> list,
                    @RequestParam(required = false) String[] fieldName) throws Exception;

    @GetMapping(path = RESOURCE + "/{id}/customers")
    @ApiOperation("Get Material Customer List")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "Material ID", required = true) })
    GetListOut<MaterialCustomer> getCustomerList(@PathVariable String id) throws Exception;

    @PostMapping(path = RESOURCE + "/{id}/customers/save")
    @ApiOperation("Save Material Customer List")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "Material ID", required = true),
                    @ApiImplicitParam(name = "list", value = "Material Customer List", dataType = "MaterialCustomer", allowMultiple = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of Customer for Save", allowMultiple = true) })
    void saveCustomerList(@PathVariable String id, @RequestBody List<MaterialCustomer> list,
                    @RequestParam(required = false) String[] fieldName) throws Exception;

    @GetMapping(path = RESOURCE + "/{id}/flows")
    @ApiOperation("Get Material Flow List")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "Material ID", required = true) })
    GetListOut<MaterialFlow> getFlowList(@PathVariable String id) throws Exception;

    @PostMapping(path = RESOURCE + "/{id}/flows/save")
    @ApiOperation("Save Material Flow List")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "Material ID", required = true),
                    @ApiImplicitParam(name = "list", value = "Material Flow List", dataType = "MaterialFlow", allowMultiple = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of Flow for Save", allowMultiple = true) })
    void saveFlowList(@PathVariable String id, @RequestBody List<MaterialFlow> list,
                    @RequestParam(required = false) String[] fieldName) throws Exception;

}
