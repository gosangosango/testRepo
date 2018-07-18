package kr.co.miracom.mes.wip.resource.complex.lot_end.service.post;

import lombok.Data;

/**
 * Output V O: LotEnd
 * @author gom
 * @since 2018. 06. 25.
 */
@Data
public class PostLotEndOut {
    private boolean success = true;
    public String lotId;
}
