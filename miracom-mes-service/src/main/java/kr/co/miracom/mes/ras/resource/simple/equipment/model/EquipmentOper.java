package kr.co.miracom.mes.ras.resource.simple.equipment.model;

import java.time.ZonedDateTime;

import lombok.Data;

/**
 * Item V O: Equipment
 * @author myjung.jung
 * @since 2018. 06. 11.
 */
@Data
public class EquipmentOper {
    private String id;
    private String operCode;
    private String operDesc;
    private String createUserId;
    private ZonedDateTime createTime;
    private String updateUserId;
    private ZonedDateTime updateTime;
}
