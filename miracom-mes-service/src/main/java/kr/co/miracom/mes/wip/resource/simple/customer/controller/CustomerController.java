package kr.co.miracom.mes.wip.resource.simple.customer.controller;

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
import kr.co.miracom.mes.wip.resource.simple.customer.model.Customer;
import kr.co.miracom.mes.wip.resource.simple.customer.model.CustomerDetail;
import kr.co.miracom.mes.wip.resource.simple.customer.service.get_list.GetCustomerListIn;

/**
 * Setup Controller: Customer
 * @author myjung.jung
 * @since 2018. 06. 14.
 */
@Api(value = "Creating, Retrieving, Updating and Deleting Customers.")
@RequestMapping(path = MesConst.VER_PATH)
public interface CustomerController {
    static final String RESOURCE = "wip/customers";

    @GetMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Get Customer")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "select", value = "Field Names of Customer for Select", allowMultiple = true),
                    @ApiImplicitParam(name = "unselect", value = "Field Names of Customer for Unselect", allowMultiple = true) })
    CustomerDetail get(@PathVariable String id, SelectOptions options) throws Exception;

    @PutMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Update Customer")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "data", value = "Customer Json", dataType = "CustomerDetail", required = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of Customer for Update", allowMultiple = true) })
    void put(@PathVariable String id, @RequestBody CustomerDetail data,
                    @RequestParam(required = false) String[] fieldName) throws Exception;

    @GetMapping(path = RESOURCE)
    @ApiOperation("Get Customer List")
    GetListOut<Customer> getList(GetCustomerListIn input) throws Exception;

    @PostMapping(path = RESOURCE + "/save")
    @ApiOperation("Save Customer List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "list", value = "Customer List", dataType = "Customer", required = true, allowMultiple = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of Customer for Save", allowMultiple = true) })
    void saveList(@RequestBody List<Customer> list, @RequestParam(required = false) String[] fieldName)
                    throws Exception;

    @DeleteMapping(path = RESOURCE)
    @ApiOperation("Delete Customer List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "list", value = "Customer List", dataType = "Customer", required = true, allowMultiple = true) })
    void deleteList(@RequestBody List<Customer> list) throws Exception;

}
