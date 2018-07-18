package kr.co.miracom.mes.a17.resource.simple.flow.model;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class A17Flow {
	private String id;
	private String flowCode;
	private String flowDesc;
	private String createUser;
	private ZonedDateTime createTime;
	private String updateUserId;
	private ZonedDateTime updateTime;


}
