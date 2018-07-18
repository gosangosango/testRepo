package kr.co.miracom.mes.qcm.model;

import java.time.ZonedDateTime;

import kr.co.miracom.dbist.annotation.DbistRelation;
import kr.co.miracom.mes.wip.model.subtype.WipMatDef;
import lombok.Data;

@Data
public class MQcmReqDef {
    private String factoryCode;
    private String inspNo;
    private String inspType;
    private String matCode;
    private String inspStatus;
    private ZonedDateTime inspReqTime;
    private String inspReqDate;
    private String inspReqUserId;
    private String inspReqComment;
    private Double inspReqQty;
    private ZonedDateTime inspTime;
    private String inspDate;
    private String inspUserId;
    private String inspComment;
    private Double okQty;
    private Double ngQty;
    private String judgResultCode;
    private ZonedDateTime judgTime;
    private String judgDate;
    private String judgUserId;
    private String judgComment;
    private String createUserId;
    private ZonedDateTime createTime;
    private String updateUserId;
    private ZonedDateTime updateTime;
}
