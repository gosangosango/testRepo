package kr.co.miracom.mes.a16.resource.simple.operation.model;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class A16Operation {

	private String id;
	private String operCode;
	private String operDesc;
	private String createUseId;
	private ZonedDateTime createTime;
	private String updateUserId;
	private ZonedDateTime updateTime;
}
	
