package kr.co.miracom.mes.a19.resource.simple.operation.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.mes.a19.resource.simple.flow.model.A19FlowOper;
import kr.co.miracom.mes.a19.resource.simple.flow.service.get_list.GetA19FlowListIn;
import kr.co.miracom.mes.a19.resource.simple.operation.model.A19Operation;
import kr.co.miracom.mes.a19.resource.simple.operation.model.A19OperationDetail;
import kr.co.miracom.mes.a19.resource.simple.operation.service.A19OperationService;
import kr.co.miracom.mes.a19.resource.simple.operation.service.get_list.GetA19OperationList;
import kr.co.miracom.mes.a19.resource.simple.operation.service.get_list.GetA19OperationListIn;

@RestController
@RequestMapping(path = "/v1")
public class A19OperationController {

	@GetMapping(path = "a19/operations")
	public GetListOut<A19Operation> getList(GetA19OperationListIn input) throws Exception{
		return BeanUtils.get(GetA19OperationList.class).getList(input);
	}
	
	@GetMapping(path = "a19/operations/{id}")
	public A19OperationDetail get(@PathVariable(required = true) String id) throws Exception{
		return BeanUtils.get(A19OperationService.class).get(id);
	}
	
	
	

}
