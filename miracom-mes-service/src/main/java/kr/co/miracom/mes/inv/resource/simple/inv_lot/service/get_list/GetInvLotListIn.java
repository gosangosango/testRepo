package kr.co.miracom.mes.inv.resource.simple.inv_lot.service.get_list;

import kr.co.miracom.framework.service.GetListIn;
import lombok.Getter;
import lombok.Setter;

/**
 * Input V O: InvLot
 * @author myjung.jung
 * @since 2018. 06. 21.
 */
@Getter
@Setter
public class GetInvLotListIn extends GetListIn {
    private String invLotId;
    private String operCode;
    private String matCode;
    // private boolean invFlag;
     private double qty;
     private boolean positiveQty;
    // private boolean inOperFlag;
     private String inputOrderNo;
    // @JsonDeserialize(using = DateStrDeserializer.class)
    // private String recvDate;
    // private double recvQty;
    // private String lastTranCode;
    // private ZonedDateTime lastTranTime;
    // private boolean holdFlag;
    // private String holdCode;
    // private String fromToType;
    // private String fromToLotId;
    // private String vendorCode;
    // private String locNo;
    // private boolean lotDelFlag;
    // private String lotDelCode;
    // private ZonedDateTime lotDelTime;
    // private String createUserId;
    // private ZonedDateTime createTime;
    // private String updateUserId;
    // private ZonedDateTime updateTime;
}
