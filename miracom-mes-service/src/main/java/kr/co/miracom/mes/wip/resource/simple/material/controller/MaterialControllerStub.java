package kr.co.miracom.mes.wip.resource.simple.material.controller;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.mes.wip.resource.simple.material.model.Material;
import kr.co.miracom.mes.wip.resource.simple.material.model.MaterialCustomer;
import kr.co.miracom.mes.wip.resource.simple.material.model.MaterialDetail;
import kr.co.miracom.mes.wip.resource.simple.material.model.MaterialFlow;
import kr.co.miracom.mes.wip.resource.simple.material.model.MaterialVendor;
import kr.co.miracom.mes.wip.resource.simple.material.service.get_list.GetMaterialListIn;

/**
 * Setup Controller: Material
 * @author myjung.jung
 * @since 2018. 06. 05.
 */
public class MaterialControllerStub implements MaterialController {

    @Override
    public MaterialDetail get(String id, SelectOptions options) throws Exception {
        return null;
    }

    @Override
    public void put(String id, MaterialDetail data, String[] fieldName) throws Exception {

    }

    @Override
    public GetListOut<Material> getList(GetMaterialListIn input) throws Exception {
        return null;
    }

    @Override
    public void saveList(List<Material> list, String[] fieldName) throws Exception {

    }

    @Override
    public void deleteList(List<Material> list) throws Exception {

    }

    @Override
    public GetListOut<MaterialVendor> getVendorList(String id) throws Exception {
        return null;
    }

    @Override
    public void saveVendorList(String id, List<MaterialVendor> list, String[] fieldName) throws Exception {

    }

    @Override
    public GetListOut<MaterialCustomer> getCustomerList(String id) throws Exception {
        return null;
    }

    @Override
    public void saveCustomerList(String id, List<MaterialCustomer> list, String[] fieldName) throws Exception {

    }

    @Override
    public GetListOut<MaterialFlow> getFlowList(String id) throws Exception {
        return null;
    }

    @Override
    public void saveFlowList(String id, List<MaterialFlow> list, String[] fieldName) throws Exception {

    }

}
