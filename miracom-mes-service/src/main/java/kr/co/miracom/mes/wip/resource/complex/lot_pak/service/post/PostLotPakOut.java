package kr.co.miracom.mes.wip.resource.complex.lot_pak.service.post;

import lombok.Data;

/**
 * Output V O: LotPak
 * @author gom
 * @since 2018. 07. 02.
 */
@Data
public class PostLotPakOut {
    private boolean success = true;
    private String packLabelId;
}
