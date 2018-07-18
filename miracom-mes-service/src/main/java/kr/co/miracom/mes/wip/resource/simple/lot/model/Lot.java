package kr.co.miracom.mes.wip.resource.simple.lot.model;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * Item V O: Lot
 * @author gom
 * @since 2018. 06. 21.
 */
@Data
public class Lot {
    private String id;
    private String lotId;
    private String lotDesc;
    private String lineCode;
    private String lineDesc;
    private String matCode;
    private String matDesc;
    private String matShortDesc;
    private String flowCode;
    private String flowDesc;
    private int flowSeqNo;
    private String operCode;
    private String operDesc;
    private String orderNo;
    private String lotType;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime createTime;
    private Double createQty;
    private Double qty;
    private String lotComment;
    private String lotStatus;
    private int holdFlag;
    private String holdCode;
    private int lotPriority;
    private boolean startFlag;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime startTime;
    private String startEquipCode;
    private boolean endFlag;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime endTime;
    private String endEquipCode;
    private String fromToType;
    private String fromToLotId;
    private String shipCode;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime shipTime;
    private String lastTranCode;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime lastTranTime;
    private int lastHistSeq;
    private boolean lotDelFlag;
    private String lotDelCode;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime lotDelTime;
}
