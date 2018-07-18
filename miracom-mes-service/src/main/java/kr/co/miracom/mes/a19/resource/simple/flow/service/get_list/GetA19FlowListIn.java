package kr.co.miracom.mes.a19.resource.simple.flow.service.get_list;


import kr.co.miracom.framework.service.GetListIn;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
//검색 조건에 파라미터로 사용될 클래스
public class GetA19FlowListIn extends GetListIn{

	private String flowCode;
	private String flowDesc;
	
}
