package kr.co.miracom.mes.inv.resource.simple.inv_request.controller;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.mes.inv.resource.simple.inv_request.model.InvRequest;
import kr.co.miracom.mes.inv.resource.simple.inv_request.model.InvRequestDetail;
import kr.co.miracom.mes.inv.resource.simple.inv_request.model.InvRequestMat;
import kr.co.miracom.mes.inv.resource.simple.inv_request.service.get_list.GetInvRequestListIn;
import kr.co.miracom.mes.inv.resource.simple.inv_request.service.send.SendInvRequestInvLotIn;
import kr.co.miracom.mes.inv.resource.simple.inv_request.service.send.SendInvRequestInvLotOut;

/**
 * Setup Controller: InvRequest
 * @author User
 * @since 2018. 07. 03.
 */
public class InvRequestControllerStub implements InvRequestController {

    @Override
    public InvRequestDetail get(String id, SelectOptions options) throws Exception {
        return null;
    }

    @Override
    public void put(String id, InvRequestDetail data, String[] fieldName) throws Exception {

    }

    @Override
    public GetListOut<InvRequest> getList(GetInvRequestListIn input) throws Exception {
        return null;
    }

    @Override
    public void saveList(List<InvRequest> list, String[] fieldName) throws Exception {

    }

    @Override
    public void deleteList(List<InvRequest> list) throws Exception {

    }

    @Override
    public GetListOut<InvRequestMat> getMatList(String id) throws Exception {
        return null;
    }

    @Override
    public void saveMatList(String id, List<InvRequestMat> list, String[] fieldName) throws Exception {

    }

    @Override
    public SendInvRequestInvLotOut send(String id, SendInvRequestInvLotIn input) throws Exception {
     // TODO Auto-generated method stub
        return null;
    }
}
