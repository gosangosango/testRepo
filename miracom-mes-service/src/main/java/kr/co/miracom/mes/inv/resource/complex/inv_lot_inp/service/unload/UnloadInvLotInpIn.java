package kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.unload;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.model.LoadInv;
import lombok.Data;

/**
 * Input V O: InvLotInput
 * @author gom
 * @since 2018. 07. 04.
 */
@Data
public class UnloadInvLotInpIn {
    @ApiModelProperty(required = true)
    private String id;
    @ApiModelProperty(required = true)
    private String lineCode;
    @ApiModelProperty(required = true)
    private String orderNo;
    private String operCode;
    private String equipCode;
    @ApiModelProperty(required = true)
    private List<LoadInv> list;
}
