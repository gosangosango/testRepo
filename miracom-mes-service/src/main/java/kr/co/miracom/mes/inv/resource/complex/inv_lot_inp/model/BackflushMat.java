package kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.model;

import lombok.Data;

@Data
public class BackflushMat {
    private String childMatCode;
    private String matDesc;
    private Double sumQty;
}
