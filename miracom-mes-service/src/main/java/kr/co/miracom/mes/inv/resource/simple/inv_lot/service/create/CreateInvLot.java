package kr.co.miracom.mes.inv.resource.simple.inv_lot.service.create;

import kr.co.miracom.framework.exception.BizException;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.inv.model.MInvLotSts;
import kr.co.miracom.mes.inv.util.InvUtils;
import kr.co.miracom.mes.wip.model.MWipMatDef;
import kr.co.miracom.mes.wip.model.MWipOprDef;
import kr.co.miracom.mes.wip.model.MWipVenDef;

public class CreateInvLot {
    public CreateInvLotOut create(CreateInvLotIn input) throws Exception {
        ValueUtils.checkNotEmpty(input, "invLotId", "operCode", "matCode", "qty");
        DbUtils.checkFound(MWipOprDef.class, input.getOperCode());
        DbUtils.checkFound(MWipMatDef.class, input.getMatCode());
        DbUtils.checkFound(MWipVenDef.class, input.getVendorCode());

        MInvLotSts invLot = DbUtils.selectWithLock(MInvLotSts.class, input);
        if (invLot != null) {
            throw new BizException("XXXXXX", "이미 있는 자재Lot입니다.");
        }
        invLot = new MInvLotSts();
        invLot.setFactoryCode(AuthUtils.getFactoryCode());

        ValueUtils.populate(input, invLot);

        DbUtils.insert(invLot);
        InvUtils.insertInvLotHistory("CREATE", invLot);

        CreateInvLotOut output = new CreateInvLotOut();
        output.setInvLotId(invLot.getInvLotId());
        return output;
    }
}
