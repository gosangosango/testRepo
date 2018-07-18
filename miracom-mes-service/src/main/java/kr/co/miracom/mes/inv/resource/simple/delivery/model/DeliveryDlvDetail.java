package kr.co.miracom.mes.inv.resource.simple.delivery.model;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * Item V O: Delivery
 * @author myjung.jung
 * @since 2018. 06. 25.
 */
@Data
public class DeliveryDlvDetail {
    private String id;
    private int dlvSeq;
    private String matCode;
    private String matDesc;
    private String matShortDesc;
    private String poNo;
    private int poSeq;
    private double dlvQty;
    private String iqcNo;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime iqcReqTime;
    private String iqcUserId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime iqcTime;
    private String iqcStatus;
    private String iqcResultCode;
    private String createUserId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime createTime;
    private String updateUserId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime updateTime;
}
