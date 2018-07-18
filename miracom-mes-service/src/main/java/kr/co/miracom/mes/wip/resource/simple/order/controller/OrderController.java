package kr.co.miracom.mes.wip.resource.simple.order.controller;

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
import kr.co.miracom.mes.wip.resource.simple.order.model.Order;
import kr.co.miracom.mes.wip.resource.simple.order.model.OrderDetail;
import kr.co.miracom.mes.wip.resource.simple.order.service.get_list.GetOrderListIn;

/**
 * Setup Controller: Order
 * @author myjung.jung
 * @since 2018. 06. 12.
 */
@Api(value = "Creating, Retrieving, Updating and Deleting Orders.")
@RequestMapping(path = MesConst.VER_PATH)
public interface OrderController {
    static final String RESOURCE = "wip/orders";

    @GetMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Get Order")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "select", value = "Field Names of Order for Select", allowMultiple = true),
                    @ApiImplicitParam(name = "unselect", value = "Field Names of Order for Unselect", allowMultiple = true) })
    OrderDetail get(@PathVariable String id, SelectOptions options) throws Exception;

    @PutMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Update Order")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "data", value = "Order Json", dataType = "OrderDetail", required = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of Order for Update", allowMultiple = true) })
    void put(@PathVariable String id, @RequestBody OrderDetail data, @RequestParam(required = false) String[] fieldName)
                    throws Exception;

    @GetMapping(path = RESOURCE)
    @ApiOperation("Get Order List")
    GetListOut<Order> getList(GetOrderListIn input) throws Exception;

    @PostMapping(path = RESOURCE + "/save")
    @ApiOperation("Save Order List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "list", value = "Order List", dataType = "Order", required = true, allowMultiple = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of Order for Save", allowMultiple = true) })
    void saveList(@RequestBody List<Order> list, @RequestParam(required = false) String[] fieldName) throws Exception;

    @DeleteMapping(path = RESOURCE)
    @ApiOperation("Delete Order List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "list", value = "Order List", dataType = "Order", required = true, allowMultiple = true) })
    void deleteList(@RequestBody List<Order> list) throws Exception;

    @PostMapping(path = RESOURCE + "/confirm")
    @ApiOperation("Confirm Order List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "list", value = "Order List", dataType = "Order", required = true, allowMultiple = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of Order for Save", allowMultiple = true) })
    void confirmList(@RequestBody List<Order> list, @RequestParam(required = false) String[] fieldName)
                    throws Exception;

}
