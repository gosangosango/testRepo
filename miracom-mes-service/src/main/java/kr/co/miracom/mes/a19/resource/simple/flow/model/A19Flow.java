package kr.co.miracom.mes.a19.resource.simple.flow.model;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
//리스트에 출력될 각각의 아이템과 링크되는 클래스
public class A19Flow {
	private String id;
	private String flowCode;
	private String flowDesc;
	private String createUserId;
	private ZonedDateTime createTime;
	private String UpdateUserId;
	private ZonedDateTime UpdateTime;
}
