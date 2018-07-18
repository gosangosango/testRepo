package kr.co.miracom.mes.wip.model;

import java.time.ZonedDateTime;

import kr.co.miracom.dbist.annotation.DbistTable;
import lombok.Data;

@DbistTable(name = "MWIPINVLOD")
@Data
public class MWipInvLod {
    private String factoryCode;
    private String invLotId;
    private String matCode;
    private String lineCode;
    private String operCode;
    private String equipCode;
    private ZonedDateTime loadTime;
    private String createUserId;
    private ZonedDateTime createTime;
    private String updateUserId;
    private ZonedDateTime updateTime;
}
