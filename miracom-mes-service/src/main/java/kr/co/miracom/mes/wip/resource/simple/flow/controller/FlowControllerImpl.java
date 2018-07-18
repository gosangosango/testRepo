package kr.co.miracom.mes.wip.resource.simple.flow.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.mes.wip.resource.simple.flow.model.Flow;
import kr.co.miracom.mes.wip.resource.simple.flow.model.FlowDetail;
import kr.co.miracom.mes.wip.resource.simple.flow.model.FlowOper;
import kr.co.miracom.mes.wip.resource.simple.flow.service.FlowOperService;
import kr.co.miracom.mes.wip.resource.simple.flow.service.FlowService;
import kr.co.miracom.mes.wip.resource.simple.flow.service.get_list.GetFlowList;
import kr.co.miracom.mes.wip.resource.simple.flow.service.get_list.GetFlowListIn;

/**
 * Setup Controller: Flow
 * @author myjung.jung
 * @since 2018. 06. 05.
 */
@CrossOrigin
@RestController
public class FlowControllerImpl implements FlowController {

    @Override
    public FlowDetail get(@PathVariable String id, SelectOptions options) throws Exception {
        return BeanUtils.get(FlowService.class).get(id, options);
    }

    @Override
    public void put(@PathVariable String id, @RequestBody FlowDetail data,
                    @RequestParam(required = false) String[] fieldName) throws Exception {
        BeanUtils.get(FlowService.class).put(id, data, fieldName);
    }

    @Override
    public GetListOut<Flow> getList(GetFlowListIn input) throws Exception {
        return BeanUtils.get(GetFlowList.class).getList(input);
    }

    @Override
    public void saveList(@RequestBody List<Flow> list, @RequestParam(required = false) String[] fieldName)
                    throws Exception {
        BeanUtils.get(FlowService.class).saveList(list, fieldName);
    }

    @Override
    public void deleteList(@RequestBody List<Flow> list) throws Exception {
        BeanUtils.get(FlowService.class).deleteList(list);
    }

    @Override
    public GetListOut<FlowOper> getOperList(@PathVariable String id) throws Exception {
        return BeanUtils.get(FlowOperService.class).getList(id);
    }

    @Override
    public void saveOperList(@PathVariable String id, @RequestBody List<FlowOper> list,
                    @RequestParam(required = false) String[] fieldName) throws Exception {
        BeanUtils.get(FlowOperService.class).saveList(id, list, fieldName);
    }

}
