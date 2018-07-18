package kr.co.miracom.mes.wip.model;

import java.time.ZonedDateTime;

import kr.co.miracom.dbist.annotation.DbistRelation;
import kr.co.miracom.mes.wip.model.subtype.WipMatDef;
import lombok.Data;

@Data
public class MWipLinOph {
    private String factoryCode;
    private String workDate;
    private String workShift;
    private String lineCode;
    private String operCode;
    private String equipCode;
    private String matCode;
    @DbistRelation(field = { "factoryCode", "matCode" })
    private WipMatDef mat;
    private String startTimeStr;
    private String endTimeStr;
    private String orderNo;
    private Double outQty;
    private Double goodQty;
    private Double lossQty;
    private boolean resultOperFlag;
    private String createUserId;
    private ZonedDateTime createTime;
    private String updateUserId;
    private ZonedDateTime updateTime;
}
