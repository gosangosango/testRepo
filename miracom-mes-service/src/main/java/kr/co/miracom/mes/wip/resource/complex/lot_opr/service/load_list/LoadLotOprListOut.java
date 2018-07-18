package kr.co.miracom.mes.wip.resource.complex.lot_opr.service.load_list;

import java.util.List;

import kr.co.miracom.mes.wip.resource.complex.lot_end.model.InputBFMat;
import lombok.Data;

/**
 * Output V O: Lot_opr
 * @author gom
 * @since 2018. 06. 25.
 */
@Data
public class LoadLotOprListOut {
    private List<InputBFMat> list;
}
