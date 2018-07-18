package kr.co.miracom.mes.wip.resource.complex.lot_pak.service.get;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Input V O: LotPak
 * @author gom
 * @since 2018. 07. 02.
 */
@Data
public class GetLotPakIn {
    @ApiModelProperty(required = true)
    private String labelId;
    @ApiModelProperty(required = true)
    private String viewType;
    private String packLabelType;
}
