package kr.co.miracom.mes.a05.resource.simple.flow.model;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class A05Flow {
	private String id;
	private String flowCode;
	private String flowDesc;
	private String createUserId;
	private String updateUserId;
	private ZonedDateTime createTime;
	private ZonedDateTime updateTime;
}
