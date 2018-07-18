package kr.co.miracom.mes.wip.resource.simple.order.service;

import java.util.ArrayList;
import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.exception.BizException;
import kr.co.miracom.framework.service.RestService;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.Property;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.wip.model.MWipFlwDef;
import kr.co.miracom.mes.wip.model.MWipMatDef;
import kr.co.miracom.mes.wip.model.MWipOrdSts;
import kr.co.miracom.mes.wip.resource.simple.order.model.Order;
import kr.co.miracom.mes.wip.resource.simple.order.model.OrderDetail;

/**
 * Setup Service: Order
 * @author myjung.jung
 * @since 2018. 06. 12.
 */
public class OrderService {
    private RestService<MWipOrdSts, Order, OrderDetail> restService = new RestService<>(MWipOrdSts.class, Order.class,
                    OrderDetail.class);

    public OrderDetail get(String id, SelectOptions options) throws Exception {
        return restService.get(id, options);
    }

    public void post(String id, OrderDetail data, String[] fieldName) throws Exception {
        checkSave(id, data);
        restService.post(id, data, fieldName);
    }

    public void put(String id, OrderDetail data, String[] fieldName) throws Exception {
        checkSave(id, data);
        restService.put(id, data, fieldName);
    }

    public void delete(String id, OrderDetail data) throws Exception {
        checkSave(id, data);
        restService.delete(id, data);
    }

    public void postList(List<Order> list, String[] fieldName) throws Exception {
        checkSave(list);
        restService.postList(list, fieldName);
    }

    public void putList(List<Order> list, String[] fieldName) throws Exception {
        checkSave(list);
        restService.putList(list, fieldName);
    }

    public void saveList(List<Order> list, String[] fieldName) throws Exception {
        checkSave(list);
        restService.saveList(list, fieldName);
    }

    public void deleteList(List<Order> list) throws Exception {
        checkSave(list);
        restService.deleteList(list);
    }

    public void confirmList(List<Order> list, String[] fieldName) throws Exception {
        saveList(list, fieldName);
        List<MWipOrdSts> orderList = new ArrayList<>(list.size());
        for (Order item : list) {
            MWipOrdSts order = DbUtils.select(MWipOrdSts.class, item,
                            new SelectOptions().addSelect("factoryCode", "orderNo"));
            order.setOrdStatus("CONFIRM");
            orderList.add(order);
        }
        DbUtils.updateBatch(orderList, new String[] { "ordStatus" });
    }

    private static void checkSave(List<Order> list) throws Exception {
        for (Order order : list) {
            checkSave(order.getId(), order);
        }
    }

    private static void checkSave(String id, Order data) throws Exception {
        DbUtils.checkFound(MWipMatDef.class, data.getMatCode());
        DbUtils.checkFound(MWipFlwDef.class, data.getFlowCode());

        if (ValueUtils.isEmpty(id)) {
            MWipOrdSts order = DbUtils.select(MWipOrdSts.class, data, new SelectOptions().addSelect("orderNo"));
            if (order != null) {
                throw new BizException("XXXXXX", "이미 데이터가 있습니다.", new Property("orderNo", order.getOrderNo()));
            }
            data.setOrdStatus("CREATE");
            return;
        }

        MWipOrdSts order = DbUtils.select(MWipOrdSts.class, id, new SelectOptions().addSelect("orderNo"));

        if (order == null) {
            data.setOrdStatus("CREATE");
            return;
        }

        if (!"CREATE".equals(order.getOrdStatus())) {
            throw new BizException("XXXXXX", "작업지시 내용을 변경할 수 있는 상태가 아닙니다.", new Property("orderNo", order.getOrderNo()),
                            new Property("ordStatus", order.getOrdStatus()));
        }

        data.setOrdStatus(order.getOrdStatus());
    }

}
