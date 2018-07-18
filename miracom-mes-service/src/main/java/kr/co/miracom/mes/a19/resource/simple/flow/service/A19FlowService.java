package kr.co.miracom.mes.a19.resource.simple.flow.service;





import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.mes.a19.model.MA19FlwDef;
import kr.co.miracom.mes.a19.resource.simple.flow.model.A19FlowDetail;

public class A19FlowService {
		
	public A19FlowDetail get(String id) throws Exception {
		
	
		
		return DbUtils.select(MA19FlwDef.class, id, null, A19FlowDetail.class, true);
		
	}
}
