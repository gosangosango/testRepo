package kr.co.miracom.mes.a05.resource.simple.flow.service.get_list;

import kr.co.miracom.framework.service.GetListIn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class A05GetFlowListIn extends GetListIn {
	private String flowCode;
	private String flowDesc;

}
