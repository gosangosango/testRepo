package kr.co.miracom.mes.inv.resource.simple.inv_lot.service.instore;

import kr.co.miracom.framework.exception.BizException;
import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.inv.model.MInvLotSts;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.service.create.CreateInvLot;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.service.create.CreateInvLotIn;
import kr.co.miracom.mes.inv.util.InvUtils;
import kr.co.miracom.mes.wip.model.MWipMatDef;
import kr.co.miracom.mes.wip.model.MWipOprDef;
import kr.co.miracom.mes.wip.model.MWipVenDef;

public class InstoreInvLot {
    public InstoreInvLotOut instore(InstoreInvLotIn input) throws Exception {
        ValueUtils.checkNotEmpty(input, "invLotId", "operCode", "matCode", "qty");
        DbUtils.checkFound(MWipOprDef.class, input.getOperCode());
        DbUtils.checkFound(MWipMatDef.class, input.getMatCode());
        DbUtils.checkFound(MWipVenDef.class, input.getVendorCode());

        MInvLotSts invLot = DbUtils.selectWithLock(MInvLotSts.class, input);
        if (invLot == null) {
            CreateInvLotIn reqIn = ValueUtils.populate(input, new CreateInvLotIn());
            BeanUtils.get(CreateInvLot.class).create(reqIn);
            invLot = DbUtils.selectWithLock(MInvLotSts.class, input);
        } else {
            if (invLot.isInOperFlag()) {
                throw new BizException("XXXXXX", "이미 입고된 자재입니다.");
            }
        }

        ValueUtils.populate(input, invLot);
        invLot.setInOperFlag(true);

        DbUtils.update(invLot);
        InvUtils.insertInvLotHistory("IN", invLot);

        InstoreInvLotOut output = new InstoreInvLotOut();
        output.setInvLotId(invLot.getInvLotId());
        return output;
    }
}
