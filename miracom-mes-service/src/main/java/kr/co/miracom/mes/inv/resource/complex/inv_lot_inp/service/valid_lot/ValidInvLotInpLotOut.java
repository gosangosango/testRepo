package kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.valid_lot;
import lombok.Data;

/**
 * Output V O: LotEnd
 * @author gom
 * @since 2018. 06. 25.
 */
@Data
public class ValidInvLotInpLotOut {
    private boolean success = true;
    private String invLotId;
    private String matCode;
    private String matDesc;
    private Double qty;
}
