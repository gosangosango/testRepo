package kr.co.miracom.mes.inv.model;

import java.time.ZonedDateTime;

import kr.co.miracom.dbist.annotation.DbistRelation;
import kr.co.miracom.mes.wip.model.subtype.WipMatDef;
import lombok.Data;

@Data
public class MInvShpOrd {
    private String factoryCode;
    private String shipOrdNo;
    private String matCode;
    private String customerCode;
    private String shipExpDate;
    private Double shipPlanQty;
    private Double shipQty;
    private String destination;
    private boolean deleteFlag;
    private String deleteUserId;
    private String createUserId;
    private ZonedDateTime createTime;
    private String updateUserId;
    private ZonedDateTime updateTime;
}
