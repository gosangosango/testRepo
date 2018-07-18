package kr.co.miracom.mes.a09.resource.simple.operation.model;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class A09Operation {
	private String id;
	private String operDesc;
	private String operCode;
	private ZonedDateTime createTime;
	private String createUserId;
	private String updateUserId;
	private ZonedDateTime updateTime;

}
