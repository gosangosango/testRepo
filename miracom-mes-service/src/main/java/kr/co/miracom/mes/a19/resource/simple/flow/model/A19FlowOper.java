package kr.co.miracom.mes.a19.resource.simple.flow.model;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class A19FlowOper {

	private String id;
	private String flowCode;
	private String operCode;
	private String createUserId;
	private ZonedDateTime createTime;
	private String UpdateUserId;
	private ZonedDateTime UpdateTime;
	private int seqNo;
}


