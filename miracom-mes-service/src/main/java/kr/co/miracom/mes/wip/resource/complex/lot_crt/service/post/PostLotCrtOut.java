package kr.co.miracom.mes.wip.resource.complex.lot_crt.service.post;

import lombok.Data;

/**
 * Output V O: LotCrt
 * @author myjung.jung
 * @since 2018. 06. 25.
 */
@Data
public class PostLotCrtOut {
    private boolean success = true;
    private String lotId;
}
