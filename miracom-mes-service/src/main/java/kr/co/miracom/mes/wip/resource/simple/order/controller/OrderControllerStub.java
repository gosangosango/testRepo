package kr.co.miracom.mes.wip.resource.simple.order.controller;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.mes.wip.resource.simple.order.model.Order;
import kr.co.miracom.mes.wip.resource.simple.order.model.OrderDetail;
import kr.co.miracom.mes.wip.resource.simple.order.service.get_list.GetOrderListIn;

/**
 * Setup Controller: Order
 * @author myjung.jung
 * @since 2018. 06. 12.
 */
public class OrderControllerStub implements OrderController {

    @Override
    public OrderDetail get(String id, SelectOptions options) throws Exception {
        return null;
    }

    @Override
    public void put(String id, OrderDetail data, String[] fieldName) throws Exception {

    }

    @Override
    public GetListOut<Order> getList(GetOrderListIn input) throws Exception {
        return null;
    }

    @Override
    public void saveList(List<Order> list, String[] fieldName) throws Exception {

    }

    @Override
    public void deleteList(List<Order> list) throws Exception {

    }

    @Override
    public void confirmList(List<Order> list, String[] fieldName) throws Exception {

    }

}
