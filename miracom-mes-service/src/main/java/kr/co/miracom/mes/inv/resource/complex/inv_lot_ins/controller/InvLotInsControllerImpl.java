package kr.co.miracom.mes.inv.resource.complex.inv_lot_ins.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_ins.service.get.GetInvLotIns;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_ins.service.get.GetInvLotInsIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_ins.service.get.GetInvLotInsOut;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_ins.service.get_list.GetInvLotInsList;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_ins.service.get_list.GetInvLotInsListIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_ins.service.get_list.GetInvLotInsListOut;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_ins.service.post.PostInvLotIns;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_ins.service.post.PostInvLotInsIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_ins.service.post.PostInvLotInsOut;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_ins.service.post_list.PostInvLotInsList;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_ins.service.post_list.PostInvLotInsListIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_ins.service.post_list.PostInvLotInsListOut;

/**
 * Service Controller: InvLotIns
 * @author myjung.jung
 * @since 2018. 06. 25.
 */
@CrossOrigin
@RestController
public class InvLotInsControllerImpl implements InvLotInsController {

    @Override
    public GetInvLotInsOut get(@PathVariable String id, GetInvLotInsIn input) throws Exception {
        input.setInvLotId(id);
        return BeanUtils.get(GetInvLotIns.class).get(input);
    }

    @Override
    public PostInvLotInsOut post(@PathVariable String id, @RequestBody PostInvLotInsIn input) throws Exception {
        input.setInvLotId(id);
        return BeanUtils.get(PostInvLotIns.class).post(input);
    }

    @Override
    public GetInvLotInsListOut getList(GetInvLotInsListIn input) throws Exception {
        return BeanUtils.get(GetInvLotInsList.class).getList(input);
    }

    @Override
    public PostInvLotInsListOut postList(@RequestBody PostInvLotInsListIn input) throws Exception {
        return BeanUtils.get(PostInvLotInsList.class).postList(input);
    }

}
