package kr.co.miracom.mes.qcm.model;

import java.time.ZonedDateTime;

import kr.co.miracom.dbist.annotation.DbistRelation;
import kr.co.miracom.mes.wip.model.subtype.WipMatDef;
import lombok.Data;

@Data
public class MQcmReqDtl {
    private String factoryCode;
    private String inspNo;
    private int inspSeq;
    private String labelId;
    private String matCode;
    private Double inspReqQty;
    private String createUserId;
    private ZonedDateTime createTime;
    private String updateUserId;
    private ZonedDateTime updateTime;
}
