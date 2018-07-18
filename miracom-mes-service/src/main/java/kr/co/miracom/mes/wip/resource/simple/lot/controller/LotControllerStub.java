package kr.co.miracom.mes.wip.resource.simple.lot.controller;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.mes.wip.resource.simple.lot.model.Lot;
import kr.co.miracom.mes.wip.resource.simple.lot.model.LotDetail;
import kr.co.miracom.mes.wip.resource.simple.lot.service.create.CreateLotIn;
import kr.co.miracom.mes.wip.resource.simple.lot.service.create.CreateLotOut;
import kr.co.miracom.mes.wip.resource.simple.lot.service.end.EndLotIn;
import kr.co.miracom.mes.wip.resource.simple.lot.service.end.EndLotOut;
import kr.co.miracom.mes.wip.resource.simple.lot.service.get_list.GetLotListIn;
import kr.co.miracom.mes.wip.resource.simple.lot.service.pack.PackLotIn;
import kr.co.miracom.mes.wip.resource.simple.lot.service.pack.PackLotOut;
import kr.co.miracom.mes.wip.resource.simple.lot.service.request_inspection.RequestInspectionLotIn;
import kr.co.miracom.mes.wip.resource.simple.lot.service.request_inspection.RequestInspectionLotOut;

/**
 * Setup Controller: Lot
 * @author gom
 * @since 2018. 06. 21.
 */
public class LotControllerStub implements LotController {

    @Override
    public LotDetail get(String id, SelectOptions options) throws Exception {
        return null;
    }

    @Override
    public void put(String id, LotDetail data, String[] fieldName) throws Exception {

    }

    @Override
    public GetListOut<Lot> getList(GetLotListIn input) throws Exception {
        return null;
    }

    @Override
    public void saveList(List<Lot> list, String[] fieldName) throws Exception {

    }

    @Override
    public void deleteList(List<Lot> list) throws Exception {

    }

    @Override
    public CreateLotOut create(String id, CreateLotIn input) throws Exception {
        return null;
    }

    @Override
    public EndLotOut end(String id, EndLotIn input) throws Exception {
        return null;
    }

    @Override
    public PackLotOut pack(String id, PackLotIn input) throws Exception {
        return null;
    }
    
    @Override
    public RequestInspectionLotOut requestInspection(String id, RequestInspectionLotIn input) throws Exception {
        return null;
    }
}
