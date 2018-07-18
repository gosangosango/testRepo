package kr.co.miracom.mes.wip.resource.complex.lot_req_ins.model;

import lombok.Data;

/**
 * Item V O: Lot
 * @author gom
 * @since 2018. 07. 5.
 */
@Data
public class MatReqIns {
    private String lineCode;
    private String lineDesc;
    private String matCode;
    private String matDesc;
    private String packDate;
    private String labelId;
    private Double packQty;
}
