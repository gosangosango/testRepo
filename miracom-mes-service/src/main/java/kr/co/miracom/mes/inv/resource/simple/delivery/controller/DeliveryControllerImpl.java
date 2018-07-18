package kr.co.miracom.mes.inv.resource.simple.delivery.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.mes.inv.resource.simple.delivery.model.Delivery;
import kr.co.miracom.mes.inv.resource.simple.delivery.model.DeliveryDetail;
import kr.co.miracom.mes.inv.resource.simple.delivery.model.DeliveryDlvDetail;
import kr.co.miracom.mes.inv.resource.simple.delivery.service.DeliveryDlvDetailService;
import kr.co.miracom.mes.inv.resource.simple.delivery.service.DeliveryService;
import kr.co.miracom.mes.inv.resource.simple.delivery.service.get_list.GetDeliveryList;
import kr.co.miracom.mes.inv.resource.simple.delivery.service.get_list.GetDeliveryListIn;
import kr.co.miracom.mes.inv.resource.simple.delivery.service.inspect_list.InspectDeliveryList;
import kr.co.miracom.mes.inv.resource.simple.delivery.service.inspect_list.InspectDeliveryListIn;
import kr.co.miracom.mes.inv.resource.simple.delivery.service.inspect_list.InspectDeliveryListOut;

/**
 * Setup Controller: Delivery
 * @author myjung.jung
 * @since 2018. 06. 25.
 */
@CrossOrigin
@RestController
public class DeliveryControllerImpl implements DeliveryController {

    @Override
    public DeliveryDetail get(@PathVariable String id, SelectOptions options) throws Exception {
        return BeanUtils.get(DeliveryService.class).get(id, options);
    }

    @Override
    public void put(@PathVariable String id, @RequestBody DeliveryDetail data,
                    @RequestParam(required = false) String[] fieldName) throws Exception {
        BeanUtils.get(DeliveryService.class).put(id, data, fieldName);
    }

    @Override
    public GetListOut<Delivery> getList(GetDeliveryListIn input) throws Exception {
        return BeanUtils.get(GetDeliveryList.class).getList(input);
    }

    @Override
    public void saveList(@RequestBody List<Delivery> list, @RequestParam(required = false) String[] fieldName)
                    throws Exception {
        BeanUtils.get(DeliveryService.class).saveList(list, fieldName);
    }

    @Override
    public void deleteList(@RequestBody List<Delivery> list) throws Exception {
        BeanUtils.get(DeliveryService.class).deleteList(list);
    }

    @Override
    public InspectDeliveryListOut inspectList(@RequestBody InspectDeliveryListIn input) throws Exception {
        return BeanUtils.get(InspectDeliveryList.class).inspect(input);
    }

    @Override
    public GetListOut<DeliveryDlvDetail> getDlvDetailList(@PathVariable String id) throws Exception {
        return BeanUtils.get(DeliveryDlvDetailService.class).getList(id);
    }

    @Override
    public void saveDlvDetailList(@PathVariable String id, @RequestBody List<DeliveryDlvDetail> list,
                    @RequestParam(required = false) String[] fieldName) throws Exception {
        BeanUtils.get(DeliveryDlvDetailService.class).saveList(id, list, fieldName);
    }

}
