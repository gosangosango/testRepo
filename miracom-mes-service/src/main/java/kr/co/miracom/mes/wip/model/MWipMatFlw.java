package kr.co.miracom.mes.wip.model;

import java.time.ZonedDateTime;

import kr.co.miracom.dbist.annotation.DbistRelation;
import kr.co.miracom.mes.wip.model.subtype.WipFlwDef;
import kr.co.miracom.mes.wip.model.subtype.WipLinDef;
import kr.co.miracom.mes.wip.model.subtype.WipMatDef;

import lombok.Data;

@Data
public class MWipMatFlw {
    private String factoryCode;
    
    private String matCode;
    @DbistRelation(field = { "factoryCode", "matCode" })
    private WipMatDef mat;
    
    private String flowCode;
    @DbistRelation(field = { "factoryCode", "flowCode" })
    private WipFlwDef flow;
    private String lineCode;
    @DbistRelation(field = { "factoryCode", "lineCode" })
    private WipLinDef line;
    private int seqNo;
    private String createUserId;
    private ZonedDateTime createTime;
    private String updateUserId;
    private ZonedDateTime updateTime;
}
