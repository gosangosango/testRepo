package kr.co.miracom.mes.wip.model;

import java.time.ZonedDateTime;

import kr.co.miracom.dbist.annotation.DbistRelation;
import kr.co.miracom.mes.wip.model.subtype.WipFlwDef;
import kr.co.miracom.mes.wip.model.subtype.WipOprDef;
import lombok.Data;

@Data
public class MWipLotHis {
    private String factoryCode;
    private String lotId;
    private int histSeq;
    private ZonedDateTime tranTime;
    private String tranCode;
    private String lotDesc;
    private String lineCode;
    private String matCode;
    private String flowCode;
    @DbistRelation(field = { "factoryCode", "flowCode" })
    private WipFlwDef flow;
    private int flowSeqNo;
    private String operCode;
    @DbistRelation(field = { "factoryCode", "operCode" })
    private WipOprDef oper;
    private String orderNo;
    private String lotType;
    private ZonedDateTime createTime;
    private Double createQty;
    private Double qty;
    private String lotComment;
    private String lotStatus;
    private boolean holdFlag;
    private String holdCode;
    private int lotPriority;
    private boolean startFlag;
    private ZonedDateTime startTime;
    private String startEquipCode;
    private boolean endFlag;
    private ZonedDateTime endTime;
    private String endEquipCode;
    private String fromToType;
    private String fromToLotId;
    private String shipCode;
    private ZonedDateTime shipTime;
    private int lastHistSeq;
    private boolean lotDelFlag;
    private String lotDelCode;
    private ZonedDateTime lotDelTime;
    private boolean prodCompleteFlag;
}
