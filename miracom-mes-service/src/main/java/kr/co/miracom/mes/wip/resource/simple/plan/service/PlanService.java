package kr.co.miracom.mes.wip.resource.simple.plan.service;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.RestService;
import kr.co.miracom.mes.wip.model.MWipPlnDef;
import kr.co.miracom.mes.wip.resource.simple.plan.model.Plan;
import kr.co.miracom.mes.wip.resource.simple.plan.model.PlanDetail;

/**
 * Setup Service: Plan
 * @author myjung.jung
 * @since 2018. 06. 19.
 */
public class PlanService {
    private RestService<MWipPlnDef, Plan, PlanDetail> restService = new RestService<>(MWipPlnDef.class, Plan.class,
                    PlanDetail.class);

    public PlanDetail get(String id, SelectOptions options) throws Exception {
        return restService.get(id, options);
    }

    public void post(String id, PlanDetail data, String[] fieldName) throws Exception {
        restService.post(id, data, fieldName);
    }

    public void put(String id, PlanDetail data, String[] fieldName) throws Exception {
        restService.put(id, data, fieldName);
    }

    public void delete(String id, PlanDetail data) throws Exception {
        restService.delete(id, data);
    }

    public void postList(List<Plan> list, String[] fieldName) throws Exception {
        restService.postList(list, fieldName);
    }

    public void putList(List<Plan> list, String[] fieldName) throws Exception {
        restService.putList(list, fieldName);
    }

    public void saveList(List<Plan> list, String[] fieldName) throws Exception {
        restService.saveList(list, fieldName);
    }

    public void deleteList(List<Plan> list) throws Exception {
        restService.deleteList(list);
    }

}
