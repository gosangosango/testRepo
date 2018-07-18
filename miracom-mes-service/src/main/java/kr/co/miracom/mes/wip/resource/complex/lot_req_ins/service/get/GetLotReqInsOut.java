package kr.co.miracom.mes.wip.resource.complex.lot_req_ins.service.get;

import java.util.List;

import kr.co.miracom.mes.wip.resource.complex.lot_req_ins.model.MatReqIns;
import lombok.Data;

/**
 * Output V O: LotReqIns
 * @author gom
 * @since 2018. 07. 05.
 */
@Data
public class GetLotReqInsOut {
    private boolean success = true;
    private List<MatReqIns> list;
}
