package kr.co.miracom.mes.a19.resource.simple.operation.service.get_list;

import kr.co.miracom.dbist.dml.Page;
import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.a19.model.MA19OprDef;
import kr.co.miracom.mes.a19.resource.simple.operation.model.A19Operation;

public class GetA19OperationList {
	
	public GetListOut<A19Operation> getList(GetA19OperationListIn input) throws Exception{
		Query query = new Query(input.getPageNumber(), input.getPageSize());
		
		if(!ValueUtils.isEmpty(input.getOperCode())) {
			query.addFilter("operCode", "like", "%" + input.getOperCode() + "%");
		}
		
		if(!ValueUtils.isEmpty(input.getOperDesc())) {
			query.addFilter("operDesc", "like", "%" + input.getOperDesc() + "%");
		}
		
		Page<A19Operation> page = DbUtils.selectPage(MA19OprDef.class, query, A19Operation.class);
		
		GetListOut<A19Operation> output = new GetListOut<>();
		
		output.setList(page.getList());
		output.setTotalSize(page.getTotalSize());
		
		return output;
	}
}
