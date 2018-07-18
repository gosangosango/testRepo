package kr.co.miracom.mes.a13.resource.simple.flow.service.get_list;

import kr.co.miracom.framework.service.GetListIn;
import lombok.Getter;
import lombok.Setter;

// 검색 조건을 설정할 DTO
@Getter
@Setter
public class GetA13FlowListIn extends GetListIn{
	private String flowCode;
	private String flowDesc;
}
