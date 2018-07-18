package kr.co.miracom.mes.wip.resource.simple.operation.model;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * Item V O: Operation
 * @author myjung.jung
 * @since 2018. 06. 11.
 */
@Data
public class OperationEquip {
    private String id;
    private String equipCode;
    private String equipDesc;
    private String createUserId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime createTime;
    private String updateUserId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime updateTime;
}
