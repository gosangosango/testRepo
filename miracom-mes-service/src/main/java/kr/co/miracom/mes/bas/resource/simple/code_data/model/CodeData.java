package kr.co.miracom.mes.bas.resource.simple.code_data.model;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * Item V O: CodeData
 * @author myjung.jung
 * @since 2018. 06. 05.
 */
@Data
public class CodeData {
    private String id;
    private String tableName;
    private String key1;
    private String key2;
    private String key3;
    private String key4;
    private String key5;
    private String keyDesc;
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
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime createTime;
    private String updateUserId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime updateTime;
}
