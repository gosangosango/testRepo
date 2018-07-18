package kr.co.miracom.mes.wip.resource.simple.plan.model;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * Item V O: Plan
 * @author myjung.jung
 * @since 2018. 06. 19.
 */
@Data
public class Plan {
    private String id;
    private String planMonth;
    private String matCode;
    private String matDesc;
    private String matShortDesc;
    private double planQty;
    private String createUserId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime createTime;
    private String updateUserId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime updateTime;
}
