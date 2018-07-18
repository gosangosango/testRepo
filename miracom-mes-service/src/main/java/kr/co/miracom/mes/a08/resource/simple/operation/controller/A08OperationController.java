package kr.co.miracom.mes.a08.resource.simple.operation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.mes.a08.resource.simple.operation.model.A08Operation;
import kr.co.miracom.mes.a08.resource.simple.operation.service.A08OperationService;
import kr.co.miracom.mes.a08.resource.simple.operation.service.get_list.GetA08OperationList;
import kr.co.miracom.mes.wip.resource.simple.flow.model.FlowDetail;
import kr.co.miracom.mes.wip.resource.simple.flow.service.get_list.GetFlowListIn;

@RestController
@RequestMapping(path = "/v1")
public class A08OperationController {
	@GetMapping(path = "/a08/operation")
	public GetListOut<A08Operation> getList(GetFlowListIn input) throws Exception {
		return BeanUtils.get(GetA08OperationList.class).getList(input);
	}
	
	@GetMapping(path = "/a08/operation/{id}")
	public FlowDetail get(@PathVariable String id) throws Exception {
		return BeanUtils.get(A08OperationService.class).get(id);
	}
}
