package kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.controller;

import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.backflush_list.BackflushInvLotInpList;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.backflush_list.BackflushInvLotInpListIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.backflush_list.BackflushInvLotInpListOut;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.get.GetInvLotInp;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.get.GetInvLotInpIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.get.GetInvLotInpOut;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.get_list.GetInvLotInpList;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.get_list.GetInvLotInpListIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.get_list.GetInvLotInpListOut;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.load_list.LoadInvLotInpList;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.load_list.LoadInvLotInpListIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.load_list.LoadInvLotInpListOut;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.post.PostInvLotInp;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.post.PostInvLotInpIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.post.PostInvLotInpOut;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.post_list.PostInvLotInpList;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.post_list.PostInvLotInpListIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.post_list.PostInvLotInpListOut;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.unload.UnloadInvLotInp;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.unload.UnloadInvLotInpIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.unload.UnloadInvLotInpOut;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.valid_lot.ValidInvLotInpLot;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.valid_lot.ValidInvLotInpLotIn;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.valid_lot.ValidInvLotInpLotOut;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Service Controller: InvLotInp
 * @author gom
 * @since 2018. 07. 04.
 */
@CrossOrigin
@RestController
public class InvLotInpControllerImpl implements InvLotInpController {

    @Override
    public GetInvLotInpOut get(@PathVariable String id, GetInvLotInpIn input) throws Exception {
        input.setId(id);
        return BeanUtils.get(GetInvLotInp.class).get(input);
    }

    @Override
    public PostInvLotInpOut post(@PathVariable String id, @RequestBody PostInvLotInpIn input) throws Exception {
        input.setId(id);
        return BeanUtils.get(PostInvLotInp.class).post(input);
    }

    @Override
    public GetInvLotInpListOut getList(GetInvLotInpListIn input) throws Exception {
        return BeanUtils.get(GetInvLotInpList.class).getList(input);
    }

    @Override
    public PostInvLotInpListOut postList(@RequestBody PostInvLotInpListIn input) throws Exception {
        return BeanUtils.get(PostInvLotInpList.class).postList(input);
    }

    @Override
    public LoadInvLotInpListOut loadList(@PathVariable String id, LoadInvLotInpListIn input) throws Exception {
        return BeanUtils.get(LoadInvLotInpList.class).loadList(input);
    }
    
    @Override
    public BackflushInvLotInpListOut backflushList(@PathVariable String id, BackflushInvLotInpListIn input) throws Exception {
        return BeanUtils.get(BackflushInvLotInpList.class).backflushList(input);
    }
    
    @Override
    public ValidInvLotInpLotOut validLot(@PathVariable String id, ValidInvLotInpLotIn input) throws Exception {
        return BeanUtils.get(ValidInvLotInpLot.class).validLot(input);
    }
    
    @Override
    public UnloadInvLotInpOut unload(@PathVariable String id, @RequestBody UnloadInvLotInpIn input) throws Exception {
        input.setId(id);
        return BeanUtils.get(UnloadInvLotInp.class).unload(input);
    }
}
