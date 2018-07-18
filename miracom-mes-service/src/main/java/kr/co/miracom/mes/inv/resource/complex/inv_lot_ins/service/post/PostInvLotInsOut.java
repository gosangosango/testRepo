package kr.co.miracom.mes.inv.resource.complex.inv_lot_ins.service.post;

import lombok.Data;

/**
 * Output V O: InvLotIns
 * @author myjung.jung
 * @since 2018. 06. 25.
 */
@Data
public class PostInvLotInsOut {
    private String invLotId;
    private String operCode;
    private String operDesc;
    private String matCode;
    private String matDesc;
    private double qty;
    private String vendorCode;
    private String vendorDesc;
}
