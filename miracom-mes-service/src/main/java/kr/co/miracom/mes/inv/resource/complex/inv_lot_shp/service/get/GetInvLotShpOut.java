package kr.co.miracom.mes.inv.resource.complex.inv_lot_shp.service.get;

import lombok.Data;

/**
 * Output V O: InvLotShp
 * @author gom
 * @since 2018. 07. 10.
 */
@Data
public class GetInvLotShpOut {
    private boolean success = true;
    private String labelId;
    private String matCode;
    private Double packQty;
}
