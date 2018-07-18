package kr.co.miracom.mes.wip.model;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class MWipCusDef {
    private String factoryCode;
    private String customerCode;
    private String customerDesc;
    private String createUserId;
    private ZonedDateTime createTime;
    private String updateUserId;
    private ZonedDateTime updateTime;
}
