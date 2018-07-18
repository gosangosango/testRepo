package kr.co.miracom.mes.wip.model;

import java.time.ZonedDateTime;

import kr.co.miracom.dbist.annotation.DbistRelation;
import kr.co.miracom.mes.wip.model.subtype.WipOprDef;
import lombok.Data;

@Data
public class MWipBomOpr {
    private String factoryCode;
    private String matCode;
    private String childMatCode;
    private String operCode;
    @DbistRelation(field = { "factoryCode", "operCode" })
    private WipOprDef oper;
    private String createUserId;
    private ZonedDateTime createTime;
    private String updateUserId;
    private ZonedDateTime updateTime;
}
