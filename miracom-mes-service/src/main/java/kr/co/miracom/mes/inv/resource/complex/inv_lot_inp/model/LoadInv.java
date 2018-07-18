package kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.model;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class LoadInv {
    private String invLotId;
    private ZonedDateTime loadTime;
    private String matCode;
    private String matDesc;
    private Double qty;
    private String operCode;
    private String operDesc;
    private String equipCode;
    private String equipDesc;
}
