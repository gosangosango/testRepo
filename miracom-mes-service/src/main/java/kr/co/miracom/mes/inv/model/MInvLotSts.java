package kr.co.miracom.mes.inv.model;

import java.time.ZonedDateTime;

import kr.co.miracom.dbist.annotation.DbistRelation;
import kr.co.miracom.mes.bas.model.subtype.GcmTblDat;
import kr.co.miracom.mes.wip.model.subtype.WipMatDef;
import kr.co.miracom.mes.wip.model.subtype.WipOprDef;
import kr.co.miracom.mes.wip.model.subtype.WipVenDef;
import lombok.Data;

@Data
public class MInvLotSts {
    private String factoryCode;
    private String invLotId;
    private String operCode;
    @DbistRelation(field = { "factoryCode", "operCode" })
    private WipOprDef oper;
    private String matCode;
    @DbistRelation(field = { "factoryCode", "matCode" })
    private WipMatDef mat;
    private boolean invFlag;
    private double qty;
    private boolean inOperFlag;
    private String inputOrderNo;
    private String recvDate;
    private double recvQty;
    private String lastTranCode;
    private ZonedDateTime lastTranTime;
    private boolean holdFlag;
    private String holdCode;
    @DbistRelation(field = { "factoryCode", "value:HOLD_CODE", "holdCode", "value:NONE", "value:NONE", "value:NONE",
                    "value:NONE" })
    private GcmTblDat hold;
    private String fromToType;
    private String fromToLotId;
    private String vendorCode;
    @DbistRelation(field = { "factoryCode", "vendorCode" })
    private WipVenDef vendor;
    private String locNo;
    private boolean lotDelFlag;
    private String lotDelCode;
    private ZonedDateTime lotDelTime;
    private String createUserId;
    private ZonedDateTime createTime;
    private String updateUserId;
    private ZonedDateTime updateTime;
}
