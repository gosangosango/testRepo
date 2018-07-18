package kr.co.miracom.mes.inv.resource.complex.inv_lot_ins.service.post;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Input V O: InvLotIns
 * @author myjung.jung
 * @since 2018. 06. 25.
 */
@Data
public class PostInvLotInsIn {
    @ApiModelProperty(required = true)
    private String invLotId;
    @ApiModelProperty(required = true)
    private String operCode;
    @ApiModelProperty(required = true)
    private String matCode;
    @ApiModelProperty(required = true)
    private double qty;
    private String vendorCode;
}
