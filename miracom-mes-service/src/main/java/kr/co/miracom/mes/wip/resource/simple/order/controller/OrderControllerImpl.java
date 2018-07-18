package kr.co.miracom.mes.wip.resource.simple.order.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.mes.wip.resource.simple.order.model.Order;
import kr.co.miracom.mes.wip.resource.simple.order.model.OrderDetail;
import kr.co.miracom.mes.wip.resource.simple.order.service.OrderService;
import kr.co.miracom.mes.wip.resource.simple.order.service.get_list.GetOrderList;
import kr.co.miracom.mes.wip.resource.simple.order.service.get_list.GetOrderListIn;

/**
 * Setup Controller: Order
 * @author myjung.jung
 * @since 2018. 06. 12.
 */
@CrossOrigin
@RestController
public class OrderControllerImpl implements OrderController {

    @Override
    public OrderDetail get(@PathVariable String id, SelectOptions options) throws Exception {
        return BeanUtils.get(OrderService.class).get(id, options);
    }

    @Override
    public void put(@PathVariable String id, @RequestBody OrderDetail data,
                    @RequestParam(required = false) String[] fieldName) throws Exception {
        BeanUtils.get(OrderService.class).put(id, data, fieldName);
    }

    @Override
    public GetListOut<Order> getList(GetOrderListIn input) throws Exception {
        return BeanUtils.get(GetOrderList.class).getList(input);
    }

    @Override
    public void saveList(@RequestBody List<Order> list, @RequestParam(required = false) String[] fieldName)
                    throws Exception {
        BeanUtils.get(OrderService.class).saveList(list, fieldName);
    }

    @Override
    public void deleteList(@RequestBody List<Order> list) throws Exception {
        BeanUtils.get(OrderService.class).deleteList(list);
    }

    @Override
    public void confirmList(@RequestBody List<Order> list, @RequestParam(required = false) String[] fieldName)
                    throws Exception {
        BeanUtils.get(OrderService.class).confirmList(list, fieldName);
    }

}
