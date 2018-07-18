package kr.co.miracom.mes.wip.resource.complex.lot_pak.service.get;

import java.time.ZonedDateTime;
import java.util.List;

import kr.co.miracom.mes.wip.resource.complex.lot_pak.model.PackLot;
import lombok.Data;

/**
 * Output V O: LotPak
 * @author gom
 * @since 2018. 07. 02.
 */
@Data
public class GetLotPakOut {
    private boolean success = true;
    private ZonedDateTime createTime;
    private String matCode;
    private String matDesc;
    private String labelId;
    private Double qty;
    private String labelType; 
}
