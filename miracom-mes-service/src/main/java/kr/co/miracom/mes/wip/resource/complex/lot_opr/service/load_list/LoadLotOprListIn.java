package kr.co.miracom.mes.wip.resource.complex.lot_opr.service.load_list;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Input V O: Lot_opr
 * @author gom
 * @since 2018. 06. 25.
 */
@Data
public class LoadLotOprListIn {
    @ApiModelProperty(required = true)
    private String lineCode;
    @ApiModelProperty(required = true)
    private String orderNo;
    @ApiModelProperty(required = true)
    private String operCode;
    private String equipCode;
}
