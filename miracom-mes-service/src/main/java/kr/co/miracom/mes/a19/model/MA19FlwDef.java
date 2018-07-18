package kr.co.miracom.mes.a19.model;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class MA19FlwDef {
	private String flowCode;
	private String flowDesc;
	private String createUserId;
	private ZonedDateTime createTime;
	private String UpdateUserId;
	private ZonedDateTime UpdateTime;
}
