package kr.co.miracom.mes.inv.model;

import java.time.ZonedDateTime;

import kr.co.miracom.dbist.annotation.DbistRelation;
import kr.co.miracom.mes.wip.model.subtype.WipVenDef;
import lombok.Data;

@Data
public class MInvDlvMst {
    private String factoryCode;
    private String dlvNo;
    private String poNo;
    private int poSeq;
    private String dlvExpDate;
    private String vendorCode;
    @DbistRelation(field = { "factoryCode", "vendorCode" })
    private WipVenDef vendor;
    private String purInspUserId;
    private ZonedDateTime purInspTime;
    private boolean purInspCompFlag;
    private String purInspDate;
    private String createUserId;
    private ZonedDateTime createTime;
    private String updateUserId;
    private ZonedDateTime updateTime;
}
