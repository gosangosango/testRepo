package kr.co.miracom.mes.a11.resource.simple.flow.model;

import java.time.ZonedDateTime;
import lombok.Data;

@Data
public class A11Flow {
	private String id;
	private String flowCode;
	private String flowDesc;
	private String createUserId;
	private ZonedDateTime creatTime;
	private String updaterUserId;
	private ZonedDateTime updateTime;
}
