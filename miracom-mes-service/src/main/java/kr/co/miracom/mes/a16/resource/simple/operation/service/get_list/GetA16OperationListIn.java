package kr.co.miracom.mes.a16.resource.simple.operation.service.get_list;

import kr.co.miracom.framework.service.GetListIn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetA16OperationListIn extends GetListIn {
	private String operCode;
	private String operDesc;
}
