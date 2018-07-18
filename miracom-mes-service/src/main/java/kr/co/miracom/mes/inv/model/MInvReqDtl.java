package kr.co.miracom.mes.inv.model;

import java.time.ZonedDateTime;

import kr.co.miracom.dbist.annotation.DbistRelation;
import kr.co.miracom.mes.wip.model.subtype.WipMatDef;
import lombok.Data;

@Data
public class MInvReqDtl {
    private String factoryCode;
    private String reqNo;
    private String reqMatCode;
    @DbistRelation(field = { "factoryCode", "reqMatCode" })
    private WipMatDef reqMat;
    private Double reqQty;
    private String createUserId;
    private ZonedDateTime createTime;
    private String updateUserId;
    private ZonedDateTime updateTime;
    
}
