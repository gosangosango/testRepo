package kr.co.miracom.mes.wip.resource.simple.lot.model;

import lombok.Data;

/**
 * Item V O: Lot
 * @author gom
 * @since 2018. 07. 5.
 */
@Data
public class ReqLot {
    private String labelId;
    private String matCode;
    private Double inspReqQty;
}
