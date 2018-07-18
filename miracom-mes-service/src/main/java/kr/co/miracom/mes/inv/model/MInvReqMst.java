package kr.co.miracom.mes.inv.model;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class MInvReqMst {
    private String factoryCode;
    private String reqNo;
    private String orderNo;
    private String reqDate;
    private String createUserId;
    private ZonedDateTime createTime;
    private String updateUserId;
    private ZonedDateTime updateTime;
}
