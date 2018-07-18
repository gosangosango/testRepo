package kr.co.miracom.mes.wip.resource.simple.line.model;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * Item V O: Line
 * @author myjung.jung
 * @since 2018. 06. 05.
 */
@Data
public class Line {
    private String id;
    private String lineCode;
    private String lineDesc;
    private String shift1Start;
    private String erpLineCode;
    private String lineGrp;
    private String lineGrpDesc;
    private boolean prodFlag;
    private String inpMatOperCode;
    private String inpMatOperDesc;
    private String toOperCode;
    private String toOperDesc;
    private boolean deleteFlag;
    private String deleteUserId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime deleteTime;
    private String createUserId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime createTime;
    private String updateUserId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime updateTime;
}
