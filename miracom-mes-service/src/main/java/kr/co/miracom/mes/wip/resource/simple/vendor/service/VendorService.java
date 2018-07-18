package kr.co.miracom.mes.wip.resource.simple.vendor.service;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.RestService;
import kr.co.miracom.mes.wip.model.MWipVenDef;
import kr.co.miracom.mes.wip.resource.simple.vendor.model.Vendor;
import kr.co.miracom.mes.wip.resource.simple.vendor.model.VendorDetail;

/**
 * Setup Service: Vendor
 * @author myjung.jung
 * @since 2018. 06. 14.
 */
public class VendorService {
    private RestService<MWipVenDef, Vendor, VendorDetail> restService = new RestService<>(MWipVenDef.class,
                    Vendor.class, VendorDetail.class);

    public VendorDetail get(String id, SelectOptions options) throws Exception {
        return restService.get(id, options);
    }

    public void post(String id, VendorDetail data, String[] fieldName) throws Exception {
        restService.post(id, data, fieldName);
    }

    public void put(String id, VendorDetail data, String[] fieldName) throws Exception {
        restService.put(id, data, fieldName);
    }

    public void delete(String id, VendorDetail data) throws Exception {
        restService.delete(id, data);
    }

    public void postList(List<Vendor> list, String[] fieldName) throws Exception {
        restService.postList(list, fieldName);
    }

    public void putList(List<Vendor> list, String[] fieldName) throws Exception {
        restService.putList(list, fieldName);
    }

    public void saveList(List<Vendor> list, String[] fieldName) throws Exception {
        restService.saveList(list, fieldName);
    }

    public void deleteList(List<Vendor> list) throws Exception {
        restService.deleteList(list);
    }

}
