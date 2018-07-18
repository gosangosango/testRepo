package kr.co.miracom.mes.wip.model;

import java.time.ZonedDateTime;

import kr.co.miracom.dbist.annotation.DbistRelation;
import kr.co.miracom.mes.wip.model.subtype.WipOprDef;
import lombok.Data;

@Data
public class MWipFlwOpr {
    private String factoryCode;
    private String flowCode;
    private String operCode;
    @DbistRelation(field = { "factoryCode", "operCode" })
    private WipOprDef oper;
    private int seqNo;
    private String prevOperCode;
    @DbistRelation(field = { "factoryCode", "prevOperCode" })
    private WipOprDef prevOper;
    private String nextOperCode;
    @DbistRelation(field = { "factoryCode", "nextOperCode" })
    private WipOprDef nextOper;
    private String createUserId;
    private ZonedDateTime createTime;
    private String updateUserId;
    private ZonedDateTime updateTime;
}
