package kr.co.miracom.mes.wip.resource.simple.order_bom.controller;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.mes.wip.resource.simple.order_bom.model.OrderBom;
import kr.co.miracom.mes.wip.resource.simple.order_bom.model.OrderBomDetail;
import kr.co.miracom.mes.wip.resource.simple.order_bom.model.OrderBomComp;
import kr.co.miracom.mes.wip.resource.simple.order_bom.service.get_list.GetOrderBomListIn;

/**
 * Setup Controller: OrderBom
 * @author myjung.jung
 * @since 2018. 06. 26.
 */
public class OrderBomControllerStub implements OrderBomController {

    @Override
    public OrderBomDetail get(String id, SelectOptions options) throws Exception {
        return null;
    }

    @Override
    public void post(String id, OrderBomDetail data, String[] fieldName) throws Exception {

    }

    @Override
    public void put(String id, OrderBomDetail data, String[] fieldName) throws Exception {

    }

    @Override
    public GetListOut<OrderBom> getList(GetOrderBomListIn input) throws Exception {
        return null;
    }

    @Override
    public void saveList(List<OrderBom> list, String[] fieldName) throws Exception {

    }

    @Override
    public void deleteList(List<OrderBom> list) throws Exception {

    }

    @Override
    public GetListOut<OrderBomComp> getCompList(String id) throws Exception {
        return null;
    }

    @Override
    public void saveCompList(String id, List<OrderBomComp> list, String[] fieldName) throws Exception {

    }

}
