package kr.co.miracom.mes.a07.resource.simple.flow.model;

import java.time.ZonedDateTime;

import lombok.Data;
@Data
public class FlowOper {
	private String id;
	private String flowCode;
	private String operDesc;
	private String createUserId;
	private ZonedDateTime createTime;
	private String updateUserId;
	private ZonedDateTime updateTime;
}
