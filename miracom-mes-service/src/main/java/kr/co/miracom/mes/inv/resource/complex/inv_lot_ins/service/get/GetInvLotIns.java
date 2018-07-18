package kr.co.miracom.mes.inv.resource.complex.inv_lot_ins.service.get;

import kr.co.miracom.framework.exception.BizException;
import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.model.InvLot;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.service.InvLotService;

/**
 * Get Service: InvLotIns
 * @author myjung.jung
 * @since 2018. 06. 25.
 */
public class GetInvLotIns {
    public GetInvLotInsOut get(GetInvLotInsIn input) throws Exception {
        InvLot invLot = BeanUtils.get(InvLotService.class).get(input.getInvLotId(), null);

        if (invLot.isInOperFlag()) {
            throw new BizException("XXXXXX", "이미 입고된 자재입니다.");
        }

        GetInvLotInsOut output = new GetInvLotInsOut();
        ValueUtils.populate(invLot, output);
        return output;
    }
}
