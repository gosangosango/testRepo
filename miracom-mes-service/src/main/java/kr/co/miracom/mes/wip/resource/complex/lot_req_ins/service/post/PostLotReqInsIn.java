package kr.co.miracom.mes.wip.resource.complex.lot_req_ins.service.post;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import kr.co.miracom.mes.wip.resource.simple.lot.model.ReqLot;
import lombok.Data;

/**
 * Input V O: LotReqIns
 * @author gom
 * @since 2018. 07. 05.
 */
@Data
public class PostLotReqInsIn {
    private String id;
    @ApiModelProperty(required = true)
    private String inspType;
    @ApiModelProperty(required = true)
    private Double inspReqQty;
    @ApiModelProperty(required = true)
    private String matCode;
    @ApiModelProperty(required = true)
    private List<ReqLot> list;
}
