package kr.co.miracom.mes.inv.resource.complex.inv_lot_shp.model;

import lombok.Data;

/**
 * Item V O: Lot
 * @author gom
 * @since 2018. 07. 5.
 */
@Data
public class ShipOrd {
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
