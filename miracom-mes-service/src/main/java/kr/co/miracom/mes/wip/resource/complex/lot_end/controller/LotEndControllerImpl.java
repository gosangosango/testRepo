package kr.co.miracom.mes.wip.resource.complex.lot_end.controller;

import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.mes.wip.resource.complex.lot_end.service.deduct_inv.DeductLotEndInv;
import kr.co.miracom.mes.wip.resource.complex.lot_end.service.deduct_inv.DeductLotEndInvIn;
import kr.co.miracom.mes.wip.resource.complex.lot_end.service.deduct_inv.DeductLotEndInvOut;
import kr.co.miracom.mes.wip.resource.complex.lot_end.service.get.GetLotEnd;
import kr.co.miracom.mes.wip.resource.complex.lot_end.service.get.GetLotEndIn;
import kr.co.miracom.mes.wip.resource.complex.lot_end.service.get.GetLotEndOut;
import kr.co.miracom.mes.wip.resource.complex.lot_end.service.get_list.GetLotEndList;
import kr.co.miracom.mes.wip.resource.complex.lot_end.service.get_list.GetLotEndListIn;
import kr.co.miracom.mes.wip.resource.complex.lot_end.service.get_list.GetLotEndListOut;
import kr.co.miracom.mes.wip.resource.complex.lot_end.service.post.PostLotEnd;
import kr.co.miracom.mes.wip.resource.complex.lot_end.service.post.PostLotEndIn;
import kr.co.miracom.mes.wip.resource.complex.lot_end.service.post.PostLotEndOut;
import kr.co.miracom.mes.wip.resource.complex.lot_end.service.post_list.PostLotEndList;
import kr.co.miracom.mes.wip.resource.complex.lot_end.service.post_list.PostLotEndListIn;
import kr.co.miracom.mes.wip.resource.complex.lot_end.service.post_list.PostLotEndListOut;
import kr.co.miracom.mes.wip.resource.complex.lot_end.service.valid_inv.ValidLotEndInvIn;
import kr.co.miracom.mes.wip.resource.complex.lot_end.service.valid_inv.ValidLotEndInvOut;
import kr.co.miracom.mes.wip.resource.complex.lot_end.service.valid_inv.ValidLotEndInv;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Service Controller: LotEnd
 * @author gom
 * @since 2018. 06. 25.
 */
@CrossOrigin
@RestController
public class LotEndControllerImpl implements LotEndController {

    @Override
    public GetLotEndOut get(@PathVariable String id, GetLotEndIn input) throws Exception {
        input.setLotId(id);
        return BeanUtils.get(GetLotEnd.class).get(input);
    }

    @Override
    public PostLotEndOut post(@PathVariable String id, @RequestBody PostLotEndIn input) throws Exception {
        input.setLotId(id);
        return BeanUtils.get(PostLotEnd.class).post(input);
    }

    @Override
    public GetLotEndListOut getList(GetLotEndListIn input) throws Exception {
        return BeanUtils.get(GetLotEndList.class).getList(input);
    }

    @Override
    public PostLotEndListOut postList(@RequestBody PostLotEndListIn input) throws Exception {
        return BeanUtils.get(PostLotEndList.class).postList(input);
    }

    @Override
    public ValidLotEndInvOut validInv(@PathVariable String id, ValidLotEndInvIn input) throws Exception {
        return BeanUtils.get(ValidLotEndInv.class).validInv(input);
    }
    
    @Override
    public DeductLotEndInvOut deductInv(@PathVariable String id, @RequestBody DeductLotEndInvIn input) throws Exception {
        return BeanUtils.get(DeductLotEndInv.class).deductInv(input);
    }
}
