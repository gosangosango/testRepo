package kr.co.miracom.mes.a19.resource.simple.a19lot.model;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * Item V O: A19lot
 * @author Zotac023
 * @since 2018. 07. 18.
 */
@Data
public class A19lot {
    private String id;
    private String lotId;
    private String lotDesc;
    private String lineCode;
    private String matCode;
    private String flowCode;
    private int flowSeqNo;
    private String operCode;
    private String orderNo;
    private String lotType;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime createTime;
    private Double createQty;
    private Double qty;
    private String lotComment;
    private String lotStatus;
    private boolean holdFlag;
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
    private boolean prodCompleteFlag;
    
    //Relation Fields
    private String matShortDesc;
    private String lineDesc;
    private String OperDesc;
    private String smallLabelId;
    private String boxNo;
    private String largeLabelId;
    private String palletNo;
}
