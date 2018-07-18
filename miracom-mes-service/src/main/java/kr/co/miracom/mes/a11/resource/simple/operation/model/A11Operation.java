package kr.co.miracom.mes.a11.resource.simple.operation.model;

import java.time.ZonedDateTime;
import lombok.Data;

@Data
public class A11Operation {
	private String id;
	private String OperCode;
	private String OperDesc;
	private String createUserId;
	private ZonedDateTime creatTime;
	private String updaterUserId;
	private ZonedDateTime updateTime;
}
