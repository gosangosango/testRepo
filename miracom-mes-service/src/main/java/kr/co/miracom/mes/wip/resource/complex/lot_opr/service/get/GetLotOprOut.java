package kr.co.miracom.mes.wip.resource.complex.lot_opr.service.get;

import java.util.List;

import kr.co.miracom.mes.wip.resource.complex.lot_end.model.InputBFMat;
import lombok.Data;

/**
 * Output V O: Lot_opr
 * @author gom
 * @since 2018. 06. 25.
 */
@Data
public class GetLotOprOut {
    private Double dayOrdQty;
    private Double dayOutQty;
    private Double dayRemainQty;
    private Double dayGoodQty;
    private Double dayLossQty;
    private Double dayOperOutQty;
    private Double dayEquipOutQty;
    private List<InputBFMat> list;
}
