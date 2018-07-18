package kr.co.miracom.mes.wip.resource.simple.flow.controller;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.mes.wip.resource.simple.flow.model.Flow;
import kr.co.miracom.mes.wip.resource.simple.flow.model.FlowDetail;
import kr.co.miracom.mes.wip.resource.simple.flow.model.FlowOper;
import kr.co.miracom.mes.wip.resource.simple.flow.service.get_list.GetFlowListIn;

/**
 * Setup Controller: Flow
 * @author myjung.jung
 * @since 2018. 06. 05.
 */
public class FlowControllerStub implements FlowController {

    @Override
    public FlowDetail get(String id, SelectOptions options) throws Exception {
        return null;
    }

    @Override
    public void put(String id, FlowDetail data, String[] fieldName) throws Exception {

    }

    @Override
    public GetListOut<Flow> getList(GetFlowListIn input) throws Exception {
        return null;
    }

    @Override
    public void saveList(List<Flow> list, String[] fieldName) throws Exception {

    }

    @Override
    public void deleteList(List<Flow> list) throws Exception {

    }

    @Override
    public GetListOut<FlowOper> getOperList(String id) throws Exception {
        return null;
    }

    @Override
    public void saveOperList(String id, List<FlowOper> list, String[] fieldName) throws Exception {

    }

}
