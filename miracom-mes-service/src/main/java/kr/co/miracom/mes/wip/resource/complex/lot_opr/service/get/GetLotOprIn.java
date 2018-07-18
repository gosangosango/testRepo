package kr.co.miracom.mes.wip.resource.complex.lot_opr.service.get;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Input V O: Lot_opr
 * @author gom
 * @since 2018. 06. 25.
 */
@Data
public class GetLotOprIn {
    @ApiModelProperty(required = true)
    private String lineCode;
    @ApiModelProperty(required = true)
    private String orderNo;
    private String operCode;
    private String equipCode;
    private boolean invMatFlag;
}
