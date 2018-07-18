package kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.backflush_list;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Input V O: InvLotInput
 * @author gom
 * @since 2018. 07. 04.
 */
@Data
public class BackflushInvLotInpListIn {
    @ApiModelProperty(required = true)
    private String lineCode;
    @ApiModelProperty(required = true)
    private String orderNo;
}
