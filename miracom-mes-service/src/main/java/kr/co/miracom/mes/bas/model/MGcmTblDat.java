package kr.co.miracom.mes.bas.model;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class MGcmTblDat {
    private String factoryCode;
    private String tableName;
    private String key1;
    private String key2;
    private String key3;
    private String key4;
    private String key5;
    private String keyDesc;
    private int seqNo;
    private String data1;
    private String data2;
    private String data3;
    private String data4;
    private String data5;
    private String data6;
    private String data7;
    private String data8;
    private String data9;
    private String data10;
    private String createUserId;
    private ZonedDateTime createTime;
    private String updateUserId;
    private ZonedDateTime updateTime;
}
