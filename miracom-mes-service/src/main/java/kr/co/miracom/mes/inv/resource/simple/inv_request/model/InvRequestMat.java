package kr.co.miracom.mes.inv.resource.simple.inv_request.model;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * Item V O: InvRequest
 * @author User
 * @since 2018. 07. 03.
 */
@Data
public class InvRequestMat {
    private String id;
    private String reqMatCode;
    private String reqMatDesc;
    private String reqMatShortDesc;
    private Double reqQty;
    private Double issueQty;
    private Double remainQty;
    private String createUserId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime createTime;
    private String updateUserId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime updateTime;
}
