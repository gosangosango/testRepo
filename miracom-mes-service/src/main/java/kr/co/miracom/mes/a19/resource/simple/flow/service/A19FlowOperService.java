package kr.co.miracom.mes.a19.resource.simple.flow.service;

import java.util.List;

import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.mes.a19.model.MA19FlwDef;
import kr.co.miracom.mes.a19.model.MA19FlwOpr;
import kr.co.miracom.mes.a19.resource.simple.flow.model.A19FlowOper;

public class A19FlowOperService {

	public List<A19FlowOper> getList(String id) throws Exception{
		MA19FlwDef parent = DbUtils.select(MA19FlwDef.class, id, true);
		return DbUtils.selectOneToManyList(MA19FlwOpr.class, parent, A19FlowOper.class);
	}
	
}
