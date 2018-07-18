package kr.co.miracom.mes.a09.resource.simple.flow.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.mes.a09.resource.simple.flow.model.A09Flow;
import kr.co.miracom.mes.a09.resource.simple.flow.model.A09FlowDetail;
import kr.co.miracom.mes.a09.resource.simple.flow.service.get_list.GetA09FlowListIn;

@RestController
@RequestMapping("/v1/a09")
public class A09FlowController {

	@GetMapping(path= "/flows")
	public GetListOut<A09Flow> getList(GetA09FlowListIn input) throws Exception{
		return null;
	}
	
	@GetMapping(path= "/flows/{id}")
	public A09FlowDetail get(@PathVariable String id ) throws Exception{
		return null;
	}
}
