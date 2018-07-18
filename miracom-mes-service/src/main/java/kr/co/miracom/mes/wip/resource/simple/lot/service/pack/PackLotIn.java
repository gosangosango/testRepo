package kr.co.miracom.mes.wip.resource.simple.lot.service.pack;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import kr.co.miracom.mes.wip.resource.simple.lot.model.Pack;
import lombok.Data;

@Data
public class PackLotIn {
    @ApiModelProperty(required = true)
    private String packLabelId;
    private boolean autoNumberingFlag;
    private String matCode;
    @ApiModelProperty(required = true)
    private String packLabelType;
    @ApiModelProperty(required = true)
    private double packCount;
    @ApiModelProperty(required = true)
    private List<Pack> list;
}
