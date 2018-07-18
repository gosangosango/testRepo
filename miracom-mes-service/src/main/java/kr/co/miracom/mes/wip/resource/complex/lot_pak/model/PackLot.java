package kr.co.miracom.mes.wip.resource.complex.lot_pak.model;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class PackLot {
    private ZonedDateTime endTime;
    private String matCode;
    private String matDesc;
    private String labelId;
    private Double qty;
    private String labelType; 
}
