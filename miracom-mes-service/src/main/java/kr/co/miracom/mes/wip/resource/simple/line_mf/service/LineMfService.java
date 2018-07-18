package kr.co.miracom.mes.wip.resource.simple.line_mf.service;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.RestService;
import kr.co.miracom.mes.wip.model.MWipMatFlw;
import kr.co.miracom.mes.wip.resource.simple.line_mf.model.LineMf;
import kr.co.miracom.mes.wip.resource.simple.line_mf.model.LineMfDetail;

/**
 * Setup Service: LineMf
 * @author mo21.kim
 * @since 2018. 07. 03.
 */
public class LineMfService {
    private RestService<MWipMatFlw, LineMf, LineMfDetail> restService = new RestService<>(MWipMatFlw.class, LineMf.class, LineMfDetail.class);

    public LineMfService() {
        // restService.setIdValueRule("UPPER");
        // restService.setPkFieldName("id");
        // restService.addFieldPattern("id", "^[-A-Z0-9.]*$");
    }

    public LineMfDetail get(String id, SelectOptions options) throws Exception {
        return restService.get(id, options);
    }

    public void post(String id, LineMfDetail data, String[] fieldName) throws Exception {
        restService.post(id, data, fieldName);
    }

    public void put(String id, LineMfDetail data, String[] fieldName) throws Exception {
        restService.put(id, data, fieldName);
    }

    public void delete(String id, LineMfDetail data) throws Exception {
        restService.delete(id, data);
    }

    public void postList(List<LineMf> list, String[] fieldName) throws Exception {
        restService.postList(list, fieldName);
    }

    public void putList(List<LineMf> list, String[] fieldName) throws Exception {
        restService.putList(list, fieldName);
    }

    public void saveList(List<LineMf> list, String[] fieldName) throws Exception {
        restService.saveList(list, fieldName);
    }

    public void deleteList(List<LineMf> list) throws Exception {
        restService.deleteList(list);
    }

}
