package kr.co.miracom.mes.inv.resource.simple.ret_mat.service;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.RestService;
import kr.co.miracom.mes.inv.model.MInvRetMat;
import kr.co.miracom.mes.inv.resource.simple.ret_mat.model.RetMat;
import kr.co.miracom.mes.inv.resource.simple.ret_mat.model.RetMatDetail;

/**
 * Setup Service: RetMat
 * @author mo21.kim
 * @since 2018. 07. 06.
 */
public class RetMatService {
    private RestService<MInvRetMat, RetMat, RetMatDetail> restService = new RestService<>(MInvRetMat.class, RetMat.class, RetMatDetail.class);

    public RetMatService() {
        // restService.setIdValueRule("UPPER");
        // restService.setPkFieldName("id");
        // restService.addFieldPattern("id", "^[-A-Z0-9.]*$");
    }

    public RetMatDetail get(String id, SelectOptions options) throws Exception {
        return restService.get(id, options);
    }

    public void post(String id, RetMatDetail data, String[] fieldName) throws Exception {
        restService.post(id, data, fieldName);
    }

    public void put(String id, RetMatDetail data, String[] fieldName) throws Exception {
        restService.put(id, data, fieldName);
    }

    public void delete(String id, RetMatDetail data) throws Exception {
        restService.delete(id, data);
    }

    public void postList(List<RetMat> list, String[] fieldName) throws Exception {
        restService.postList(list, fieldName);
    }

    public void putList(List<RetMat> list, String[] fieldName) throws Exception {
        restService.putList(list, fieldName);
    }

    public void saveList(List<RetMat> list, String[] fieldName) throws Exception {
        restService.saveList(list, fieldName);
    }

    public void deleteList(List<RetMat> list) throws Exception {
        restService.deleteList(list);
    }
}
