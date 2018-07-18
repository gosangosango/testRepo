package kr.co.miracom.mes.inv.resource.complex.inv_lot_shp.service.ship_order;

import lombok.Data;

/**
 * Output V O: InvLotShp
 * @author gom
 * @since 2018. 07. 10.
 */
@Data
public class ShipInvLotShpOrderOut {
    private boolean success = true;
    private String shipOrdNo;
    private String customerCode;
    private String customerName;
    private String matCode;
    private String matShortDesc;
    private String shipExpDate;
    private Double shipPlanQty;
    private Double shipQty;
    private String destination;
}
