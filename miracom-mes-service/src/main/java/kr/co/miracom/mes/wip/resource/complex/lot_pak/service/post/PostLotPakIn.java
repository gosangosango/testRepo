package kr.co.miracom.mes.wip.resource.complex.lot_pak.service.post;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import kr.co.miracom.mes.wip.resource.simple.lot.model.Pack;
import lombok.Data;

/**
 * Input V O: LotPak
 * @author gom
 * @since 2018. 07. 02.
 */
@Data
public class PostLotPakIn {
    private String id;
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
