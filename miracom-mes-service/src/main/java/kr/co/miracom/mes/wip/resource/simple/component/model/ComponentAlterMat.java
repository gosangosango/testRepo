package kr.co.miracom.mes.wip.resource.simple.component.model;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * Item V O: Component
 * @author myjung.jung
 * @since 2018. 07. 04.
 */
@Data
public class ComponentAlterMat {
    private String id;
    private String alterMatCode;
    private String alterMatDesc;
    private String createUserId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime createTime;
    private String updateUserId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime updateTime;
}
