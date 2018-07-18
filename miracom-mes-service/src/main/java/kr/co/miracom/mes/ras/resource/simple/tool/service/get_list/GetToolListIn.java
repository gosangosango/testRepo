package kr.co.miracom.mes.ras.resource.simple.tool.service.get_list;

import kr.co.miracom.framework.service.GetListIn;

import lombok.Getter;
import lombok.Setter;

/**
 * Input V O: Tool
 * @author hhk
 * @since 2018. 07. 03.
 */
@Getter
@Setter
public class GetToolListIn extends GetListIn {
     private String toolCode;
     private String toolDesc;
     private String toolType;
     private String matCode;
    // private Double oneTimeUsageQty;
    // private Double usageQty;
    // private Double totalLifeQty;
    // private String periodCode;
    // private String createUserId;
    // private ZonedDateTime createTime;
    // private String updateUserId;
    // private ZonedDateTime updateTime;
}
