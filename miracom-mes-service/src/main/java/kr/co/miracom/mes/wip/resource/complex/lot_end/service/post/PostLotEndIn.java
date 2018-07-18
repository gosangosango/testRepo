package kr.co.miracom.mes.wip.resource.complex.lot_end.service.post;

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
public class PostLotEndIn {
    @ApiModelProperty(required = true)
    private String lotId;
    @ApiModelProperty(required = true)
    private String operCode;
    @ApiModelProperty(required = true)
    private String matCode;
    @ApiModelProperty(required = true)
    private String goodType;
    private double qty;
    private String equipCode;
    private String lotComment;
    private List<InputMat> list;
}
