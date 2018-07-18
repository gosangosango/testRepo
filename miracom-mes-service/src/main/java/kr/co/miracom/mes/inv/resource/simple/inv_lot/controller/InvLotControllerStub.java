package kr.co.miracom.mes.inv.resource.simple.inv_lot.controller;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.model.InvLot;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.model.InvLotDetail;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.service.get_list.GetInvLotListIn;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.service.input.InputInvLotIn;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.service.input.InputInvLotOut;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.service.instore.InstoreInvLotIn;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.service.instore.InstoreInvLotOut;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.service.unload.UnloadInvLotIn;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.service.unload.UnloadInvLotOut;

/**
 * Setup Controller: InvLot
 * @author myjung.jung
 * @since 2018. 06. 21.
 */
public class InvLotControllerStub implements InvLotController {

    @Override
    public InvLotDetail get(String id, SelectOptions options) throws Exception {
        return null;
    }

    // @Override
    // public void put(String id, InvLotDetail data, String[] fieldName) throws Exception {
    //
    // }

    @Override
    public GetListOut<InvLot> getList(GetInvLotListIn input) throws Exception {
        return null;
    }

    // @Override
    // public void saveList(List<InvLot> list, String[] fieldName) throws Exception {
    //
    // }

    // @Override
    // public void deleteList(List<InvLot> list) throws Exception {
    //
    // }

    @Override
    public InstoreInvLotOut instore(String id, InstoreInvLotIn input) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public InputInvLotOut input(String id, InputInvLotIn input) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UnloadInvLotOut unload(String id, UnloadInvLotIn input) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }
    
}
