package kr.co.miracom.mes.a11.resource.simple.operation.service.get_list;

import kr.co.miracom.framework.service.GetListIn;
import lombok.Data;

@Data
public class GetA11OperationListIn extends GetListIn {
	private String flowCode;
	private String flowDesc;
}
