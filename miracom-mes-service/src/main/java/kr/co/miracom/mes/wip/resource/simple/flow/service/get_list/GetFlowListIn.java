package kr.co.miracom.mes.wip.resource.simple.flow.service.get_list;

import kr.co.miracom.framework.service.GetListIn;
import lombok.Getter;
import lombok.Setter;

/**
 * Input V O: Flow
 * @author myjung.jung
 * @since 2018. 06. 05.
 */
@Getter
@Setter
public class GetFlowListIn extends GetListIn {
    private String flowCode;
    private String flowDesc;
    // private String firstOperCode;
    // private String lastOperCode;
    // private String createUserId;
    // private ZonedDateTime createTime;
    // private String updateUserId;
    // private ZonedDateTime updateTime;
}
