package kr.co.miracom.mes.a19.model;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class MA19OprDef {
	private String operCode;
	private String operDesc;
	private String createUserId;
	private ZonedDateTime createTime;
	private String UpdateUserId;
	private ZonedDateTime UpdateTime;
}
