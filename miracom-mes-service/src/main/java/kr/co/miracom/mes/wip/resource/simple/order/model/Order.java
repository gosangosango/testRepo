package kr.co.miracom.mes.wip.resource.simple.order.model;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import kr.co.miracom.framework.converter.jackson.DateStrDeserializer;
import kr.co.miracom.framework.converter.jackson.DateStrSerializer;
import lombok.Data;

/**
 * Item V O: Order
 * @author myjung.jung
 * @since 2018. 06. 12.
 */
@Data
public class Order {
    private String id;
    private String orderNo;
    private String orderDesc;
    private String lineCode;
    private String lineDesc;
    private String matCode;
    private String matDesc;
    private String planMonth;
    private String flowCode;
    private String flowDesc;
    private double ordQty;
    private double ordInQty;
    private double ordOutQty;
    private double rcvGoodQty;
    private double rcvLossQty;
    @JsonSerialize(using = DateStrSerializer.class)
    @JsonDeserialize(using = DateStrDeserializer.class)
    private String ordDate;
    private String ordStatus;
    private String ordStatusDesc;
    private String ordPriority;
    private String ordType;
    private String ordTypeDesc;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime ordStartTime;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime ordEndTime;
    private String ordComment;
    private String createUserId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime createTime;
    private String updateUserId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime updateTime;
}
