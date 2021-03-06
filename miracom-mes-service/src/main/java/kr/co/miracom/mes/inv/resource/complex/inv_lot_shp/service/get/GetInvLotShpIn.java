package kr.co.miracom.mes.inv.resource.complex.inv_lot_shp.service.get;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Input V O: InvLotShp
 * @author gom
 * @since 2018. 07. 10.
 */
@Data
public class GetInvLotShpIn {
    private String id;
    @ApiModelProperty(required = true)
    private String shipOperCode;
    @ApiModelProperty(required = true)
    private String shipOrdNo;
    @ApiModelProperty(required = true)
    private String labelId;
}
