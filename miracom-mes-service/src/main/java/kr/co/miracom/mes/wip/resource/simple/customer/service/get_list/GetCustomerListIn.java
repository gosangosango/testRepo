package kr.co.miracom.mes.wip.resource.simple.customer.service.get_list;

import kr.co.miracom.framework.service.GetListIn;
import lombok.Getter;
import lombok.Setter;

/**
 * Input V O: Customer
 * @author myjung.jung
 * @since 2018. 06. 14.
 */
@Getter
@Setter
public class GetCustomerListIn extends GetListIn {
    private String customerCode;
    private String customerDesc;
    // private String createUserId;
    // private ZonedDateTime createTime;
    // private String updateUserId;
    // private ZonedDateTime updateTime;
}
