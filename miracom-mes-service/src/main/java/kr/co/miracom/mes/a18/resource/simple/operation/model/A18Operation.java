package kr.co.miracom.mes.a18.resource.simple.operation.model;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class A18Operation {
	private String id;
	private String flowCode;
	private String flowDesc;
	private String createUserId;
	private ZonedDateTime createTime;
	private String updateUserId;
	private ZonedDateTime updateTime;
}
