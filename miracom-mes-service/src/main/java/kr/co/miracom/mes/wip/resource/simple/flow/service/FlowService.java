package kr.co.miracom.mes.wip.resource.simple.flow.service;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.RestService;
import kr.co.miracom.mes.wip.model.MWipFlwDef;
import kr.co.miracom.mes.wip.resource.simple.flow.model.Flow;
import kr.co.miracom.mes.wip.resource.simple.flow.model.FlowDetail;

/**
 * Setup Service: Flow
 * @author myjung.jung
 * @since 2018. 06. 05.
 */
public class FlowService {
    private RestService<MWipFlwDef, Flow, FlowDetail> restService = new RestService<>(MWipFlwDef.class, Flow.class,
                    FlowDetail.class);

    public FlowDetail get(String id, SelectOptions options) throws Exception {
        return restService.get(id, options);
    }

    public void post(String id, FlowDetail data, String[] fieldName) throws Exception {
        restService.post(id, data, fieldName);
    }

    public void put(String id, FlowDetail data, String[] fieldName) throws Exception {
        restService.put(id, data, fieldName);
    }

    public void delete(String id, FlowDetail data) throws Exception {
        restService.delete(id, data);
    }

    public void postList(List<Flow> list, String[] fieldName) throws Exception {
        restService.postList(list, fieldName);
    }

    public void putList(List<Flow> list, String[] fieldName) throws Exception {
        restService.putList(list, fieldName);
    }

    public void saveList(List<Flow> list, String[] fieldName) throws Exception {
        restService.saveList(list, fieldName);
    }

    public void deleteList(List<Flow> list) throws Exception {
        restService.deleteList(list);
    }

}
