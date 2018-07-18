package kr.co.miracom.mes.wip.resource.complex.lot_pak.controller;

import kr.co.miracom.mes.wip.resource.complex.lot_pak.service.get.GetLotPakIn;
import kr.co.miracom.mes.wip.resource.complex.lot_pak.service.get.GetLotPakOut;
import kr.co.miracom.mes.wip.resource.complex.lot_pak.service.get_list.GetLotPakListIn;
import kr.co.miracom.mes.wip.resource.complex.lot_pak.service.get_list.GetLotPakListOut;
import kr.co.miracom.mes.wip.resource.complex.lot_pak.service.label_list.LabelLotPakListIn;
import kr.co.miracom.mes.wip.resource.complex.lot_pak.service.label_list.LabelLotPakListOut;
import kr.co.miracom.mes.wip.resource.complex.lot_pak.service.pack_qty.PackLotPakQtyIn;
import kr.co.miracom.mes.wip.resource.complex.lot_pak.service.pack_qty.PackLotPakQtyOut;
import kr.co.miracom.mes.wip.resource.complex.lot_pak.service.post.PostLotPakIn;
import kr.co.miracom.mes.wip.resource.complex.lot_pak.service.post.PostLotPakOut;
import kr.co.miracom.mes.wip.resource.complex.lot_pak.service.post_list.PostLotPakListIn;
import kr.co.miracom.mes.wip.resource.complex.lot_pak.service.post_list.PostLotPakListOut;

/**
 * Service Controller: LotPak
 * @author gom
 * @since 2018. 07. 02.
 */
public class LotPakControllerStub implements LotPakController {

    @Override
    public GetLotPakOut get(String id, GetLotPakIn input) throws Exception {
        return null;
    }

    @Override
    public PostLotPakOut post(String id, PostLotPakIn input) throws Exception {
        return null;
    }

    @Override
    public GetLotPakListOut getList(GetLotPakListIn input) throws Exception {
        return null;
    }

    @Override
    public PostLotPakListOut postList(PostLotPakListIn input) throws Exception {
        return null;
    }

    @Override
    public PackLotPakQtyOut packQty(String id, PackLotPakQtyIn input) throws Exception {
        return null;
    }
    
    @Override
    public LabelLotPakListOut labelList(String id, LabelLotPakListIn input) throws Exception {
        return null;
    }
}
