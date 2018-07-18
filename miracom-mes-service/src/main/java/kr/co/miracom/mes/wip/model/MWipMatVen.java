package kr.co.miracom.mes.wip.model;

import java.time.ZonedDateTime;

import kr.co.miracom.dbist.annotation.DbistRelation;
import kr.co.miracom.mes.wip.model.subtype.WipVenDef;
import lombok.Data;

@Data
public class MWipMatVen {
    private String factoryCode;
    private String matCode;
    private String vendorCode;
    @DbistRelation(field = { "factoryCode", "vendorCode" })
    private WipVenDef vendor;
    private String createUserId;
    private ZonedDateTime createTime;
    private String updateUserId;
    private ZonedDateTime updateTime;
}
