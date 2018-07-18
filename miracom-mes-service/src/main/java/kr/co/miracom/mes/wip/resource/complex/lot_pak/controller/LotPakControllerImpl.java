package kr.co.miracom.mes.wip.resource.complex.lot_pak.controller;

import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.mes.wip.resource.complex.lot_pak.service.get.GetLotPak;
import kr.co.miracom.mes.wip.resource.complex.lot_pak.service.get.GetLotPakIn;
import kr.co.miracom.mes.wip.resource.complex.lot_pak.service.get.GetLotPakOut;
import kr.co.miracom.mes.wip.resource.complex.lot_pak.service.get_list.GetLotPakList;
import kr.co.miracom.mes.wip.resource.complex.lot_pak.service.get_list.GetLotPakListIn;
import kr.co.miracom.mes.wip.resource.complex.lot_pak.service.get_list.GetLotPakListOut;
import kr.co.miracom.mes.wip.resource.complex.lot_pak.service.label_list.LabelLotPakList;
import kr.co.miracom.mes.wip.resource.complex.lot_pak.service.label_list.LabelLotPakListIn;
import kr.co.miracom.mes.wip.resource.complex.lot_pak.service.label_list.LabelLotPakListOut;
import kr.co.miracom.mes.wip.resource.complex.lot_pak.service.pack_qty.PackLotPakQty;
import kr.co.miracom.mes.wip.resource.complex.lot_pak.service.pack_qty.PackLotPakQtyIn;
import kr.co.miracom.mes.wip.resource.complex.lot_pak.service.pack_qty.PackLotPakQtyOut;
import kr.co.miracom.mes.wip.resource.complex.lot_pak.service.post.PostLotPak;
import kr.co.miracom.mes.wip.resource.complex.lot_pak.service.post.PostLotPakIn;
import kr.co.miracom.mes.wip.resource.complex.lot_pak.service.post.PostLotPakOut;
import kr.co.miracom.mes.wip.resource.complex.lot_pak.service.post_list.PostLotPakList;
import kr.co.miracom.mes.wip.resource.complex.lot_pak.service.post_list.PostLotPakListIn;
import kr.co.miracom.mes.wip.resource.complex.lot_pak.service.post_list.PostLotPakListOut;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Service Controller: LotPak
 * @author gom
 * @since 2018. 07. 02.
 */
@CrossOrigin
@RestController
public class LotPakControllerImpl implements LotPakController {

    @Override
    public GetLotPakOut get(@PathVariable String id, GetLotPakIn input) throws Exception {
        input.setLabelId(id);
        return BeanUtils.get(GetLotPak.class).get(input);
    }

    @Override
    public PostLotPakOut post(@PathVariable String id, @RequestBody PostLotPakIn input) throws Exception {
        input.setId(id);
        return BeanUtils.get(PostLotPak.class).post(input);
    }

    @Override
    public GetLotPakListOut getList(GetLotPakListIn input) throws Exception {
        return BeanUtils.get(GetLotPakList.class).getList(input);
    }

    @Override
    public PostLotPakListOut postList(@RequestBody PostLotPakListIn input) throws Exception {
        return BeanUtils.get(PostLotPakList.class).postList(input);
    }

    @Override
    public PackLotPakQtyOut packQty(@PathVariable String id, PackLotPakQtyIn input) throws Exception {
        return BeanUtils.get(PackLotPakQty.class).packQty(input);
    }
    
    @Override
    public LabelLotPakListOut labelList(@PathVariable String id, LabelLotPakListIn input) throws Exception {
        return BeanUtils.get(LabelLotPakList.class).labelList(input);
    }
}
