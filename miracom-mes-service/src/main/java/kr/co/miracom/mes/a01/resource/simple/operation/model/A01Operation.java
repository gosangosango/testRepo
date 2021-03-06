package kr.co.miracom.mes.a01.resource.simple.operation.model;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class A01Operation {
	private String id;
	private String operCode;
	private String operDesc;
	private String createUserId;
	private ZonedDateTime createTime;
	private String updateUserId;
	private ZonedDateTime updateTime;
}
