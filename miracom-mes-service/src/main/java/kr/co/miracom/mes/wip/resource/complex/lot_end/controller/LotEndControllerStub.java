package kr.co.miracom.mes.wip.resource.complex.lot_end.controller;

import kr.co.miracom.mes.wip.resource.complex.lot_end.service.deduct_inv.DeductLotEndInvIn;
import kr.co.miracom.mes.wip.resource.complex.lot_end.service.deduct_inv.DeductLotEndInvOut;
import kr.co.miracom.mes.wip.resource.complex.lot_end.service.get.GetLotEndIn;
import kr.co.miracom.mes.wip.resource.complex.lot_end.service.get.GetLotEndOut;
import kr.co.miracom.mes.wip.resource.complex.lot_end.service.get_list.GetLotEndListIn;
import kr.co.miracom.mes.wip.resource.complex.lot_end.service.get_list.GetLotEndListOut;
import kr.co.miracom.mes.wip.resource.complex.lot_end.service.post.PostLotEndIn;
import kr.co.miracom.mes.wip.resource.complex.lot_end.service.post.PostLotEndOut;
import kr.co.miracom.mes.wip.resource.complex.lot_end.service.post_list.PostLotEndListIn;
import kr.co.miracom.mes.wip.resource.complex.lot_end.service.post_list.PostLotEndListOut;
import kr.co.miracom.mes.wip.resource.complex.lot_end.service.valid_inv.ValidLotEndInvIn;
import kr.co.miracom.mes.wip.resource.complex.lot_end.service.valid_inv.ValidLotEndInvOut;

/**
 * Service Controller: LotEnd
 * @author gom
 * @since 2018. 06. 25.
 */
public class LotEndControllerStub implements LotEndController {

    @Override
    public GetLotEndOut get(String id, GetLotEndIn input) throws Exception {
        return null;
    }

    @Override
    public PostLotEndOut post(String id, PostLotEndIn input) throws Exception {
        return null;
    }

    @Override
    public GetLotEndListOut getList(GetLotEndListIn input) throws Exception {
        return null;
    }

    @Override
    public PostLotEndListOut postList(PostLotEndListIn input) throws Exception {
        return null;
    }

    @Override
    public ValidLotEndInvOut validInv(String id, ValidLotEndInvIn input) throws Exception {
        return null;
    }
    
    @Override
    public DeductLotEndInvOut deductInv(String id, DeductLotEndInvIn input) throws Exception {
        return null;
    }
}
