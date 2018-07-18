package kr.co.miracom.mes.wip.model;

import java.time.ZonedDateTime;

import kr.co.miracom.dbist.annotation.DbistRelation;
import kr.co.miracom.framework.annotation.DbData;
import kr.co.miracom.mes.wip.model.subtype.WipOprDef;
import lombok.Data;

@DbData(notFoundCode = "MIC-00016")
@Data
public class MWipFlwDef {
    private String factoryCode;
    private String flowCode;
    private String flowDesc;
    private String firstOperCode;
    @DbistRelation(field = { "factoryCode", "firstOperCode" })
    private WipOprDef firstOper;
    private String lastOperCode;
    @DbistRelation(field = { "factoryCode", "lastOperCode" })
    private WipOprDef lastOper;
    private String createUserId;
    private ZonedDateTime createTime;
    private String updateUserId;
    private ZonedDateTime updateTime;
}
