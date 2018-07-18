package kr.co.miracom.mes.inv.model;

import java.time.ZonedDateTime;

import kr.co.miracom.dbist.annotation.DbistRelation;
import kr.co.miracom.mes.bas.model.subtype.GcmTblDat;
import kr.co.miracom.mes.wip.model.subtype.WipMatDef;
import kr.co.miracom.mes.wip.model.subtype.WipOprDef;
import lombok.Data;

@Data
public class MInvRetMat {
    private String factoryCode;
    private String retTimeStr;
    private String invLotId;
    
    private String matCode;
    @DbistRelation(field = { "factoryCode", "matCode" })
    private WipMatDef mat;
    
    private String retDate;
    private String orderNo;
    
    private String fromOperCode;
    @DbistRelation(field = { "factoryCode", "fromOperCode" })
    private WipOprDef fromOper;
    
    private String toOperCode;
    @DbistRelation(field = { "factoryCode", "toOperCode" })
    private WipOprDef toOper;
    
    private String retType;    
    @DbistRelation(field = { "factoryCode", "retType", "value:NONE", "value:NONE", "value:NONE", "value:NONE","value:NONE" })
    private GcmTblDat retData;
    
    private String retCode;
    @DbistRelation(field = { "factoryCode", "retCode", "value:NONE", "value:NONE", "value:NONE", "value:NONE","value:NONE" })
    private GcmTblDat ret;
    
    private String retReason;
    
    private String createUserId;
    private ZonedDateTime createTime;
    private String updateUserId;
    private ZonedDateTime updateTime;
}
