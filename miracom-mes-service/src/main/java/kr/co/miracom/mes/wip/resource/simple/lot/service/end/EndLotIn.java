package kr.co.miracom.mes.wip.resource.simple.lot.service.end;

import java.time.ZonedDateTime;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EndLotIn {
    @ApiModelProperty(required = true)
    private String lotId;
    @ApiModelProperty(required = true)
    private String operCode;
    @ApiModelProperty(required = true)
    private String matCode;
    @ApiModelProperty(required = true)
    private String goodType;
    private double qty;
    private String equipCode;
    private String lotComment;
    ZonedDateTime tranTime;
    private boolean lastFlag;
}
