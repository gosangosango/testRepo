package kr.co.miracom.mes.inv.model;

import java.time.ZonedDateTime;

import kr.co.miracom.dbist.annotation.DbistRelation;
import kr.co.miracom.mes.wip.model.subtype.WipMatDef;
import lombok.Data;

@Data
public class MInvDlvDtl {
    private String factoryCode;
    private String dlvNo;
    private int dlvSeq;
    private String matCode;
    @DbistRelation(field = { "factoryCode", "matCode" })
    private WipMatDef mat;
    private String poNo;
    private int poSeq;
    private double dlvQty;
    private String iqcNo;
    private ZonedDateTime iqcReqTime;
    private String iqcUserId;
    private ZonedDateTime iqcTime;
    private String iqcStatus;
    private String iqcResultCode;
    private String createUserId;
    private ZonedDateTime createTime;
    private String updateUserId;
    private ZonedDateTime updateTime;
}
