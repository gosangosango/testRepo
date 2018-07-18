package kr.co.miracom.mes.inv.resource.complex.inv_lot_ins.service.post;

import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.model.InvLotDetail;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.service.InvLotService;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.service.instore.InstoreInvLot;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.service.instore.InstoreInvLotIn;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.service.instore.InstoreInvLotOut;

/**
 * Post Service: InvLotIns
 * @author myjung.jung
 * @since 2018. 06. 25.
 */
public class PostInvLotIns {
    public PostInvLotInsOut post(PostInvLotInsIn input) throws Exception {
        String invLotId = input.getInvLotId();

        {
            InstoreInvLotIn reqIn = ValueUtils.populate(input, new InstoreInvLotIn());
            InstoreInvLotOut reqOut = BeanUtils.get(InstoreInvLot.class).instore(reqIn);
            invLotId = reqOut.getInvLotId();
        }

        InvLotDetail invLot = BeanUtils.get(InvLotService.class).get(invLotId, null);

        PostInvLotInsOut output = new PostInvLotInsOut();
        ValueUtils.populate(invLot, output);
        return output;
    }
}
