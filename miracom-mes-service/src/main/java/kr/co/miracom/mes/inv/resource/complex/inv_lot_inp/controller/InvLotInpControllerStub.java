package kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.controller;

import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.backflush_list.BackflushInvLotInpListIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.backflush_list.BackflushInvLotInpListOut;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.get.GetInvLotInpIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.get.GetInvLotInpOut;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.get_list.GetInvLotInpListIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.get_list.GetInvLotInpListOut;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.load_list.LoadInvLotInpListIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.load_list.LoadInvLotInpListOut;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.post.PostInvLotInpIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.post.PostInvLotInpOut;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.post_list.PostInvLotInpListIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.post_list.PostInvLotInpListOut;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.unload.UnloadInvLotInpIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.unload.UnloadInvLotInpOut;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.valid_lot.ValidInvLotInpLotIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.valid_lot.ValidInvLotInpLotOut;

/**
 * Service Controller: InvLotInp
 * @author gom
 * @since 2018. 07. 04.
 */
public class InvLotInpControllerStub implements InvLotInpController {

    @Override
    public GetInvLotInpOut get(String id, GetInvLotInpIn input) throws Exception {
        return null;
    }

    @Override
    public PostInvLotInpOut post(String id, PostInvLotInpIn input) throws Exception {
        return null;
    }

    @Override
    public GetInvLotInpListOut getList(GetInvLotInpListIn input) throws Exception {
        return null;
    }

    @Override
    public PostInvLotInpListOut postList(PostInvLotInpListIn input) throws Exception {
        return null;
    }
    
    @Override
    public LoadInvLotInpListOut loadList(String id, LoadInvLotInpListIn input) throws Exception {
        return null;
    }

    @Override
    public BackflushInvLotInpListOut backflushList(String id, BackflushInvLotInpListIn input) throws Exception {
        return null;
    }
    
    @Override
    public ValidInvLotInpLotOut validLot(String id, ValidInvLotInpLotIn input) throws Exception {
        return null;
    }
    
    @Override
    public UnloadInvLotInpOut unload(String id, UnloadInvLotInpIn input) throws Exception {
        return null;
    }
}
