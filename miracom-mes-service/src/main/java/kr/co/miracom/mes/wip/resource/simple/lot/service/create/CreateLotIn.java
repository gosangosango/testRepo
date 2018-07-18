package kr.co.miracom.mes.wip.resource.simple.lot.service.create;

import lombok.Data;

@Data
public class CreateLotIn {
    private String lotId;
    private String lotDesc;
    private String orderNo;
    private String lineCode;
    private String matCode;
    private String flowCode;
    private double qty;
    private String lotComment;
}
