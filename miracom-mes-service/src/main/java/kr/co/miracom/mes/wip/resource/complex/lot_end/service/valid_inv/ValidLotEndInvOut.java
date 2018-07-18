package kr.co.miracom.mes.wip.resource.complex.lot_end.service.valid_inv;

import java.util.List;

import kr.co.miracom.mes.wip.resource.complex.lot_end.model.InputBFMat;
import kr.co.miracom.mes.wip.resource.complex.lot_end.model.InputMat;
import lombok.Data;

/**
 * Output V O: LotEnd
 * @author gom
 * @since 2018. 06. 25.
 */
@Data
public class ValidLotEndInvOut {
    private boolean success = true;
    private String invLotId;
    private String matCode;
    private String matShortDesc;
    private Double qty;
}
