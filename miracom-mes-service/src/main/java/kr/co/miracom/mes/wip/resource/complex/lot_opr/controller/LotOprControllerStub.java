package kr.co.miracom.mes.wip.resource.complex.lot_opr.controller;

import kr.co.miracom.mes.wip.resource.complex.lot_opr.service.get.GetLotOprIn;
import kr.co.miracom.mes.wip.resource.complex.lot_opr.service.get.GetLotOprOut;
import kr.co.miracom.mes.wip.resource.complex.lot_opr.service.get_list.GetLotOprListIn;
import kr.co.miracom.mes.wip.resource.complex.lot_opr.service.get_list.GetLotOprListOut;
import kr.co.miracom.mes.wip.resource.complex.lot_opr.service.get_summary.GetLotOprSummaryIn;
import kr.co.miracom.mes.wip.resource.complex.lot_opr.service.get_summary.GetLotOprSummaryOut;
import kr.co.miracom.mes.wip.resource.complex.lot_opr.service.load_list.LoadLotOprListIn;
import kr.co.miracom.mes.wip.resource.complex.lot_opr.service.load_list.LoadLotOprListOut;
import kr.co.miracom.mes.wip.resource.complex.lot_opr.service.post.PostLotOprIn;
import kr.co.miracom.mes.wip.resource.complex.lot_opr.service.post.PostLotOprOut;
import kr.co.miracom.mes.wip.resource.complex.lot_opr.service.post_list.PostLotOprListIn;
import kr.co.miracom.mes.wip.resource.complex.lot_opr.service.post_list.PostLotOprListOut;

/**
 * Service Controller: Lot_opr
 * @author gom
 * @since 2018. 06. 25.
 */
public class LotOprControllerStub implements LotOprController {

    @Override
    public GetLotOprOut get(String id, GetLotOprIn input) throws Exception {
        return null;
    }

    @Override
    public PostLotOprOut post(String id, PostLotOprIn input) throws Exception {
        return null;
    }

    @Override
    public GetLotOprListOut getList(GetLotOprListIn input) throws Exception {
        return null;
    }

    @Override
    public PostLotOprListOut postList(PostLotOprListIn input) throws Exception {
        return null;
    }
    
    @Override
    public GetLotOprSummaryOut getSummary(String id, GetLotOprSummaryIn input) throws Exception {
        return null;
    }
    
    @Override
    public LoadLotOprListOut loadList(String id, LoadLotOprListIn input) throws Exception {
        return null;
    }
}
