package kr.co.miracom.mes.wip.resource.complex.lot_end.service.valid_inv;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Input V O: LotEnd
 * @author gom
 * @since 2018. 06. 25.
 */
@Data
public class ValidLotEndInvIn {
    @ApiModelProperty(required = true)
    private String invLotId;
    @ApiModelProperty(required = true)
    private String orderNo;
    @ApiModelProperty(required = true)
    private String operCode;
}
