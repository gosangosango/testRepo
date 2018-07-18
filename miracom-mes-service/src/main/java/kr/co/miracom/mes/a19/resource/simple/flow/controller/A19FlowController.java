package kr.co.miracom.mes.a19.resource.simple.flow.controller;



import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.mes.a19.resource.simple.flow.model.A19Flow;
import kr.co.miracom.mes.a19.resource.simple.flow.model.A19FlowDetail;
import kr.co.miracom.mes.a19.resource.simple.flow.model.A19FlowOper;
import kr.co.miracom.mes.a19.resource.simple.flow.service.A19FlowOperService;
import kr.co.miracom.mes.a19.resource.simple.flow.service.A19FlowService;
import kr.co.miracom.mes.a19.resource.simple.flow.service.get_list.GetA19FlowList;
import kr.co.miracom.mes.a19.resource.simple.flow.service.get_list.GetA19FlowListIn;

@RestController
@RequestMapping(path = "/v1")
public class A19FlowController {
	
	@GetMapping(path = "a19/flows")
	public GetListOut<A19Flow> getList(GetA19FlowListIn input) throws Exception{
		
		return BeanUtils.get(GetA19FlowList.class).getList(input);
		
	}
	
	@GetMapping(path = "a19/flows/{id}")
	public A19FlowDetail get(@PathVariable(required = true) String id) throws Exception {
	
		return BeanUtils.get(A19FlowService.class).get(id);
		
	}
	
	@GetMapping(path = "a19/flows/{id}/opers")
	public List<A19FlowOper> getOperList(@PathVariable(required = true)String id) throws Exception{
		return BeanUtils.get(A19FlowOperService.class).getList(id);
	}
	
}
