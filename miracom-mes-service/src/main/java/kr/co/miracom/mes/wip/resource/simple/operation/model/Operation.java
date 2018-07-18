package kr.co.miracom.mes.wip.resource.simple.operation.model;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * Item V O: Operation
 * @author myjung.jung
 * @since 2018. 06. 05.
 */
@Data
public class Operation {
    private String id;
    private String operCode;
    private String operDesc;
    private String unit;
    private boolean startRequireFlag;
    private boolean pushPullFlag;
    private boolean erpIfFlag;
    private String erpOperCode;
    private boolean storeFlag;
    private String storeGrp;
    private String storeGrpDesc;
    private String createUserId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime createTime;
    private String updateUserId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime updateTime;
}
