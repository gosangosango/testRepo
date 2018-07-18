package kr.co.miracom.mes.wip.model;

import java.time.ZonedDateTime;

import kr.co.miracom.dbist.annotation.DbistRelation;
import kr.co.miracom.mes.bas.model.subtype.GcmTblDat;
import lombok.Data;

@Data
public class MWipOprDef {
    private String factoryCode;
    private String operCode;
    private String operDesc;
    private String unit;
    private boolean startRequireFlag;
    private boolean pushPullFlag;
    private boolean erpIfFlag;
    private String erpOperCode;
    private boolean storeFlag;
    private String storeGrp;
    @DbistRelation(field = { "factoryCode", "value:STORE_GRP", "storeGrp", "value:NONE", "value:NONE", "value:NONE",
                    "value:NONE" })
    private GcmTblDat storeGrpData;
    private String createUserId;
    private ZonedDateTime createTime;
    private String updateUserId;
    private ZonedDateTime updateTime;
}
