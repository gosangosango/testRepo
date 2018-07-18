package kr.co.miracom.mes.inv.resource.simple.delivery.controller;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.mes.inv.resource.simple.delivery.model.Delivery;
import kr.co.miracom.mes.inv.resource.simple.delivery.model.DeliveryDetail;
import kr.co.miracom.mes.inv.resource.simple.delivery.model.DeliveryDlvDetail;
import kr.co.miracom.mes.inv.resource.simple.delivery.service.get_list.GetDeliveryListIn;
import kr.co.miracom.mes.inv.resource.simple.delivery.service.inspect_list.InspectDeliveryListIn;
import kr.co.miracom.mes.inv.resource.simple.delivery.service.inspect_list.InspectDeliveryListOut;

/**
 * Setup Controller: Delivery
 * @author myjung.jung
 * @since 2018. 06. 25.
 */
public class DeliveryControllerStub implements DeliveryController {

    @Override
    public DeliveryDetail get(String id, SelectOptions options) throws Exception {
        return null;
    }

    @Override
    public void put(String id, DeliveryDetail data, String[] fieldName) throws Exception {

    }

    @Override
    public GetListOut<Delivery> getList(GetDeliveryListIn input) throws Exception {
        return null;
    }

    @Override
    public void saveList(List<Delivery> list, String[] fieldName) throws Exception {

    }

    @Override
    public void deleteList(List<Delivery> list) throws Exception {

    }

    @Override
    public InspectDeliveryListOut inspectList(InspectDeliveryListIn input) throws Exception {
        return null;
    }

    @Override
    public GetListOut<DeliveryDlvDetail> getDlvDetailList(String id) throws Exception {
        return null;
    }

    @Override
    public void saveDlvDetailList(String id, List<DeliveryDlvDetail> list, String[] fieldName) throws Exception {

    }

}
