package kr.co.miracom.mes.a19.model.subtype;

import java.time.ZonedDateTime;

import kr.co.miracom.dbist.annotation.DbistTable;
import lombok.Data;
@DbistTable(name = "MA19FLWDEF")
@Data
public class A19FlwDef {
	private String flowCode;
	private String flowDesc;
	private String createUserId;
	private ZonedDateTime createTime;
	private String UpdateUserId;
	private ZonedDateTime UpdateTime;
}
