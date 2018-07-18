package kr.co.miracom.mes.wip.resource.simple.line_mf.model;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * Item V O: LineMf
 * @author mo21.kim
 * @since 2018. 07. 03.
 */
@Data
public class LineMf {
    private String id;
    private String matCode;
    private String matDesc;
    private String matShortDesc;
    private String flowCode;
    private String flowDesc;
    private String lineCode;
    private String lineDesc;
    private int seqNo;
    private String createUserId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime createTime;
    private String updateUserId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime updateTime;
}
