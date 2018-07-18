package kr.co.miracom.mes.a19.resource.simple.a19flow.model;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * Item V O: A19flow
 * @author Zotac023
 * @since 2018. 07. 17.
 */
@Data
public class A19flow {
    private String id;
    private String flowCode;
    private String flowDesc;
    private String createUserId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime createTime;
    private String UpdateUserId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime UpdateTime;
}
