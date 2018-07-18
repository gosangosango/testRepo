package kr.co.miracom.mes.wip.model;

import java.time.ZonedDateTime;

import kr.co.miracom.dbist.annotation.DbistTable;
import lombok.Data;

@DbistTable(name = "MWIPVENDEF")
@Data
public class MWipVenDef {
    private String factoryCode;
    private String vendorCode;
    private String vendorDesc;
    private String createUserId;
    private ZonedDateTime createTime;
    private String updateUserId;
    private ZonedDateTime updateTime;
}
