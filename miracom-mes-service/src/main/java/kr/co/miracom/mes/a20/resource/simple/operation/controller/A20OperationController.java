package kr.co.miracom.mes.a20.resource.simple.operation.controller;

import io.swagger.annotations.OAuth2Definition.Flow;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.mes.a20.resource.simple.flow.model.A20FlowDetail;
import kr.co.miracom.mes.wip.resource.simple.flow.model.FlowOper;
import kr.co.miracom.mes.wip.resource.simple.flow.service.get_list.GetFlowListIn;

public class A20OperationController {
	public GetListOut<Flow> getList(GetFlowListIn input) throws Exception{
		return null;
	}
	
	public A20FlowDetail get(String id) throws Exception{
		return null;	
	}
	public GetListOut<FlowOper> GetOperList(String id) throws Exception {
		return null;
	}
}
