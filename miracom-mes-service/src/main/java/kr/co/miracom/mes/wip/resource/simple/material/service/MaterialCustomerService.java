package kr.co.miracom.mes.wip.resource.simple.material.service;

import java.util.List;

import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.service.RestService4OneToMany;
import kr.co.miracom.mes.wip.model.MWipMatCus;
import kr.co.miracom.mes.wip.model.MWipMatDef;
import kr.co.miracom.mes.wip.resource.simple.material.model.MaterialCustomer;

/**
 * Setup Service: Material
 * @author myjung.jung
 * @since 2018. 06. 05.
 */
public class MaterialCustomerService {
    private RestService4OneToMany<MWipMatDef, MWipMatCus, MaterialCustomer> restService = new RestService4OneToMany<>(
                    MWipMatDef.class, MWipMatCus.class, MaterialCustomer.class);

    public GetListOut<MaterialCustomer> getList(String id) throws Exception {
        return restService.getList(id);
    }

    public void saveList(String id, List<MaterialCustomer> list, String[] fieldName) throws Exception {
        restService.saveList(id, list, fieldName);
    }

}
