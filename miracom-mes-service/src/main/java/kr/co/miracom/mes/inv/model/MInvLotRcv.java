package kr.co.miracom.mes.inv.model;

import java.time.ZonedDateTime;

import kr.co.miracom.dbist.annotation.DbistRelation;
import kr.co.miracom.mes.wip.model.subtype.WipLinDef;
import kr.co.miracom.mes.wip.model.subtype.WipMatDef;
import kr.co.miracom.mes.wip.model.subtype.WipOprDef;
import lombok.Data;

@Data
public class MInvLotRcv {
    private String factoryCode;
    private String invLotId;
    private int histSeq;
    private String operCode;
    @DbistRelation(field = { "factoryCode", "operCode" })
    private WipOprDef oper;
    private String matCode;
    @DbistRelation(field = { "factoryCode", "matCode" })
    private WipMatDef mat;
    private String invDate;
    private String tranCode;
    private ZonedDateTime tranTime;
    private double tranQty;
    private double rcvGoodQty;
    private double rcvLossQty;
    private boolean invFlag;
    private String lineCode;
    @DbistRelation(field = { "factoryCode", "lineCode" })
    private WipLinDef line;
    private String createUserId;
    private ZonedDateTime createTime;
    private String updateUserId;
    private ZonedDateTime updateTime;
}
