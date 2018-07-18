package kr.co.miracom.mes.a00.resource.simple.operation.model;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class A00Operation {
	private String id;
	private String operCode;
	private String operDesc;
	private String createUserId;
	private ZonedDateTime createTime;
	private String updateUserId;
	private ZonedDateTime updateTime;
}
