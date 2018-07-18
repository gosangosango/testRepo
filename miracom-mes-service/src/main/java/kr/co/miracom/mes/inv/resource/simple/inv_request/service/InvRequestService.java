package kr.co.miracom.mes.inv.resource.simple.inv_request.service;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.RestService;
import kr.co.miracom.mes.inv.model.MInvReqMst;
import kr.co.miracom.mes.inv.resource.simple.inv_request.model.InvRequest;
import kr.co.miracom.mes.inv.resource.simple.inv_request.model.InvRequestDetail;

/**
 * Setup Service: InvRequest
 * @author User
 * @since 2018. 07. 03.
 */
public class InvRequestService {
    private RestService<MInvReqMst, InvRequest, InvRequestDetail> restService = new RestService<>(MInvReqMst.class, InvRequest.class, InvRequestDetail.class);

    public InvRequestService() {
        // restService.setIdValueRule("UPPER");
        // restService.setPkFieldName("id");
        // restService.addFieldPattern("id", "^[-A-Z0-9.]*$");
    }

    public InvRequestDetail get(String id, SelectOptions options) throws Exception {
        return restService.get(id, options);
    }

    public void post(String id, InvRequestDetail data, String[] fieldName) throws Exception {
        restService.post(id, data, fieldName);
    }

    public void put(String id, InvRequestDetail data, String[] fieldName) throws Exception {
        restService.put(id, data, fieldName);
    }

    public void delete(String id, InvRequestDetail data) throws Exception {
        restService.delete(id, data);
    }

    public void postList(List<InvRequest> list, String[] fieldName) throws Exception {
        restService.postList(list, fieldName);
    }

    public void putList(List<InvRequest> list, String[] fieldName) throws Exception {
        restService.putList(list, fieldName);
    }

    public void saveList(List<InvRequest> list, String[] fieldName) throws Exception {
        restService.saveList(list, fieldName);
    }

    public void deleteList(List<InvRequest> list) throws Exception {
        restService.deleteList(list);
    }

}
