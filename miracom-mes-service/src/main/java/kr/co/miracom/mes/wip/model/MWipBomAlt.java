package kr.co.miracom.mes.wip.model;

import java.time.ZonedDateTime;

import kr.co.miracom.dbist.annotation.DbistRelation;
import kr.co.miracom.mes.wip.model.subtype.WipMatDef;
import lombok.Data;

@Data
public class MWipBomAlt {
    private String factoryCode;
    private String childMatCode;
    private String alterMatCode;
    @DbistRelation(field = { "factoryCode", "alterMatCode" })
    private WipMatDef alterMat;
    private String createUserId;
    private ZonedDateTime createTime;
    private String updateUserId;
    private ZonedDateTime updateTime;
}
