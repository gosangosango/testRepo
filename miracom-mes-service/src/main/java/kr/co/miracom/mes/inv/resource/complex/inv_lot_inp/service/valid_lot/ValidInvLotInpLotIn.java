package kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.valid_lot;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Input V O: LotEnd
 * @author gom
 * @since 2018. 06. 25.
 */
@Data
public class ValidInvLotInpLotIn {
    @ApiModelProperty(required = true)
    private String invLotId;
    @ApiModelProperty(required = true)
    private String orderNo;
}
