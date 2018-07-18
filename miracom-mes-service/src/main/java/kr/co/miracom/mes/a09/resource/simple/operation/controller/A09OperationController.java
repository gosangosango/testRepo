package kr.co.miracom.mes.a09.resource.simple.operation.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.mes.a09.resource.simple.operation.model.A09Operation;
import kr.co.miracom.mes.a09.resource.simple.operation.model.A09OperationDetail;
import kr.co.miracom.mes.a09.resource.simple.operation.service.get_list.GetA09OperationListIn;

@RestController
@RequestMapping("/v1/a09")
public class A09OperationController {

	ArrayList<A09Operation> list = new ArrayList<>();
	
	@GetMapping(path= "/operations")
	public GetListOut<A09Operation> getList(GetA09OperationListIn input) throws Exception{
		
		A09Operation op1 = new A09Operation();
		op1.setOperCode("0001");
		op1.setOperDesc("조합");
		op1.setId(op1.getOperCode());
		list.add(op1);
		A09Operation op2 = new A09Operation();
		op2.setOperCode("0002");
		op2.setOperDesc("검사");
		op2.setId(op2.getOperCode());
		list.add(op2);
		A09Operation op3 = new A09Operation();
		op3.setOperCode("0003");
		op3.setOperDesc("소포장");
		op3.setId(op3.getOperCode());
		list.add(op3);
		A09Operation op4 = new A09Operation();
		op4.setId(op4.getOperCode());
		op4.setOperCode("0004");
		op4.setOperDesc("중포장");
		list.add(op4);
		A09Operation op5 = new A09Operation();
		op5.setOperCode("0005");
		op5.setOperDesc("대포장");
		op5.setId(op5.getOperCode());
		list.add(op5);
		
		GetListOut<A09Operation> rtn = new GetListOut<>();
		rtn.setList(list);
		rtn.setTotalSize(list.size());
		return rtn;
	}
	
	@GetMapping(path= "/operations/{id}")
	public A09OperationDetail get(@PathVariable String id) throws Exception{
		A09OperationDetail rtn = new A09OperationDetail();
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getOperCode() == id) {
				rtn = (A09OperationDetail) list.get(i);
				break;
			}
		}
		
		return rtn;
	}
}
