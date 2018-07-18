package kr.co.miracom.mes.inv.resource.simple.inv_request.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.mes.inv.resource.simple.inv_request.model.InvRequest;
import kr.co.miracom.mes.inv.resource.simple.inv_request.model.InvRequestDetail;
import kr.co.miracom.mes.inv.resource.simple.inv_request.model.InvRequestMat;
import kr.co.miracom.mes.inv.resource.simple.inv_request.service.InvRequestMatService;
import kr.co.miracom.mes.inv.resource.simple.inv_request.service.InvRequestService;
import kr.co.miracom.mes.inv.resource.simple.inv_request.service.get_list.GetInvRequestList;
import kr.co.miracom.mes.inv.resource.simple.inv_request.service.get_list.GetInvRequestListIn;
import kr.co.miracom.mes.inv.resource.simple.inv_request.service.send.SendInvRequestInvLot;
import kr.co.miracom.mes.inv.resource.simple.inv_request.service.send.SendInvRequestInvLotIn;
import kr.co.miracom.mes.inv.resource.simple.inv_request.service.send.SendInvRequestInvLotOut;

/**
 * Setup Controller: InvRequest
 * @author User
 * @since 2018. 07. 03.
 */
@CrossOrigin
@RestController
public class InvRequestControllerImpl implements InvRequestController {

    @Override
    public InvRequestDetail get(@PathVariable String id, SelectOptions options) throws Exception {
        return BeanUtils.get(InvRequestService.class).get(id, options);
    }

    @Override
    public void put(@PathVariable String id, @RequestBody InvRequestDetail data, @RequestParam(required = false) String[] fieldName) throws Exception {
        BeanUtils.get(InvRequestService.class).put(id, data, fieldName);
    }

    @Override
    public GetListOut<InvRequest> getList(GetInvRequestListIn input) throws Exception {
        return BeanUtils.get(GetInvRequestList.class).getList(input);
    }

    @Override
    public void saveList(@RequestBody List<InvRequest> list, @RequestParam(required = false) String[] fieldName) throws Exception {
        BeanUtils.get(InvRequestService.class).saveList(list, fieldName);
    }

    @Override
    public void deleteList(@RequestBody List<InvRequest> list) throws Exception {
        BeanUtils.get(InvRequestService.class).deleteList(list);
    }

    @Override
    public GetListOut<InvRequestMat> getMatList(@PathVariable String id) throws Exception {
        return BeanUtils.get(InvRequestMatService.class).getList(id);
    }

    @Override
    public void saveMatList(@PathVariable String id, @RequestBody List<InvRequestMat> list, @RequestParam(required = false) String[] fieldName) throws Exception {
        BeanUtils.get(InvRequestMatService.class).saveList(id, list, fieldName);
    }

    @Override
    public SendInvRequestInvLotOut send(@PathVariable String id, @RequestBody SendInvRequestInvLotIn input) throws Exception {
        return BeanUtils.get(SendInvRequestInvLot.class).send(input);
    }
}
