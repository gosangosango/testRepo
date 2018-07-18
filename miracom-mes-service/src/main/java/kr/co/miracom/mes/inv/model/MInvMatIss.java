package kr.co.miracom.mes.inv.model;

import java.time.ZonedDateTime;

import kr.co.miracom.dbist.annotation.DbistRelation;
import kr.co.miracom.mes.wip.model.subtype.WipMatDef;
import lombok.Data;

@Data
public class MInvMatIss {
    private String factoryCode;
    private String reqNo;
    private String reqMatCode;
    @DbistRelation(field = { "factoryCode", "reqMatCode" })
    private WipMatDef mat;
    private int seqNo;
    private String invLotId;
    private Double issueQty;
    private String fromOperCode;
    private String toOperCode;
    private String issueType;
    private String noReqType;
    private String reason;
    private String createUserId;
    private ZonedDateTime createTime;
    private String updateUserId;
    private ZonedDateTime updateTime;
}
