package kr.co.miracom.mes.a13.resource.simple.flow.model;

import java.time.ZonedDateTime;
import lombok.Data;

// 그리드에 표시될 아이템 DTO
@Data
public class A13Flow {
	private String id;
	private String flowCode;
	private String flowDesc;
	private String createUserId;
	private ZonedDateTime createTime;
	private String updateUserId;
	private ZonedDateTime updateTime;
}
