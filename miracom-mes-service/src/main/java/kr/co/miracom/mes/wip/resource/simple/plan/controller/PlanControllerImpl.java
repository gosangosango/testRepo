package kr.co.miracom.mes.wip.resource.simple.plan.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.mes.wip.resource.simple.plan.model.Plan;
import kr.co.miracom.mes.wip.resource.simple.plan.model.PlanDetail;
import kr.co.miracom.mes.wip.resource.simple.plan.service.PlanService;
import kr.co.miracom.mes.wip.resource.simple.plan.service.get_list.GetPlanList;
import kr.co.miracom.mes.wip.resource.simple.plan.service.get_list.GetPlanListIn;

/**
 * Setup Controller: Plan
 * @author myjung.jung
 * @since 2018. 06. 19.
 */
@CrossOrigin
@RestController
public class PlanControllerImpl implements PlanController {

    @Override
    public PlanDetail get(@PathVariable String id, SelectOptions options) throws Exception {
        return BeanUtils.get(PlanService.class).get(id, options);
    }

    @Override
    public void put(@PathVariable String id, @RequestBody PlanDetail data,
                    @RequestParam(required = false) String[] fieldName) throws Exception {
        BeanUtils.get(PlanService.class).put(id, data, fieldName);
    }

    @Override
    public GetListOut<Plan> getList(GetPlanListIn input) throws Exception {
        return BeanUtils.get(GetPlanList.class).getList(input);
    }

    @Override
    public void saveList(@RequestBody List<Plan> list, @RequestParam(required = false) String[] fieldName)
                    throws Exception {
        BeanUtils.get(PlanService.class).saveList(list, fieldName);
    }

    @Override
    public void deleteList(@RequestBody List<Plan> list) throws Exception {
        BeanUtils.get(PlanService.class).deleteList(list);
    }

}
