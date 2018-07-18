package kr.co.miracom.mes.wip.resource.complex.lot_crt.service.post;

import lombok.Data;

/**
 * Input V O: LotCrt
 * @author myjung.jung
 * @since 2018. 06. 25.
 */
@Data
public class PostLotCrtIn {
    private String lotId;
    private String lotDesc;
    private String orderNo;
    private String lineCode;
    private String matCode;
    private String flowCode;
    private double qty;
    private String lotComment;
}
