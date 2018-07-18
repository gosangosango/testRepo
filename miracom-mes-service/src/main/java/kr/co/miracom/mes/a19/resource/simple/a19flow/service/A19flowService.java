package kr.co.miracom.mes.a19.resource.simple.a19flow.service;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.RestService;
import kr.co.miracom.mes.a19.model.MA19FlwDef;
import kr.co.miracom.mes.a19.resource.simple.a19flow.model.A19flow;
import kr.co.miracom.mes.a19.resource.simple.a19flow.model.A19flowDetail;

/**
 * Setup Service: A19flow
 * @author Zotac023
 * @since 2018. 07. 17.
 */
public class A19flowService {
    private RestService<MA19FlwDef, A19flow, A19flowDetail> restService = new RestService<>(MA19FlwDef.class, A19flow.class, A19flowDetail.class);

    public A19flowService() {
        // restService.setIdValueRule("UPPER");
        // restService.setPkFieldName("id");
        // restService.addFieldPattern("id", "^[-A-Z0-9.]*$");
    }

    public A19flowDetail get(String id, SelectOptions options) throws Exception {
        return restService.get(id, options);
    }

    public void post(String id, A19flowDetail data, String[] fieldName) throws Exception {
        restService.post(id, data, fieldName);
    }

    public void put(String id, A19flowDetail data, String[] fieldName) throws Exception {
        restService.put(id, data, fieldName);
    }

    public void delete(String id, A19flowDetail data) throws Exception {
        restService.delete(id, data);
    }

    public void postList(List<A19flow> list, String[] fieldName) throws Exception {
        restService.postList(list, fieldName);
    }

    public void putList(List<A19flow> list, String[] fieldName) throws Exception {
        restService.putList(list, fieldName);
    }

    public void saveList(List<A19flow> list, String[] fieldName) throws Exception {
        restService.saveList(list, fieldName);
    }

    public void deleteList(List<A19flow> list) throws Exception {
        restService.deleteList(list);
    }

}
