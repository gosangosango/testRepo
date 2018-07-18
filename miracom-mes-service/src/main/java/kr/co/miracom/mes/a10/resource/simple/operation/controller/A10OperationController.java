package kr.co.miracom.mes.a10.resource.simple.operation.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.mes.a10.resource.simple.operation.model.A10Operation;
import kr.co.miracom.mes.a10.resource.simple.operation.model.A10OperationDetail;
import kr.co.miracom.mes.a10.resource.simple.operation.service.get_list.GetA10OperationListIn;

@RestController
@RequestMapping(path = "/v1")

public class A10OperationController {
	
	@GetMapping(path = "/a10/operations")
	public GetListOut<A10Operation> getList(GetA10OperationListIn input) throws Exception {
		List<A10Operation> list = new ArrayList<>();
		
		{
			A10Operation data = new A10Operation();
			data.setOperCode("O001");
			data.setOperDesc("조립");
			data.setId(data.getOperCode());
			list.add(data);
		}
		{
			A10Operation data = new A10Operation();
			data.setOperCode("O002");
			data.setOperDesc("검사");
			data.setId(data.getOperCode());
			list.add(data);
		}
		{
			A10Operation data = new A10Operation();
			data.setOperCode("O003");
			data.setOperDesc("소포장");
			data.setId(data.getOperCode());
			list.add(data);
		}
		{
			A10Operation data = new A10Operation();
			data.setOperCode("O004");
			data.setOperDesc("중포장");
			data.setId(data.getOperCode());
			list.add(data);
		}
		{
			A10Operation data = new A10Operation();
			data.setOperCode("O005");
			data.setOperDesc("대포장");
			data.setId(data.getOperCode());
			list.add(data);
		}
		
		GetListOut<A10Operation> output = new GetListOut<>();
		output.setList(list);
		output.setTotalSize(list.size());
		return output;
	}
	
	@GetMapping(path = "/a10/operations/{id}")
	public A10OperationDetail get(String id) throws Exception {
		return null;
	}
	

}
