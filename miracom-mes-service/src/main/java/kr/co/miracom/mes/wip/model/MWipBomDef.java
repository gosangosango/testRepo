package kr.co.miracom.mes.wip.model;

import java.time.ZonedDateTime;

import kr.co.miracom.dbist.annotation.DbistRelation;
import kr.co.miracom.mes.wip.model.subtype.WipMatDef;
import lombok.Data;

@Data
public class MWipBomDef {
    private String factoryCode;
    private String matCode;
    private String childMatCode;
    @DbistRelation(field = { "factoryCode", "childMatCode" })
    private WipMatDef childMat;
    private int seqNo;
    private double componentQty;
    private String applyStartDate;
    private String applyEndDate;
    private String createUserId;
    private ZonedDateTime createTime;
    private String updateUserId;
    private ZonedDateTime updateTime;
}
