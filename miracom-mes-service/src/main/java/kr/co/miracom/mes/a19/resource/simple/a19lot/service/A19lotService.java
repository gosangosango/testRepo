package kr.co.miracom.mes.a19.resource.simple.a19lot.service;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.RestService;
import kr.co.miracom.mes.a19.resource.simple.a19lot.model.A19lot;
import kr.co.miracom.mes.a19.resource.simple.a19lot.model.A19lotDetail;
import kr.co.miracom.mes.wip.model.MWipLotSts;

/**
 * Setup Service: A19lot
 * @author Zotac023
 * @since 2018. 07. 18.
 */
public class A19lotService {
    private RestService<MWipLotSts, A19lot, A19lotDetail> restService = new RestService<>(MWipLotSts.class, A19lot.class, A19lotDetail.class);

    public A19lotService() {
        // restService.setIdValueRule("UPPER");
        // restService.setPkFieldName("id");
        // restService.addFieldPattern("id", "^[-A-Z0-9.]*$");
    }

    public A19lotDetail get(String id, SelectOptions options) throws Exception {
        return restService.get(id, options);
    }

    public void post(String id, A19lotDetail data, String[] fieldName) throws Exception {
        restService.post(id, data, fieldName);
    }

    public void put(String id, A19lotDetail data, String[] fieldName) throws Exception {
        restService.put(id, data, fieldName);
    }

    public void delete(String id, A19lotDetail data) throws Exception {
        restService.delete(id, data);
    }

    public void postList(List<A19lot> list, String[] fieldName) throws Exception {
        restService.postList(list, fieldName);
    }

    public void putList(List<A19lot> list, String[] fieldName) throws Exception {
        restService.putList(list, fieldName);
    }

    public void saveList(List<A19lot> list, String[] fieldName) throws Exception {
        restService.saveList(list, fieldName);
    }

    public void deleteList(List<A19lot> list) throws Exception {
        restService.deleteList(list);
    }

}
