package kr.co.miracom.mes.wip.resource.simple.vendor.service.get_list;

import kr.co.miracom.framework.service.GetListIn;
import lombok.Getter;
import lombok.Setter;

/**
 * Input V O: Vendor
 * @author myjung.jung
 * @since 2018. 06. 14.
 */
@Getter
@Setter
public class GetVendorListIn extends GetListIn {
    private String vendorCode;
    private String vendorDesc;
    // private String createUserId;
    // private ZonedDateTime createTime;
    // private String updateUserId;
    // private ZonedDateTime updateTime;
}
