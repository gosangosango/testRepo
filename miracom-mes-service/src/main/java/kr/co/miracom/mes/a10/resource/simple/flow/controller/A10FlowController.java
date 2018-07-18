package kr.co.miracom.mes.a10.resource.simple.flow.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.mes.a10.resource.simple.flow.model.A10Flow;
import kr.co.miracom.mes.a10.resource.simple.flow.model.A10FlowDetail;
import kr.co.miracom.mes.a10.resource.simple.flow.service.get_list.GetA10FlowListIn;
import kr.co.miracom.mes.a10.resource.simple.operation.model.A10Operation;

@RestController
@RequestMapping(path = "/v1")

public class A10FlowController {
	
	@GetMapping(path = "/a10/flows")
	public GetListOut<A10Flow> getList(GetA10FlowListIn input) throws Exception {
		List<A10Flow> list = new ArrayList<>();
		
		{
			A10Flow data = new A10Flow();
			data.setFlowCode("F001");
			data.setFlowDesc("모바일1");
			data.setId(data.getFlowCode());
			list.add(data);
		}
		{
			A10Flow data = new A10Flow();
			data.setFlowCode("F002");
			data.setFlowDesc("모바일2");
			data.setId(data.getFlowCode());
			list.add(data);
		}
		
		GetListOut<A10Flow> output = new GetListOut<>();
		output.setList(list);
		output.setTotalSize(list.size());
		return output;
	}
	
	@GetMapping(path = "/a10/flows/{id}")
	public A10FlowDetail get(String id) throws Exception {
		return null;
	}
	

}
