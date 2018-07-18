package kr.co.miracom.mes.wip.resource.complex.lot_req_ins.service.post;

import lombok.Data;

/**
 * Output V O: LotReqIns
 * @author gom
 * @since 2018. 07. 05.
 */
@Data
public class PostLotReqInsOut {
    private boolean success = true;
    private String inspNo;
}
