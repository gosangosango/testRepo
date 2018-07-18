package kr.co.miracom.mes.a05.resource.simple.flow.controller;

import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.mes.a05.resource.simple.flow.model.A05FlowDetail;
import kr.co.miracom.mes.a05.resource.simple.flow.service.get_list.A05GetFlowListIn;
import kr.co.miracom.mes.wip.resource.simple.flow.model.Flow;
import kr.co.miracom.mes.wip.resource.simple.flow.model.FlowOper;

public class A05FlowController {
	public GetListOut<Flow>  getList(A05GetFlowListIn input) throws Exception{
		return null;
	}
	public A05FlowDetail get(String id) {
		return null;
		
	}
	public GetListOut<FlowOper> getOperList(String id) throws Exception{
		return null;
		
	}
}
