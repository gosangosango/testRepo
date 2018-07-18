package kr.co.miracom.mes.a20.resource.simple.flow.model;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class A20Flow {
	private String id;
	private String flowCode;
	private String flowDesc;
	private String createUserId;
	private ZonedDateTime createTime;
	private String updateUserId;
	private ZonedDateTime updateTime;
}
