package kr.co.miracom.mes.wip.model;

import java.time.ZonedDateTime;

import kr.co.miracom.dbist.annotation.DbistRelation;
import kr.co.miracom.framework.annotation.DbData;
import kr.co.miracom.mes.bas.model.subtype.GcmTblDat;
import lombok.Data;

@DbData(notFoundCode = "MIC-00015")
@Data
public class MWipMatDef {
    private String factoryCode;
    private String matCode;
    private String matDesc;
    private String matShortDesc;
    private boolean purchaseInspFlag;
    private boolean importInspFlag;
    private boolean outgoingInspFlag;
    private String unit;
    @DbistRelation(field = { "factoryCode", "value:UNIT", "unit", "value:NONE", "value:NONE", "value:NONE",
                    "value:NONE" })
    private GcmTblDat unitData;
    private String matType;
    @DbistRelation(field = { "factoryCode", "value:MAT_TYPE", "matType", "value:NONE", "value:NONE", "value:NONE",
                    "value:NONE" })
    private GcmTblDat matTypeData;
    private String matGrp;
    @DbistRelation(field = { "factoryCode", "value:MAT_GRP", "matGrp", "value:NONE", "value:NONE", "value:NONE",
                    "value:NONE" })
    private GcmTblDat matGrpData;
    private Double weightNet;
    private Double weightGross;
    private Double volume;
    private Double dimensionHr;
    private Double dimensionVt;
    private Double dimensionHt;
    private String deductionType;
    @DbistRelation(field = { "factoryCode", "value:DEDUCTION_TYPE", "deductionType", "value:NONE", "value:NONE",
                    "value:NONE", "value:NONE" })
    private GcmTblDat deductionTypeData;
    private boolean fifoFlag;
    private Double smallPackQty;
    private Double packQty;
    private Double largePackQty;
    private Double palletQty;
    private Double lotQty;
    private boolean deleteFlag;
    private String deleteUserId;
    private ZonedDateTime deleteTime;
    private String createUserId;
    private ZonedDateTime createTime;
    private String updateUserId;
    private ZonedDateTime updateTime;
}
