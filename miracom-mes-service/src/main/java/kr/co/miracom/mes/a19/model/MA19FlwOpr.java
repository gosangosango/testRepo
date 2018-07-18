package kr.co.miracom.mes.a19.model;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class MA19FlwOpr {
	private String flowCode;
	private String operCode;
	private String createUserId;
	private ZonedDateTime createTime;
	private String UpdateUserId;
	private ZonedDateTime UpdateTime;
	private int seqNo;
}
