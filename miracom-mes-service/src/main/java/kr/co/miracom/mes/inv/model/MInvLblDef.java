package kr.co.miracom.mes.inv.model;

import java.time.ZonedDateTime;

import kr.co.miracom.dbist.annotation.DbistRelation;
import kr.co.miracom.mes.wip.model.subtype.WipVenDef;
import lombok.Data;

@Data
public class MInvLblDef {
    private String factoryCode;
    private String labelId;
    private String labelType;
    private String labelCode;
    private String matCode;
    private String poNo;
    private int poSeq;
    private String dlvNo;
    private int dlvSeq;
    private String orderNo;
    private String inspNo;
    private String vendorCode;
    @DbistRelation(field = { "factoryCode", "vendorCode" })
    private WipVenDef vendor;
    private double packQty;
    private String packDate;
    private String labelStatus;
    private boolean prodCompleteFlag;
    private String createUserId;
    private ZonedDateTime createTime;
    private String updateUserId;
    private ZonedDateTime updateTime;
}
