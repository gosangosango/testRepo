package kr.co.miracom.mes.inv.resource.simple.inv_lot.service;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.RestService;
import kr.co.miracom.mes.inv.model.MInvLotSts;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.model.InvLot;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.model.InvLotDetail;

/**
 * Setup Service: InvLot
 * @author myjung.jung
 * @since 2018. 06. 21.
 */
public class InvLotService {
    private RestService<MInvLotSts, InvLot, InvLotDetail> restService = new RestService<>(MInvLotSts.class,
                    InvLot.class, InvLotDetail.class);

    public InvLotDetail get(String id, SelectOptions options) throws Exception {
        return restService.get(id, options);
    }

    public void post(String id, InvLotDetail data, String[] fieldName) throws Exception {
        restService.post(id, data, fieldName);
    }

    public void put(String id, InvLotDetail data, String[] fieldName) throws Exception {
        restService.put(id, data, fieldName);
    }

    public void delete(String id, InvLotDetail data) throws Exception {
        restService.delete(id, data);
    }

    public void postList(List<InvLot> list, String[] fieldName) throws Exception {
        restService.postList(list, fieldName);
    }

    public void putList(List<InvLot> list, String[] fieldName) throws Exception {
        restService.putList(list, fieldName);
    }

    public void saveList(List<InvLot> list, String[] fieldName) throws Exception {
        restService.saveList(list, fieldName);
    }

    public void deleteList(List<InvLot> list) throws Exception {
        restService.deleteList(list);
    }

}
