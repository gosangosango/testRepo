package kr.co.miracom.mes.wip.resource.complex.lot_end.service.get;

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
public class GetLotEndOut {
    private boolean success = true;
    private Double qty;
}
