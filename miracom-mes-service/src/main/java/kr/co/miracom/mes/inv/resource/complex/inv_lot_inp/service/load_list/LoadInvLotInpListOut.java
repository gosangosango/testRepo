package kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.load_list;

import java.util.List;

import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.model.LoadInv;
import lombok.Data;

/**
 * Output V O: InvLotInput
 * @author gom
 * @since 2018. 07. 04.
 */
@Data
public class LoadInvLotInpListOut {
    private boolean success = true;
    private List<LoadInv> list;
}
