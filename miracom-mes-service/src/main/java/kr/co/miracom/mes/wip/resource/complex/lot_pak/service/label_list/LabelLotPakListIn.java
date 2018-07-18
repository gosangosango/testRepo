package kr.co.miracom.mes.wip.resource.complex.lot_pak.service.label_list;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Input V O: LotPak
 * @author gom
 * @since 2018. 07. 02.
 */
@Data
public class LabelLotPakListIn {
    @ApiModelProperty(required = true)
    private String lineCode;
    @ApiModelProperty(required = true)
    private String matCode;
    @ApiModelProperty(required = true)
    private String labelType;
}
