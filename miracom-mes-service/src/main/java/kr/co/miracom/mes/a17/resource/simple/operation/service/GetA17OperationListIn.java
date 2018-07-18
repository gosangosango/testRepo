package kr.co.miracom.mes.a17.resource.simple.operation.service;

import kr.co.miracom.framework.service.GetListIn;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetA17OperationListIn extends GetListIn{

	private String operCode;
	private String operDesc;
}
