package kr.co.miracom.mes.inv.resource.simple.delivery.service.get_list;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import kr.co.miracom.framework.converter.jackson.DateStrDeserializer;
import kr.co.miracom.framework.service.GetListIn;
import lombok.Getter;
import lombok.Setter;

/**
 * Input V O: Delivery
 * @author myjung.jung
 * @since 2018. 06. 25.
 */
@Getter
@Setter
public class GetDeliveryListIn extends GetListIn {
    private String dlvNo;
    private String poNo;
    // private int poSeq;
    // private String dlvExpDate;
    @JsonDeserialize(using = DateStrDeserializer.class)
    private String fromDate;
    @JsonDeserialize(using = DateStrDeserializer.class)
    private String toDate;
    private String vendorCode;
    // private String purInspUserId;
    // private ZonedDateTime purInspTime;
    private boolean purInspCompOnlyFlag;
    private boolean purInspIncompOnlyFlag;
    // @JsonDeserialize(using = DateStrDeserializer.class)
    // private String purInspDate;
    // private String createUserId;
    // private ZonedDateTime createTime;
    // private String updateUserId;
    // private ZonedDateTime updateTime;
}
