package kr.co.miracom.mes.a19.model.subtype;

import java.time.ZonedDateTime;

import kr.co.miracom.dbist.annotation.DbistRelation;
import kr.co.miracom.dbist.annotation.DbistTable;
import lombok.Data;
@DbistTable(name = "MA19OPRDEF")
@Data
public class A19FlwOpr {
	private String flowCode;
	@DbistRelation(field = "flowCode")
	private A19FlwDef flow;
	private String operCode;
	@DbistRelation(field = "operCode")
	private A19FlwOpr oper;
	private String createUserId;
	private ZonedDateTime createTime;
	private String UpdateUserId;
	private ZonedDateTime UpdateTime;
	private int seqNo;
}
