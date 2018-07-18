package kr.co.miracom.mes.a08.resource.simple.operation.service.get_list;

import java.util.ArrayList;
import java.util.List;

import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.mes.a08.resource.simple.operation.model.A08Operation;
import kr.co.miracom.mes.wip.resource.simple.flow.service.get_list.GetFlowListIn;

public class GetA08OperationList {
	public GetListOut<A08Operation> getList(GetFlowListIn input) throws Exception {
		List<A08Operation> list1 = new ArrayList<A08Operation>();
		{
			A08Operation data = new A08Operation();
			data.setOperCode("0001");
			data.setOperDesc("메롱");
			data.setId(data.getOperCode());
			list1.add(data);
		}
		
		GetListOut<A08Operation> output = new GetListOut<>();
		output.setList(list1);
		output.setTotalSize(list1.size());
		return output;
	}
}
