package kr.co.miracom.mes.a19.resource.simple.operation.model;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class A19Operation {
	
	private String id;//DB컬럼에 없지만 컴포지트된PK가있을경우 이 필드에 저장되어 REST 호출시에 사용됨
	private String operCode;
	private String operDesc;
	private String createUserId;
	private ZonedDateTime createTime;
	private String UpdateUserId;
	private ZonedDateTime UpdateTime;
}
