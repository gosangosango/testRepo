package kr.co.miracom.mes.inv.resource.simple.inv_lot.service.instore;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class InstoreInvLotIn {
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
