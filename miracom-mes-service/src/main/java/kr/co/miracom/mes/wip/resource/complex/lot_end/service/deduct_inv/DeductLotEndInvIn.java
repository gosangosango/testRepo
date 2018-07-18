package kr.co.miracom.mes.wip.resource.complex.lot_end.service.deduct_inv;

import java.time.ZonedDateTime;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import kr.co.miracom.mes.wip.resource.complex.lot_end.model.InputMat;
import lombok.Data;

/**
 * Input V O: LotEnd
 * @author gom
 * @since 2018. 06. 25.
 */
@Data
public class DeductLotEndInvIn {
    @ApiModelProperty(required = true)
    private String lotId;
    @ApiModelProperty(required = true)
    private String orderNo;
    @ApiModelProperty(required = true)
    private String lineCode;
    @ApiModelProperty(required = true)
    private String operCode;
    private boolean lastFlag;
    private String equipCode;
    private Double qty;
    private List<InputMat> list;
    private ZonedDateTime tranTime;
}
