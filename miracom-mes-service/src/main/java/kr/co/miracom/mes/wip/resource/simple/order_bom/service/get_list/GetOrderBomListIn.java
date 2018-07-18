package kr.co.miracom.mes.wip.resource.simple.order_bom.service.get_list;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import kr.co.miracom.framework.converter.jackson.DateStrDeserializer;
import kr.co.miracom.framework.service.GetListIn;
import lombok.Getter;
import lombok.Setter;

/**
 * Input V O: OrderBom
 * @author myjung.jung
 * @since 2018. 06. 26.
 */
@Getter
@Setter
public class GetOrderBomListIn extends GetListIn {
    private String orderNo;
    // private String orderDesc;
    // private String lineCode;
    // private String matCode;
    // private String planMonth;
    // private String flowCode;
    // private double ordQty;
    // private double ordInQty;
    // private double ordOutQty;
    // private double rcvGoodQty;
    // private double rcvLossQty;
    // @JsonDeserialize(using = DateStrDeserializer.class)
    // private String ordDate;
    @JsonDeserialize(using = DateStrDeserializer.class)
    private String fromDate;
    @JsonDeserialize(using = DateStrDeserializer.class)
    private String toDate;
    // private String ordStatus;
    // private String ordPriority;
    // private String ordType;
    // private ZonedDateTime ordStartTime;
    // private ZonedDateTime ordEndTime;
    // private String ordComment;
    // private String createUserId;
    // private ZonedDateTime createTime;
    // private String updateUserId;
    // private ZonedDateTime updateTime;
}
