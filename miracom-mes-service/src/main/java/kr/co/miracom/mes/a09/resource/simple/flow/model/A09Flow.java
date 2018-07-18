package kr.co.miracom.mes.a09.resource.simple.flow.model;
import java.time.ZonedDateTime;
import lombok.Data;

@Data
public class A09Flow {
	private String id;
	private String flowCode;
	private String createUserId;
	private ZonedDateTime createTime;
	private String updateUserId;
	private ZonedDateTime updateTime;

}
