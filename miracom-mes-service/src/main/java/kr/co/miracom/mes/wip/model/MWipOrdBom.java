package kr.co.miracom.mes.wip.model;

import java.time.ZonedDateTime;

import kr.co.miracom.dbist.annotation.DbistRelation;
import kr.co.miracom.mes.wip.model.subtype.WipMatDef;
import kr.co.miracom.mes.wip.model.subtype.WipOprDef;
import lombok.Data;

@Data
public class MWipOrdBom {
    private String factoryCode;
    private String orderNo;
    private String childMatCode;
    @DbistRelation(field = { "factoryCode", "childMatCode" })
    private WipMatDef childMat;
    private String matCode;
    @DbistRelation(field = { "factoryCode", "matCode" })
    private WipMatDef mat;
    private String operCode;
    @DbistRelation(field = { "factoryCode", "operCode" })
    private WipOprDef oper;
    private int seqNo;
    private double componentQty;
    private String applyDate;
    private String createUserId;
    private ZonedDateTime createTime;
    private String updateUserId;
    private ZonedDateTime updateTime;
}
