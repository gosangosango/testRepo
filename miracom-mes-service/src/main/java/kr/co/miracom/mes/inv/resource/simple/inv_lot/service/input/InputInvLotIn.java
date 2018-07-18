package kr.co.miracom.mes.inv.resource.simple.inv_lot.service.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class InputInvLotIn {
    @ApiModelProperty(required = true)
    private String lineCode;
    private String operCode;
    private String equipCode;
    @ApiModelProperty(required = true)
    private String invLotId;
}
