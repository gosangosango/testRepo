package kr.co.miracom.mes.inv.resource.simple.inv_lot.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.model.InvLot;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.model.InvLotDetail;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.service.InvLotService;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.service.get_list.GetInvLotList;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.service.get_list.GetInvLotListIn;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.service.input.InputInvLot;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.service.input.InputInvLotIn;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.service.input.InputInvLotOut;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.service.instore.InstoreInvLot;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.service.instore.InstoreInvLotIn;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.service.instore.InstoreInvLotOut;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.service.unload.UnloadInvLot;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.service.unload.UnloadInvLotIn;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.service.unload.UnloadInvLotOut;

/**
 * Setup Controller: InvLot
 * @author myjung.jung
 * @since 2018. 06. 21.
 */
@CrossOrigin
@RestController
public class InvLotControllerImpl implements InvLotController {

    @Override
    public InvLotDetail get(@PathVariable String id, SelectOptions options) throws Exception {
        return BeanUtils.get(InvLotService.class).get(id, options);
    }

    // @Override
    // public void put(@PathVariable String id, @RequestBody InvLotDetail data,
    // @RequestParam(required = false) String[] fieldName) throws Exception {
    // BeanUtils.get(InvLotService.class).put(id, data, fieldName);
    // }

    @Override
    public GetListOut<InvLot> getList(GetInvLotListIn input) throws Exception {
        return BeanUtils.get(GetInvLotList.class).getList(input);
    }

    // @Override
    // public void saveList(@RequestBody List<InvLot> list, @RequestParam(required = false) String[] fieldName)
    // throws Exception {
    // BeanUtils.get(InvLotService.class).saveList(list, fieldName);
    // }

    // @Override
    // public void deleteList(@RequestBody List<InvLot> list) throws Exception {
    // BeanUtils.get(InvLotService.class).deleteList(list);
    // }

    @Override
    public InstoreInvLotOut instore(@PathVariable String id, @RequestBody InstoreInvLotIn input) throws Exception {
        input.setInvLotId(id);
        return BeanUtils.get(InstoreInvLot.class).instore(input);
    }
    
    @Override
    public InputInvLotOut input(@PathVariable String id, @RequestBody InputInvLotIn input) throws Exception {
        input.setInvLotId(id);
        return BeanUtils.get(InputInvLot.class).input(input);
    }

    @Override
    public UnloadInvLotOut unload(@PathVariable String id, @RequestBody UnloadInvLotIn input) throws Exception {
        input.setInvLotId(id);
        return BeanUtils.get(UnloadInvLot.class).unload(input);
    }
    
}
