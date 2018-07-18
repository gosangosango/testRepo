package kr.co.miracom.mes.a19.resource.simple.flow.service.get_list;


import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import kr.co.miracom.dbist.dml.Page;
import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.a19.model.MA19FlwDef;
import kr.co.miracom.mes.a19.resource.simple.flow.model.A19Flow;
import kr.co.miracom.mes.a19.resource.simple.flow.model.A19FlowOper;

public class GetA19FlowList {
		
	public GetListOut<A19Flow> getList(GetA19FlowListIn input) throws Exception{
		
		Query query = new Query(input.getPageNumber(), input.getPageSize());
		
		if(!ValueUtils.isEmpty(input.getFlowCode())) {
			//query.addFilter("flowCode", "like", "%" + input.getFlowCode() + "%");
			DbUtils.addContains(query, "flowCode", input.getFlowCode()); //addContains 함수를 통해 like 문법 쿼리 구현가능
			DbUtils.addEquals(query, "flowCode", input.getFlowCode());
			
		}
			
		if(!ValueUtils.isEmpty(input.getFlowDesc())) {
			query.addFilter("flowDesc", "like", "%" + input.getFlowDesc() + "%");
		}
		
		Page<A19Flow> page = DbUtils.selectPage(MA19FlwDef.class, query, A19Flow.class);
		
		GetListOut<A19Flow> output = new GetListOut<>();
		
		output.setList(page.getList());
		output.setTotalSize(page.getTotalSize());
		
		
		/*
		GetListOut<A19Flow> a19Flows = new GetListOut<A19Flow>();
		A19Flow a19Flow;
		List<A19Flow> list = new ArrayList<A19Flow>();
		for(int i = 0; i < 5; i++) {
			a19Flow = new A19Flow();
			a19Flow.setId("F00" + i);
			a19Flow.setFlowCode("F00" + i);
			a19Flow.setFlowDesc((i%2==0) ? "앙포장" : "중포장");
			list.add(a19Flow);
		}
		a19Flows.setList(list);
		a19Flows.setTotalSize(list.size());
		return a19Flows;
		 */
		return output;
	}
	

	
}
																																			