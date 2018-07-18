package kr.co.miracom.mes.inv.resource.complex.inv_lot_ins.controller;

import kr.co.miracom.mes.inv.resource.complex.inv_lot_ins.service.get.GetInvLotInsIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_ins.service.get.GetInvLotInsOut;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_ins.service.get_list.GetInvLotInsListIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_ins.service.get_list.GetInvLotInsListOut;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_ins.service.post.PostInvLotInsIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_ins.service.post.PostInvLotInsOut;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_ins.service.post_list.PostInvLotInsListIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_ins.service.post_list.PostInvLotInsListOut;

/**
 * Service Controller: InvLotIns
 * @author myjung.jung
 * @since 2018. 06. 25.
 */
public class InvLotInsControllerStub implements InvLotInsController {

    @Override
    public GetInvLotInsOut get(String id, GetInvLotInsIn input) throws Exception {
        return null;
    }

    @Override
    public PostInvLotInsOut post(String id, PostInvLotInsIn input) throws Exception {
        return null;
    }

    @Override
    public GetInvLotInsListOut getList(GetInvLotInsListIn input) throws Exception {
        return null;
    }

    @Override
    public PostInvLotInsListOut postList(PostInvLotInsListIn input) throws Exception {
        return null;
    }

}
