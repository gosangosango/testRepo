package kr.co.miracom.mes.inv.resource.simple.inv_request.service.get_list;

import kr.co.miracom.framework.service.GetListIn;

import lombok.Getter;
import lombok.Setter;

/**
 * Input V O: InvRequest
 * @author User
 * @since 2018. 07. 03.
 */
@Getter
@Setter
public class GetInvRequestListIn extends GetListIn {
    private String fromDate;
    private String toDate;
    private String lineCode;
    // private String reqNo;
    // private String orderNo;
    // private String reqDate;
    // private String createUserId;
    // @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    // private ZonedDateTime createTime;
    // private String updateUserId;
    // @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    // private ZonedDateTime updateTime;
}
