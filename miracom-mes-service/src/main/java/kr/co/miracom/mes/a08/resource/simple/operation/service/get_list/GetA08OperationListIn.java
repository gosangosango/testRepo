package kr.co.miracom.mes.a08.resource.simple.operation.service.get_list;

import kr.co.miracom.framework.service.GetListIn;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class GetA08OperationListIn extends GetListIn {
	private String operCode;
	private String operDesc;
}
