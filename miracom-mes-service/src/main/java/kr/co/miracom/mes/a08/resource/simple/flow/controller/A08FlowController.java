package kr.co.miracom.mes.a08.resource.simple.flow.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.mes.a08.resource.simple.flow.model.A08Flow;
import kr.co.miracom.mes.wip.resource.simple.flow.model.FlowDetail;
import kr.co.miracom.mes.wip.resource.simple.flow.service.get_list.GetFlowListIn;

@RestController
@RequestMapping(path = "/v1")
public class A08FlowController {
	@GetMapping(path = "/a08/flows")
	public GetListOut<A08Flow> getList(GetFlowListIn input) throws Exception {
		return null;
	}
	
	@GetMapping(path = "/a08/flows/{id}")
	public FlowDetail get(@PathVariable String id) throws Exception {
		return null;
	}
}
