package kr.co.miracom.mes.wip.resource.complex.lot_pak.service.pack_qty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Input V O: LotPak
 * @author gom
 * @since 2018. 07. 02.
 */
@Data
public class PackLotPakQtyIn {
    @ApiModelProperty(required = true)
    private String matCode;
    @ApiModelProperty(required = true)
    private String labelType;
}
