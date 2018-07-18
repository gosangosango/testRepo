package kr.co.miracom.mes.inv.resource.simple.inv_request.model;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import kr.co.miracom.framework.converter.jackson.DateStrDeserializer;
import kr.co.miracom.framework.converter.jackson.DateStrSerializer;

import lombok.Data;

/**
 * Item V O: InvRequest
 * @author User
 * @since 2018. 07. 03.
 */
@Data
public class InvRequest {
    private String id;
    private String reqNo;
    private String orderNo;
    @JsonSerialize(using = DateStrSerializer.class)
    @JsonDeserialize(using = DateStrDeserializer.class)
    private String reqDate;
    private String matCode;
    private String matShortDesc;
    private String lineCode;
    private String lineDesc;
    private Double ordQty;
    private String ordPriority;
    private String ordStatus;
    private String ordStatusDesc;
    private String createUserId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime createTime;
    private String updateUserId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime updateTime;
}
