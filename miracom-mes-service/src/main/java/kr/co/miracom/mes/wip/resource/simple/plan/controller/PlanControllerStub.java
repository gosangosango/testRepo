package kr.co.miracom.mes.wip.resource.simple.plan.controller;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.mes.wip.resource.simple.plan.model.Plan;
import kr.co.miracom.mes.wip.resource.simple.plan.model.PlanDetail;
import kr.co.miracom.mes.wip.resource.simple.plan.service.get_list.GetPlanListIn;

/**
 * Setup Controller: Plan
 * @author myjung.jung
 * @since 2018. 06. 19.
 */
public class PlanControllerStub implements PlanController {

    @Override
    public PlanDetail get(String id, SelectOptions options) throws Exception {
        return null;
    }

    @Override
    public void put(String id, PlanDetail data, String[] fieldName) throws Exception {

    }

    @Override
    public GetListOut<Plan> getList(GetPlanListIn input) throws Exception {
        return null;
    }

    @Override
    public void saveList(List<Plan> list, String[] fieldName) throws Exception {

    }

    @Override
    public void deleteList(List<Plan> list) throws Exception {

    }

}
