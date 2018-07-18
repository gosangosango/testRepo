package kr.co.miracom.mes.a19.resource.simple.operation.service.get_list;

import kr.co.miracom.framework.service.GetListIn;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetA19OperationListIn extends GetListIn{

	private String operCode;
	private String operDesc;
	
}
