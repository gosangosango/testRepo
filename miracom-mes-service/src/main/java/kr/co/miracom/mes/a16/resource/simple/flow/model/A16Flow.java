package kr.co.miracom.mes.a16.resource.simple.flow.model;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class A16Flow {
	
	private String id;
	private String flowCode;
	private String flowDesc;
	private String createUseId;
	private ZonedDateTime createTime;
	private String updateUserId;
	private ZonedDateTime updateTime;
}
