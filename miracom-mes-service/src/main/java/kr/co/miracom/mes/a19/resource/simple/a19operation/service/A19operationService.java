package kr.co.miracom.mes.a19.resource.simple.a19operation.service;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.RestService;
import kr.co.miracom.mes.a19.model.MA19OprDef;
import kr.co.miracom.mes.a19.resource.simple.a19operation.model.A19operation;
import kr.co.miracom.mes.a19.resource.simple.a19operation.model.A19operationDetail;

/**
 * Setup Service: A19operation
 * @author Zotac023
 * @since 2018. 07. 17.
 */
public class A19operationService {
    private RestService<MA19OprDef, A19operation, A19operationDetail> restService = new RestService<>(MA19OprDef.class, A19operation.class, A19operationDetail.class);

    public A19operationService() {
        // restService.setIdValueRule("UPPER");
        // restService.setPkFieldName("id");
        // restService.addFieldPattern("id", "^[-A-Z0-9.]*$");
    }

    public A19operationDetail get(String id, SelectOptions options) throws Exception {
        return restService.get(id, options);
    }

    public void post(String id, A19operationDetail data, String[] fieldName) throws Exception {
        restService.post(id, data, fieldName);
    }

    public void put(String id, A19operationDetail data, String[] fieldName) throws Exception {
        restService.put(id, data, fieldName);
    }

    public void delete(String id, A19operationDetail data) throws Exception {
        restService.delete(id, data);
    }

    public void postList(List<A19operation> list, String[] fieldName) throws Exception {
        restService.postList(list, fieldName);
    }

    public void putList(List<A19operation> list, String[] fieldName) throws Exception {
        restService.putList(list, fieldName);
    }

    public void saveList(List<A19operation> list, String[] fieldName) throws Exception {
        restService.saveList(list, fieldName);
    }

    public void deleteList(List<A19operation> list) throws Exception {
        restService.deleteList(list);
    }

}
