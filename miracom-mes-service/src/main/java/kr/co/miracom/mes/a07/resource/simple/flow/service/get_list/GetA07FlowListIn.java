package kr.co.miracom.mes.a07.resource.simple.flow.service.get_list;

import kr.co.miracom.framework.service.GetListIn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetA07FlowListIn extends GetListIn {
	private String flowCode;
	private String flowDesc;

}
