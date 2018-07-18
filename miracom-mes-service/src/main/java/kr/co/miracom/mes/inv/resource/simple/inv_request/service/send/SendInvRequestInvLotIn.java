package kr.co.miracom.mes.inv.resource.simple.inv_request.service.send;

import java.util.List;

import kr.co.miracom.mes.inv.resource.simple.inv_request.model.InvRequestInvLot;
import lombok.Data;

/**
 * @author User
 *
 */
@Data
public class SendInvRequestInvLotIn {
    private String fromOperCode;
    private String toOperCode;
    private String noReqType;
    private String reason;
    private String reqNo;
    private List<InvRequestInvLot> list;
}
