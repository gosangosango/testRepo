package kr.co.miracom.mes.a17.resource.simple.operation.model;

import java.time.ZonedDateTime;

import lombok.Data;


@Data
public class A17Operation {

	private String id;
	private String operCode;
	private String operDesc;
	private String createUser;
	private ZonedDateTime createTime;
	private String updateUserId;
	private ZonedDateTime updateTime;
}


