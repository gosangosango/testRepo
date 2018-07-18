package kr.co.miracom.mes.wip.model;

import java.time.ZonedDateTime;

import kr.co.miracom.dbist.annotation.DbistRelation;
import kr.co.miracom.mes.wip.model.subtype.WipCusDef;
import lombok.Data;

@Data
public class MWipMatCus {
    private String factoryCode;
    private String matCode;
    private String customerCode;
    @DbistRelation(field = { "factoryCode", "customerCode" })
    private WipCusDef customer;
    private String customerMatCode;
    private String createUserId;
    private ZonedDateTime createTime;
    private String updateUserId;
    private ZonedDateTime updateTime;
}
