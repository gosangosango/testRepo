package kr.co.miracom.mes.wip.resource.simple.lot.service.request_inspection;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import kr.co.miracom.mes.wip.resource.simple.lot.model.Pack;
import kr.co.miracom.mes.wip.resource.simple.lot.model.ReqLot;
import lombok.Data;

@Data
public class RequestInspectionLotIn {
    @ApiModelProperty(required = true)
    private String inspType;
    @ApiModelProperty(required = true)
    private Double inspReqQty;
    @ApiModelProperty(required = true)
    private String matCode;
    @ApiModelProperty(required = true)
    private String inspNo;
    @ApiModelProperty(required = true)
    private List<ReqLot> list;
}
