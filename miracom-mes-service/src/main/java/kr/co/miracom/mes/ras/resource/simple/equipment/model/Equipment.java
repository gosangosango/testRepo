package kr.co.miracom.mes.ras.resource.simple.equipment.model;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * Item V O: Equipment
 * @author myjung.jung
 * @since 2018. 06. 11.
 */
@Data
public class Equipment {
    private String id;
    private String equipCode;
    private String equipDesc;
    private String equipType;
    private String equipTypeDesc;
    private String areaCode;
    private String areaDesc;
    private String lineCode;
    private String lineDesc;
    private boolean pmSchFlag;
    private Integer maxProcCount;
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
