package kr.co.miracom.mes.wip.model;

import java.time.ZonedDateTime;

import kr.co.miracom.dbist.annotation.DbistRelation;
import kr.co.miracom.mes.wip.model.subtype.WipFlwDef;
import kr.co.miracom.mes.wip.model.subtype.WipLinDef;
import kr.co.miracom.mes.wip.model.subtype.WipMatDef;
import kr.co.miracom.mes.wip.model.subtype.WipOprDef;
import lombok.Data;

@Data
public class MWipLotSts {
    private String factoryCode;
    private String lotId;
    private String lotDesc;
    private String lineCode;
    @DbistRelation(field = { "factoryCode", "lineCode" })
    private WipLinDef line;
    private String matCode;
    @DbistRelation(field = { "factoryCode", "matCode" })
    private WipMatDef mat;
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
    private String lastTranCode;
    private ZonedDateTime lastTranTime;
    private int lastHistSeq;
    private boolean lotDelFlag;
    private String lotDelCode;
    private ZonedDateTime lotDelTime;
    private boolean prodCompleteFlag;
}
