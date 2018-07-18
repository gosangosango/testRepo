package kr.co.miracom.mes.wip.resource.simple.material.service.get_list;

import java.util.List;

import kr.co.miracom.framework.service.GetListIn;
import lombok.Getter;
import lombok.Setter;

/**
 * Input V O: Material
 * @author myjung.jung
 * @since 2018. 06. 05.
 */
@Getter
@Setter
public class GetMaterialListIn extends GetListIn {
    private String matCode;
    private String matDesc;
    private String matShortDesc;
    // private boolean purchaseInspFlag;
    // private boolean importInspFlag;
    // private boolean outgoingInspFlag;
    private List<String> matType;
    private List<String> matGrp;
    // private Double weightNet;
    // private Double weightGross;
    // private String volumeUnit;
    // private Double volume;
    // private Double dimensionHr;
    // private String dimensionHrUnit;
    // private Double dimensionVt;
    // private String dimensionVtUnit;
    // private Double dimensionHt;
    // private String dimensionHtUnit;
    // private String packType;
    // private Double packQty;
    // private String deductionType;
    // private boolean fifoFlag;
    private boolean includeDeleteFlag;
    // private String deleteUserId;
    // private ZonedDateTime deleteTime;
    // private String createUserId;
    // private ZonedDateTime createTime;
    // private String updateUserId;
    // private ZonedDateTime updateTime;
    private boolean planSetOnlyFlag;
    private String planMonth;
}
