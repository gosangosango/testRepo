package kr.co.miracom.mes.a09.resource.simple.flow.service.get_list;
import kr.co.miracom.framework.service.GetListIn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetA09FlowListIn extends GetListIn{
	private String flowCode;
	private String flowDesc;

}
