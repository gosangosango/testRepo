package kr.co.miracom.mes.a15.resource.simple.flow.model;

import java.time.ZonedDateTime;

import lombok.Data;

//ITEM DTO이다
//여러개 컴포넌티의 객체가 하나의 아이디로 인식된다!
@Data
public class A15Flow {
	private String id;
	private String flowCode;
	private String flowDesc;
	private String createUserId;
	private ZonedDateTime createtime;
	private String updateUserId;
	private ZonedDateTime updateTime;
}
