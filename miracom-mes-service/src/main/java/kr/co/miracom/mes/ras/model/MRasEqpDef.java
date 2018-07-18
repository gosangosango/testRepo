package kr.co.miracom.mes.ras.model;

import java.time.ZonedDateTime;

import kr.co.miracom.dbist.annotation.DbistRelation;
import kr.co.miracom.mes.bas.model.subtype.GcmTblDat;
import kr.co.miracom.mes.wip.model.subtype.WipLinDef;
import lombok.Data;

@Data
public class MRasEqpDef {
    private String factoryCode;
    private String equipCode;
    private String equipDesc;
    private String equipType;
    @DbistRelation(field = { "factoryCode", "value:EQUIP_TYPE", "equipType", "value:NONE", "value:NONE", "value:NONE",
                    "value:NONE" })
    private GcmTblDat equipTypeData;
    private String areaCode;
    @DbistRelation(field = { "factoryCode", "value:AREA_CODE", "areaCode", "value:NONE", "value:NONE", "value:NONE",
                    "value:NONE" })
    private GcmTblDat area;
    private String lineCode;
    @DbistRelation(field = { "factoryCode", "lineCode" })
    private WipLinDef line;
    private boolean pmSchFlag;
    private Integer maxProcCount;
    private boolean deleteFlag;
    private String deleteUserId;
    private ZonedDateTime deleteTime;
    private String createUserId;
    private ZonedDateTime createTime;
    private String updateUserId;
    private ZonedDateTime updateTime;
}
