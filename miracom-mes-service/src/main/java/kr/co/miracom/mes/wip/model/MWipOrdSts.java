package kr.co.miracom.mes.wip.model;

import java.time.ZonedDateTime;

import kr.co.miracom.dbist.annotation.DbistRelation;
import kr.co.miracom.dbist.annotation.DbistTable;
import kr.co.miracom.framework.annotation.DbData;
import kr.co.miracom.mes.bas.model.subtype.GcmTblDat;
import kr.co.miracom.mes.wip.model.subtype.WipFlwDef;
import kr.co.miracom.mes.wip.model.subtype.WipLinDef;
import kr.co.miracom.mes.wip.model.subtype.WipMatDef;
import lombok.Data;

@DbData(notFoundCode = "MIC-00013")
@Data
public class MWipOrdSts {
    private String factoryCode;
    private String orderNo;
    private String orderDesc;
    private String lineCode;
    @DbistRelation(field = { "factoryCode", "lineCode" })
    private WipLinDef line;
    private String matCode;
    @DbistRelation(field = { "factoryCode", "matCode" })
    private WipMatDef mat;
    private String planMonth;
    @DbistRelation(field = { "factoryCode", "planMonth", "matCode" })
    private WipPlnDef plan;
    private String flowCode;
    @DbistRelation(field = { "factoryCode", "flowCode" })
    private WipFlwDef flow;
    private double ordQty;
    private double ordInQty;
    private double ordOutQty;
    private double rcvGoodQty;
    private double rcvLossQty;
    private String ordDate;
    private String ordStatus;
    @DbistRelation(field = { "factoryCode", "value:ORD_STATUS", "ordStatus", "value:NONE", "value:NONE", "value:NONE",
                    "value:NONE" })
    private GcmTblDat ordStatusData;
    private String ordPriority;
    private String ordType;
    @DbistRelation(field = { "factoryCode", "value:ORD_TYPE", "ordType", "value:NONE", "value:NONE", "value:NONE",
                    "value:NONE" })
    private GcmTblDat ordTypeData;
    private ZonedDateTime ordStartTime;
    private ZonedDateTime ordEndTime;
    private String ordComment;
    private String createUserId;
    private ZonedDateTime createTime;
    private String updateUserId;
    private ZonedDateTime updateTime;

    @Data
    @DbistTable(name = "MWIPPLNDEF")
    public static class WipPlnDef {
        private double planQty;
    }
}
