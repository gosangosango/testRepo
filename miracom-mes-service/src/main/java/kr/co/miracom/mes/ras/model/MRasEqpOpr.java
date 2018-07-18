package kr.co.miracom.mes.ras.model;

import java.time.ZonedDateTime;

import kr.co.miracom.dbist.annotation.DbistRelation;
import kr.co.miracom.mes.ras.model.subtype.RasEqpDef;
import kr.co.miracom.mes.wip.model.subtype.WipOprDef;
import lombok.Data;

@Data
public class MRasEqpOpr {
    private String factoryCode;
    private String equipCode;
    private String operCode;
    @DbistRelation(field = { "factoryCode", "equipCode" })
    private RasEqpDef equip;
    @DbistRelation(field = { "factoryCode", "operCode" })
    private WipOprDef oper;
    private String createUserId;
    private ZonedDateTime createTime;
    private String updateUserId;
    private ZonedDateTime updateTime;
}
