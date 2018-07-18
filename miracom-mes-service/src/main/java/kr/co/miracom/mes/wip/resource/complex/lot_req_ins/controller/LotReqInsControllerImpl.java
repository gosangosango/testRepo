package kr.co.miracom.mes.wip.resource.complex.lot_req_ins.controller;

import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.mes.wip.resource.complex.lot_req_ins.service.get.GetLotReqIns;
import kr.co.miracom.mes.wip.resource.complex.lot_req_ins.service.get.GetLotReqInsIn;
import kr.co.miracom.mes.wip.resource.complex.lot_req_ins.service.get.GetLotReqInsOut;
import kr.co.miracom.mes.wip.resource.complex.lot_req_ins.service.get_list.GetLotReqInsList;
import kr.co.miracom.mes.wip.resource.complex.lot_req_ins.service.get_list.GetLotReqInsListIn;
import kr.co.miracom.mes.wip.resource.complex.lot_req_ins.service.get_list.GetLotReqInsListOut;
import kr.co.miracom.mes.wip.resource.complex.lot_req_ins.service.post.PostLotReqIns;
import kr.co.miracom.mes.wip.resource.complex.lot_req_ins.service.post.PostLotReqInsIn;
import kr.co.miracom.mes.wip.resource.complex.lot_req_ins.service.post.PostLotReqInsOut;
import kr.co.miracom.mes.wip.resource.complex.lot_req_ins.service.post_list.PostLotReqInsList;
import kr.co.miracom.mes.wip.resource.complex.lot_req_ins.service.post_list.PostLotReqInsListIn;
import kr.co.miracom.mes.wip.resource.complex.lot_req_ins.service.post_list.PostLotReqInsListOut;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Service Controller: LotReqIns
 * @author gom
 * @since 2018. 07. 05.
 */
@CrossOrigin
@RestController
public class LotReqInsControllerImpl implements LotReqInsController {

    @Override
    public GetLotReqInsOut get(@PathVariable String id, GetLotReqInsIn input) throws Exception {
        input.setId(id);
        return BeanUtils.get(GetLotReqIns.class).get(input);
    }

    @Override
    public PostLotReqInsOut post(@PathVariable String id, @RequestBody PostLotReqInsIn input) throws Exception {
        input.setId(id);
        return BeanUtils.get(PostLotReqIns.class).post(input);
    }

    @Override
    public GetLotReqInsListOut getList(GetLotReqInsListIn input) throws Exception {
        return BeanUtils.get(GetLotReqInsList.class).getList(input);
    }

    @Override
    public PostLotReqInsListOut postList(@RequestBody PostLotReqInsListIn input) throws Exception {
        return BeanUtils.get(PostLotReqInsList.class).postList(input);
    }

}
