package kr.co.miracom.mes.a04.resource.simple.flow.service.get_list;

import kr.co.miracom.framework.service.GetListIn;
import lombok.Getter;
import lombok.Setter;

//검색조건에 input으로 사용될 DTO
@Getter
@Setter
public class GetA04FlowListIn extends GetListIn{
	private String flowCode;
	private String flowDesc;

}
