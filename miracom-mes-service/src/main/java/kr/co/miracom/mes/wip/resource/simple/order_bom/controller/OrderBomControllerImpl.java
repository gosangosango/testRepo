package kr.co.miracom.mes.wip.resource.simple.order_bom.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.mes.wip.resource.simple.order_bom.model.OrderBom;
import kr.co.miracom.mes.wip.resource.simple.order_bom.model.OrderBomComp;
import kr.co.miracom.mes.wip.resource.simple.order_bom.model.OrderBomDetail;
import kr.co.miracom.mes.wip.resource.simple.order_bom.service.OrderBomCompService;
import kr.co.miracom.mes.wip.resource.simple.order_bom.service.OrderBomService;
import kr.co.miracom.mes.wip.resource.simple.order_bom.service.get_list.GetOrderBomList;
import kr.co.miracom.mes.wip.resource.simple.order_bom.service.get_list.GetOrderBomListIn;

/**
 * Setup Controller: OrderBom
 * @author myjung.jung
 * @since 2018. 06. 26.
 */
@CrossOrigin
@RestController
public class OrderBomControllerImpl implements OrderBomController {

    @Override
    public OrderBomDetail get(@PathVariable String id, SelectOptions options) throws Exception {
        return BeanUtils.get(OrderBomService.class).get(id, options);
    }

    @Override
    public void post(@PathVariable String id, @RequestBody OrderBomDetail data,
                    @RequestParam(required = false) String[] fieldName) throws Exception {
        BeanUtils.get(OrderBomService.class).post(id, data, fieldName);
    }

    @Override
    public void put(@PathVariable String id, @RequestBody OrderBomDetail data,
                    @RequestParam(required = false) String[] fieldName) throws Exception {
        BeanUtils.get(OrderBomService.class).put(id, data, fieldName);
    }

    @Override
    public GetListOut<OrderBom> getList(GetOrderBomListIn input) throws Exception {
        return BeanUtils.get(GetOrderBomList.class).getList(input);
    }

    @Override
    public void saveList(@RequestBody List<OrderBom> list, @RequestParam(required = false) String[] fieldName)
                    throws Exception {
        BeanUtils.get(OrderBomService.class).saveList(list, fieldName);
    }

    @Override
    public void deleteList(@RequestBody List<OrderBom> list) throws Exception {
        BeanUtils.get(OrderBomService.class).deleteList(list);
    }

    @Override
    public GetListOut<OrderBomComp> getCompList(@PathVariable String id) throws Exception {
        return BeanUtils.get(OrderBomCompService.class).getList(id);
    }

    @Override
    public void saveCompList(@PathVariable String id, @RequestBody List<OrderBomComp> list,
                    @RequestParam(required = false) String[] fieldName) throws Exception {
        BeanUtils.get(OrderBomCompService.class).saveList(id, list, fieldName);
    }

}
