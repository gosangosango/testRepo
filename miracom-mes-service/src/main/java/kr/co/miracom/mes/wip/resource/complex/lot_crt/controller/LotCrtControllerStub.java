package kr.co.miracom.mes.wip.resource.complex.lot_crt.controller;

import kr.co.miracom.mes.wip.resource.complex.lot_crt.service.get.GetLotCrtIn;
import kr.co.miracom.mes.wip.resource.complex.lot_crt.service.get.GetLotCrtOut;
import kr.co.miracom.mes.wip.resource.complex.lot_crt.service.get_list.GetLotCrtListIn;
import kr.co.miracom.mes.wip.resource.complex.lot_crt.service.get_list.GetLotCrtListOut;
import kr.co.miracom.mes.wip.resource.complex.lot_crt.service.post.PostLotCrtIn;
import kr.co.miracom.mes.wip.resource.complex.lot_crt.service.post.PostLotCrtOut;
import kr.co.miracom.mes.wip.resource.complex.lot_crt.service.post_list.PostLotCrtListIn;
import kr.co.miracom.mes.wip.resource.complex.lot_crt.service.post_list.PostLotCrtListOut;

/**
 * Service Controller: LotCrt
 * @author myjung.jung
 * @since 2018. 06. 25.
 */
public class LotCrtControllerStub implements LotCrtController {

    @Override
    public GetLotCrtOut get(String id, GetLotCrtIn input) throws Exception {
        return null;
    }

    @Override
    public PostLotCrtOut post(String id, PostLotCrtIn input) throws Exception {
        return null;
    }

    @Override
    public GetLotCrtListOut getList(GetLotCrtListIn input) throws Exception {
        return null;
    }

    @Override
    public PostLotCrtListOut postList(PostLotCrtListIn input) throws Exception {
        return null;
    }

}
