package kr.co.miracom.mes.a15.resource.simple.operation.model;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class A15Operation {
	private String id;
	private String operCode;
	private String operDesc;
	private String createUserId;
	private ZonedDateTime createtime;
	private String updateUserId;
	private ZonedDateTime updateTime;
}
