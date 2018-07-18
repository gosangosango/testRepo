package kr.co.miracom.mes.wip.model;

import java.time.ZonedDateTime;

import kr.co.miracom.dbist.annotation.DbistRelation;
import kr.co.miracom.framework.annotation.DbData;
import kr.co.miracom.mes.bas.model.subtype.GcmTblDat;
import kr.co.miracom.mes.wip.model.subtype.WipOprDef;
import lombok.Data;

@DbData(notFoundCode = "MIC-00017")
@Data
public class MWipLinDef {
    private String factoryCode;
    private String lineCode;
    private String lineDesc;
    private String shift1Start;
    private String erpLineCode;
    private String lineGrp;
    @DbistRelation(field = { "factoryCode", "value:LINE_GRP", "lineGrp", "value:NONE", "value:NONE", "value:NONE",
                    "value:NONE" })
    private GcmTblDat lineGrpData;
    private boolean prodFlag;
    private String inpMatOperCode;
    @DbistRelation(field = { "factoryCode", "inpMatOperCode" })
    private WipOprDef inpMatOper;
    private String toOperCode;
    @DbistRelation(field = { "factoryCode", "toOperCode" })
    private WipOprDef toOper;
    private boolean deleteFlag;
    private String deleteUserId;
    private ZonedDateTime deleteTime;
    private String createUserId;
    private ZonedDateTime createTime;
    private String updateUserId;
    private ZonedDateTime updateTime;
}
