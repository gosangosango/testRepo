package kr.co.miracom.mes.wip.resource.simple.vendor.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.mes.wip.resource.simple.vendor.model.Vendor;
import kr.co.miracom.mes.wip.resource.simple.vendor.model.VendorDetail;
import kr.co.miracom.mes.wip.resource.simple.vendor.service.VendorService;
import kr.co.miracom.mes.wip.resource.simple.vendor.service.get_list.GetVendorList;
import kr.co.miracom.mes.wip.resource.simple.vendor.service.get_list.GetVendorListIn;

/**
 * Setup Controller: Vendor
 * @author myjung.jung
 * @since 2018. 06. 14.
 */
@CrossOrigin
@RestController
public class VendorControllerImpl implements VendorController {

    @Override
    public VendorDetail get(@PathVariable String id, SelectOptions options) throws Exception {
        return BeanUtils.get(VendorService.class).get(id, options);
    }

    @Override
    public void put(@PathVariable String id, @RequestBody VendorDetail data,
                    @RequestParam(required = false) String[] fieldName) throws Exception {
        BeanUtils.get(VendorService.class).put(id, data, fieldName);
    }

    @Override
    public GetListOut<Vendor> getList(GetVendorListIn input) throws Exception {
        return BeanUtils.get(GetVendorList.class).getList(input);
    }

    @Override
    public void saveList(@RequestBody List<Vendor> list, @RequestParam(required = false) String[] fieldName)
                    throws Exception {
        BeanUtils.get(VendorService.class).saveList(list, fieldName);
    }

    @Override
    public void deleteList(@RequestBody List<Vendor> list) throws Exception {
        BeanUtils.get(VendorService.class).deleteList(list);
    }

}
