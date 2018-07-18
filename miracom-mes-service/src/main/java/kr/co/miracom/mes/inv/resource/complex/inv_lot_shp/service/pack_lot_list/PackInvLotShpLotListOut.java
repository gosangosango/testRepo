package kr.co.miracom.mes.inv.resource.complex.inv_lot_shp.service.pack_lot_list;

import java.util.List;

import kr.co.miracom.mes.inv.resource.complex.inv_lot_shp.model.ShipLotDetail;
import lombok.Data;

/**
 * Output V O: InvLotShp
 * @author gom
 * @since 2018. 07. 10.
 */
@Data
public class PackInvLotShpLotListOut {
    private boolean success = true;
    private List<ShipLotDetail> list;
}
