package kr.co.miracom.mes.inv.resource.simple.delivery.service;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.RestService;
import kr.co.miracom.mes.inv.model.MInvDlvMst;
import kr.co.miracom.mes.inv.resource.simple.delivery.model.Delivery;
import kr.co.miracom.mes.inv.resource.simple.delivery.model.DeliveryDetail;

/**
 * Setup Service: Delivery
 * @author myjung.jung
 * @since 2018. 06. 25.
 */
public class DeliveryService {
    private RestService<MInvDlvMst, Delivery, DeliveryDetail> restService = new RestService<>(MInvDlvMst.class,
                    Delivery.class, DeliveryDetail.class);

    public DeliveryDetail get(String id, SelectOptions options) throws Exception {
        return restService.get(id, options);
    }

    public void post(String id, DeliveryDetail data, String[] fieldName) throws Exception {
        restService.post(id, data, fieldName);
    }

    public void put(String id, DeliveryDetail data, String[] fieldName) throws Exception {
        restService.put(id, data, fieldName);
    }

    public void delete(String id, DeliveryDetail data) throws Exception {
        restService.delete(id, data);
    }

    public void postList(List<Delivery> list, String[] fieldName) throws Exception {
        restService.postList(list, fieldName);
    }

    public void putList(List<Delivery> list, String[] fieldName) throws Exception {
        restService.putList(list, fieldName);
    }

    public void saveList(List<Delivery> list, String[] fieldName) throws Exception {
        restService.saveList(list, fieldName);
    }

    public void deleteList(List<Delivery> list) throws Exception {
        restService.deleteList(list);
    }

}
