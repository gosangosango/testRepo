package kr.co.miracom.mes.inv.resource.complex.inv_lot_shp.service.ship_order_list;

import java.util.List;

import kr.co.miracom.mes.inv.resource.complex.inv_lot_shp.model.ShipOrd;
import lombok.Data;

/**
 * Output V O: InvLotShp
 * @author gom
 * @since 2018. 07. 10.
 */
@Data
public class ShipInvLotShpOrderListOut {
    private boolean success = true;
    private List<ShipOrd> list;
}
