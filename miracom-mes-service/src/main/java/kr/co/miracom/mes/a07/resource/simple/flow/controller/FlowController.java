package kr.co.miracom.mes.a07.resource.simple.flow.controller;

import io.swagger.annotations.OAuth2Definition.Flow;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.mes.a07.resource.simple.flow.model.FlowOper;
import kr.co.miracom.mes.wip.resource.simple.flow.model.FlowDetail;
import kr.co.miracom.mes.wip.resource.simple.flow.service.get_list.GetFlowListIn;

public class FlowController {
	
	public GetListOut<Flow> getList(GetFlowListIn input) throws Exception {
		return null;	
	}
	public FlowDetail get(String id) throws Exception{
		return null;
	}
	public GetListOut<FlowOper> getOperList(String id) throws Exception{
		return null;
	}
	
}
