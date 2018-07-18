package kr.co.miracom.mes.wip.resource.simple.vendor.controller;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.mes.wip.resource.simple.vendor.model.Vendor;
import kr.co.miracom.mes.wip.resource.simple.vendor.model.VendorDetail;
import kr.co.miracom.mes.wip.resource.simple.vendor.service.get_list.GetVendorListIn;

/**
 * Setup Controller: Vendor
 * @author myjung.jung
 * @since 2018. 06. 14.
 */
public class VendorControllerStub implements VendorController {

    @Override
    public VendorDetail get(String id, SelectOptions options) throws Exception {
        return null;
    }

    @Override
    public void put(String id, VendorDetail data, String[] fieldName) throws Exception {

    }

    @Override
    public GetListOut<Vendor> getList(GetVendorListIn input) throws Exception {
        return null;
    }

    @Override
    public void saveList(List<Vendor> list, String[] fieldName) throws Exception {

    }

    @Override
    public void deleteList(List<Vendor> list) throws Exception {

    }

}
