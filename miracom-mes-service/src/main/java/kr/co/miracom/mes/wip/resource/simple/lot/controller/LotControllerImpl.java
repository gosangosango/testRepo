package kr.co.miracom.mes.wip.resource.simple.lot.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.mes.wip.resource.simple.lot.model.Lot;
import kr.co.miracom.mes.wip.resource.simple.lot.model.LotDetail;
import kr.co.miracom.mes.wip.resource.simple.lot.service.LotService;
import kr.co.miracom.mes.wip.resource.simple.lot.service.create.CreateLot;
import kr.co.miracom.mes.wip.resource.simple.lot.service.create.CreateLotIn;
import kr.co.miracom.mes.wip.resource.simple.lot.service.create.CreateLotOut;
import kr.co.miracom.mes.wip.resource.simple.lot.service.end.EndLot;
import kr.co.miracom.mes.wip.resource.simple.lot.service.end.EndLotIn;
import kr.co.miracom.mes.wip.resource.simple.lot.service.end.EndLotOut;
import kr.co.miracom.mes.wip.resource.simple.lot.service.get_list.GetLotList;
import kr.co.miracom.mes.wip.resource.simple.lot.service.get_list.GetLotListIn;
import kr.co.miracom.mes.wip.resource.simple.lot.service.pack.PackLot;
import kr.co.miracom.mes.wip.resource.simple.lot.service.pack.PackLotIn;
import kr.co.miracom.mes.wip.resource.simple.lot.service.pack.PackLotOut;
import kr.co.miracom.mes.wip.resource.simple.lot.service.request_inspection.RequestInspectionLot;
import kr.co.miracom.mes.wip.resource.simple.lot.service.request_inspection.RequestInspectionLotIn;
import kr.co.miracom.mes.wip.resource.simple.lot.service.request_inspection.RequestInspectionLotOut;

/**
 * Setup Controller: Lot
 * @author gom
 * @since 2018. 06. 21.
 */
@CrossOrigin
@RestController
public class LotControllerImpl implements LotController {

    @Override
    public LotDetail get(@PathVariable String id, SelectOptions options) throws Exception {
        return BeanUtils.get(LotService.class).get(id, options);
    }

    @Override
    public void put(@PathVariable String id, @RequestBody LotDetail data,
                    @RequestParam(required = false) String[] fieldName) throws Exception {
        BeanUtils.get(LotService.class).put(id, data, fieldName);
    }

    @Override
    public GetListOut<Lot> getList(GetLotListIn input) throws Exception {
        return BeanUtils.get(GetLotList.class).getList(input);
    }

    @Override
    public void saveList(@RequestBody List<Lot> list, @RequestParam(required = false) String[] fieldName)
                    throws Exception {
        BeanUtils.get(LotService.class).saveList(list, fieldName);
    }

    @Override
    public void deleteList(@RequestBody List<Lot> list) throws Exception {
        BeanUtils.get(LotService.class).deleteList(list);
    }

    @Override
    public CreateLotOut create(@PathVariable String id, @RequestBody CreateLotIn input) throws Exception {
        input.setLotId(id);
        return BeanUtils.get(CreateLot.class).create(input);
    }

    @Override
    public EndLotOut end(@PathVariable String id, @RequestBody EndLotIn input) throws Exception {
        input.setLotId(id);
        return BeanUtils.get(EndLot.class).end(input);
    }
    
    @Override
    public PackLotOut pack(@PathVariable String id, @RequestBody PackLotIn input) throws Exception {
        return BeanUtils.get(PackLot.class).pack(input);
    }
    
    @Override
    public RequestInspectionLotOut requestInspection(@PathVariable String id, @RequestBody RequestInspectionLotIn input) throws Exception {
        return BeanUtils.get(RequestInspectionLot.class).requestInspection(input);
    }
}
