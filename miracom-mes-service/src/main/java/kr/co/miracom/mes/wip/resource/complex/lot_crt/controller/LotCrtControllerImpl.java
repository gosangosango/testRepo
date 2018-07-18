package kr.co.miracom.mes.wip.resource.complex.lot_crt.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.mes.wip.resource.complex.lot_crt.service.get.GetLotCrt;
import kr.co.miracom.mes.wip.resource.complex.lot_crt.service.get.GetLotCrtIn;
import kr.co.miracom.mes.wip.resource.complex.lot_crt.service.get.GetLotCrtOut;
import kr.co.miracom.mes.wip.resource.complex.lot_crt.service.get_list.GetLotCrtList;
import kr.co.miracom.mes.wip.resource.complex.lot_crt.service.get_list.GetLotCrtListIn;
import kr.co.miracom.mes.wip.resource.complex.lot_crt.service.get_list.GetLotCrtListOut;
import kr.co.miracom.mes.wip.resource.complex.lot_crt.service.post.PostLotCrt;
import kr.co.miracom.mes.wip.resource.complex.lot_crt.service.post.PostLotCrtIn;
import kr.co.miracom.mes.wip.resource.complex.lot_crt.service.post.PostLotCrtOut;
import kr.co.miracom.mes.wip.resource.complex.lot_crt.service.post_list.PostLotCrtList;
import kr.co.miracom.mes.wip.resource.complex.lot_crt.service.post_list.PostLotCrtListIn;
import kr.co.miracom.mes.wip.resource.complex.lot_crt.service.post_list.PostLotCrtListOut;

/**
 * Service Controller: LotCrt
 * @author myjung.jung
 * @since 2018. 06. 25.
 */
@CrossOrigin
@RestController
public class LotCrtControllerImpl implements LotCrtController {

    @Override
    public GetLotCrtOut get(@PathVariable String id, GetLotCrtIn input) throws Exception {
        input.setLotId(id);
        return BeanUtils.get(GetLotCrt.class).get(input);
    }

    @Override
    public PostLotCrtOut post(@PathVariable String id, @RequestBody PostLotCrtIn input) throws Exception {
        input.setLotId(id);
        return BeanUtils.get(PostLotCrt.class).post(input);
    }

    @Override
    public GetLotCrtListOut getList(GetLotCrtListIn input) throws Exception {
        return BeanUtils.get(GetLotCrtList.class).getList(input);
    }

    @Override
    public PostLotCrtListOut postList(@RequestBody PostLotCrtListIn input) throws Exception {
        return BeanUtils.get(PostLotCrtList.class).postList(input);
    }

}
