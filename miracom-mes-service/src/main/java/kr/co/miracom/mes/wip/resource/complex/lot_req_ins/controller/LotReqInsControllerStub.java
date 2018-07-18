package kr.co.miracom.mes.wip.resource.complex.lot_req_ins.controller;

import kr.co.miracom.mes.wip.resource.complex.lot_req_ins.service.get.GetLotReqInsIn;
import kr.co.miracom.mes.wip.resource.complex.lot_req_ins.service.get.GetLotReqInsOut;
import kr.co.miracom.mes.wip.resource.complex.lot_req_ins.service.get_list.GetLotReqInsListIn;
import kr.co.miracom.mes.wip.resource.complex.lot_req_ins.service.get_list.GetLotReqInsListOut;
import kr.co.miracom.mes.wip.resource.complex.lot_req_ins.service.post.PostLotReqInsIn;
import kr.co.miracom.mes.wip.resource.complex.lot_req_ins.service.post.PostLotReqInsOut;
import kr.co.miracom.mes.wip.resource.complex.lot_req_ins.service.post_list.PostLotReqInsListIn;
import kr.co.miracom.mes.wip.resource.complex.lot_req_ins.service.post_list.PostLotReqInsListOut;

/**
 * Service Controller: LotReqIns
 * @author gom
 * @since 2018. 07. 05.
 */
public class LotReqInsControllerStub implements LotReqInsController {

    @Override
    public GetLotReqInsOut get(String id, GetLotReqInsIn input) throws Exception {
        return null;
    }

    @Override
    public PostLotReqInsOut post(String id, PostLotReqInsIn input) throws Exception {
        return null;
    }

    @Override
    public GetLotReqInsListOut getList(GetLotReqInsListIn input) throws Exception {
        return null;
    }

    @Override
    public PostLotReqInsListOut postList(PostLotReqInsListIn input) throws Exception {
        return null;
    }

}
