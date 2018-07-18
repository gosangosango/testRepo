package kr.co.miracom.mes.wip.resource.simple.material.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.mes.wip.resource.simple.material.model.Material;
import kr.co.miracom.mes.wip.resource.simple.material.model.MaterialCustomer;
import kr.co.miracom.mes.wip.resource.simple.material.model.MaterialDetail;
import kr.co.miracom.mes.wip.resource.simple.material.model.MaterialFlow;
import kr.co.miracom.mes.wip.resource.simple.material.model.MaterialVendor;
import kr.co.miracom.mes.wip.resource.simple.material.service.MaterialCustomerService;
import kr.co.miracom.mes.wip.resource.simple.material.service.MaterialFlowService;
import kr.co.miracom.mes.wip.resource.simple.material.service.MaterialService;
import kr.co.miracom.mes.wip.resource.simple.material.service.MaterialVendorService;
import kr.co.miracom.mes.wip.resource.simple.material.service.get_list.GetMaterialList;
import kr.co.miracom.mes.wip.resource.simple.material.service.get_list.GetMaterialListIn;

/**
 * Setup Controller: Material
 * @author myjung.jung
 * @since 2018. 06. 05.
 */
@CrossOrigin
@RestController
public class MaterialControllerImpl implements MaterialController {

    @Override
    public MaterialDetail get(@PathVariable String id, SelectOptions options) throws Exception {
        return BeanUtils.get(MaterialService.class).get(id, options);
    }

    @Override
    public void put(@PathVariable String id, @RequestBody MaterialDetail data,
                    @RequestParam(required = false) String[] fieldName) throws Exception {
        BeanUtils.get(MaterialService.class).put(id, data, fieldName);
    }

    @Override
    public GetListOut<Material> getList(GetMaterialListIn input) throws Exception {
        return BeanUtils.get(GetMaterialList.class).getList(input);
    }

    @Override
    public void saveList(@RequestBody List<Material> list, @RequestParam(required = false) String[] fieldName)
                    throws Exception {
        BeanUtils.get(MaterialService.class).saveList(list, fieldName);
    }

    @Override
    public void deleteList(@RequestBody List<Material> list) throws Exception {
        BeanUtils.get(MaterialService.class).deleteList(list);
    }

    @Override
    public GetListOut<MaterialVendor> getVendorList(@PathVariable String id) throws Exception {
        return BeanUtils.get(MaterialVendorService.class).getList(id);
    }

    @Override
    public void saveVendorList(@PathVariable String id, @RequestBody List<MaterialVendor> list,
                    @RequestParam(required = false) String[] fieldName) throws Exception {
        BeanUtils.get(MaterialVendorService.class).saveList(id, list, fieldName);
    }

    @Override
    public GetListOut<MaterialCustomer> getCustomerList(@PathVariable String id) throws Exception {
        return BeanUtils.get(MaterialCustomerService.class).getList(id);
    }

    @Override
    public void saveCustomerList(@PathVariable String id, @RequestBody List<MaterialCustomer> list,
                    @RequestParam(required = false) String[] fieldName) throws Exception {
        BeanUtils.get(MaterialCustomerService.class).saveList(id, list, fieldName);
    }

    @Override
    public GetListOut<MaterialFlow> getFlowList(@PathVariable String id) throws Exception {
        return BeanUtils.get(MaterialFlowService.class).getList(id);
    }

    @Override
    public void saveFlowList(@PathVariable String id, @RequestBody List<MaterialFlow> list,
                    @RequestParam(required = false) String[] fieldName) throws Exception {
        BeanUtils.get(MaterialFlowService.class).saveList(id, list, fieldName);
    }

}
