package kr.co.miracom.mes.wip.resource.complex.lot_req_ins.service.get;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Input V O: LotReqIns
 * @author gom
 * @since 2018. 07. 05.
 */
@Data
public class GetLotReqInsIn {
    private String id;
    @ApiModelProperty(required = true)
    private String fromDate;
    @ApiModelProperty(required = true)
    private String toDate;
    private String lineCode;
    private String matCode;
    private String orderNo;
}
