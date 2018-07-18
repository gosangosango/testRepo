package kr.co.miracom.mes.inv.resource.simple.inv_lot.service.create;

import lombok.Data;

@Data
public class CreateInvLotIn {
    private String invLotId;
    private String operCode;
    private String matCode;
    private double qty;
    private String vendorCode;
}
