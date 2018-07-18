package kr.co.miracom.mes.wip.resource.complex.lot_end.service.generate_summary;

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
public class GenerateLotEndSummaryIn {
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
    @ApiModelProperty(required = true)
    private String goodType;
    private Double qty;
    @ApiModelProperty(required = true)
    private ZonedDateTime tranTime;
}
