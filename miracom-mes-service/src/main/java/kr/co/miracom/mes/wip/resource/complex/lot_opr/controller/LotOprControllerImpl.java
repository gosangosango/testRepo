package kr.co.miracom.mes.wip.resource.complex.lot_opr.controller;

import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.mes.wip.resource.complex.lot_opr.service.get.GetLotOpr;
import kr.co.miracom.mes.wip.resource.complex.lot_opr.service.get.GetLotOprIn;
import kr.co.miracom.mes.wip.resource.complex.lot_opr.service.get.GetLotOprOut;
import kr.co.miracom.mes.wip.resource.complex.lot_opr.service.get_list.GetLotOprList;
import kr.co.miracom.mes.wip.resource.complex.lot_opr.service.get_list.GetLotOprListIn;
import kr.co.miracom.mes.wip.resource.complex.lot_opr.service.get_list.GetLotOprListOut;
import kr.co.miracom.mes.wip.resource.complex.lot_opr.service.get_summary.GetLotOprSummary;
import kr.co.miracom.mes.wip.resource.complex.lot_opr.service.get_summary.GetLotOprSummaryIn;
import kr.co.miracom.mes.wip.resource.complex.lot_opr.service.get_summary.GetLotOprSummaryOut;
import kr.co.miracom.mes.wip.resource.complex.lot_opr.service.load_list.LoadLotOprList;
import kr.co.miracom.mes.wip.resource.complex.lot_opr.service.load_list.LoadLotOprListIn;
import kr.co.miracom.mes.wip.resource.complex.lot_opr.service.load_list.LoadLotOprListOut;
import kr.co.miracom.mes.wip.resource.complex.lot_opr.service.post.PostLotOpr;
import kr.co.miracom.mes.wip.resource.complex.lot_opr.service.post.PostLotOprIn;
import kr.co.miracom.mes.wip.resource.complex.lot_opr.service.post.PostLotOprOut;
import kr.co.miracom.mes.wip.resource.complex.lot_opr.service.post_list.PostLotOprList;
import kr.co.miracom.mes.wip.resource.complex.lot_opr.service.post_list.PostLotOprListIn;
import kr.co.miracom.mes.wip.resource.complex.lot_opr.service.post_list.PostLotOprListOut;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Service Controller: Lot_opr
 * @author gom
 * @since 2018. 06. 25.
 */
@CrossOrigin
@RestController
public class LotOprControllerImpl implements LotOprController {

    @Override
    public GetLotOprOut get(@PathVariable String id, GetLotOprIn input) throws Exception {
        return BeanUtils.get(GetLotOpr.class).get(input);
    }

    @Override
    public PostLotOprOut post(@PathVariable String id, @RequestBody PostLotOprIn input) throws Exception {
        return BeanUtils.get(PostLotOpr.class).post(input);
    }

    @Override
    public GetLotOprListOut getList(GetLotOprListIn input) throws Exception {
        return BeanUtils.get(GetLotOprList.class).getList(input);
    }

    @Override
    public PostLotOprListOut postList(@RequestBody PostLotOprListIn input) throws Exception {
        return BeanUtils.get(PostLotOprList.class).postList(input);
    }

    @Override
    public GetLotOprSummaryOut getSummary(@PathVariable String id, GetLotOprSummaryIn input) throws Exception {
        return BeanUtils.get(GetLotOprSummary.class).getSummary(input);
    }
    
    @Override
    public LoadLotOprListOut loadList(@PathVariable String id, LoadLotOprListIn input) throws Exception {
        return BeanUtils.get(LoadLotOprList.class).getLoadMatList(input);
    }
    
}
