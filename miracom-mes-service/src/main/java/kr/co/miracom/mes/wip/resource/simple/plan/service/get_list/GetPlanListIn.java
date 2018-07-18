package kr.co.miracom.mes.wip.resource.simple.plan.service.get_list;

import kr.co.miracom.framework.service.GetListIn;
import lombok.Getter;
import lombok.Setter;

/**
 * Input V O: Plan
 * @author myjung.jung
 * @since 2018. 06. 19.
 */
@Getter
@Setter
public class GetPlanListIn extends GetListIn {
    private String planMonth;
    private String matCode;
    private String matDesc;
    // private double planQty;
    // private String createUserId;
    // private ZonedDateTime createTime;
    // private String updateUserId;
    // private ZonedDateTime updateTime;
}
