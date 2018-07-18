package kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.backflush_list;

import java.util.List;

import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.model.BackflushMat;
import lombok.Data;

/**
 * Output V O: InvLotInput
 * @author gom
 * @since 2018. 07. 04.
 */
@Data
public class BackflushInvLotInpListOut {
    private boolean success = true;
    private List<BackflushMat> list;
}
