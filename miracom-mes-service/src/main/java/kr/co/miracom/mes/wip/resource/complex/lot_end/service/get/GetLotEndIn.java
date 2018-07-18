package kr.co.miracom.mes.wip.resource.complex.lot_end.service.get;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Input V O: LotEnd
 * @author gom
 * @since 2018. 06. 25.
 */
@Data
public class GetLotEndIn {
    @ApiModelProperty(required = true)
    private String lotId;
    @ApiModelProperty(required = true)
    private String orderNo;
    @ApiModelProperty(required = true)
    private String lineCode;
    @ApiModelProperty(required = true)
    private String matCode;
    @ApiModelProperty(required = true)
    private String operCode;
}
