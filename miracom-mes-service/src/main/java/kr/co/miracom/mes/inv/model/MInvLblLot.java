package kr.co.miracom.mes.inv.model;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class MInvLblLot {
    private String factoryCode;
    private String labelId;
    private String smallLabelId;
    private String boxNo;
    private String largeLabelId;
    private String palletNo;
    private String createUserId;
    private ZonedDateTime createTime;
    private String updateUserId;
    private ZonedDateTime updateTime;
}
