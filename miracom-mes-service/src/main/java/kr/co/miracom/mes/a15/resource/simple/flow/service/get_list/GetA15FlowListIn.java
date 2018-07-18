package kr.co.miracom.mes.a15.resource.simple.flow.service.get_list;

import kr.co.miracom.framework.service.GetListIn;
import lombok.Getter;
import lombok.Setter;


//검색환경에 파라미터를 가질 DTO
@Setter
@Getter
public class GetA15FlowListIn extends GetListIn{
	
	private String flowCode;
	private String flowDesc;
}
