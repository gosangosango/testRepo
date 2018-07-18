package kr.co.miracom.mes.a19.resource.simple.operation.service;



import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.mes.a19.model.MA19OprDef;
import kr.co.miracom.mes.a19.resource.simple.operation.model.A19OperationDetail;


public class A19OperationService {

	
	public A19OperationDetail get(String id) throws Exception{
		return DbUtils.select(MA19OprDef.class, id,  null, A19OperationDetail.class, true);
	}
	
}
