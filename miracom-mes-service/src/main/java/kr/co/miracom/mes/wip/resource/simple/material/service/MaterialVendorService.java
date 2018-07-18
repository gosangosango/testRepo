package kr.co.miracom.mes.wip.resource.simple.material.service;

import java.util.List;

import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.service.RestService4OneToMany;
import kr.co.miracom.mes.wip.model.MWipMatDef;
import kr.co.miracom.mes.wip.model.MWipMatVen;
import kr.co.miracom.mes.wip.resource.simple.material.model.MaterialVendor;

/**
 * Setup Service: Material
 * @author myjung.jung
 * @since 2018. 06. 05.
 */
public class MaterialVendorService {
    private RestService4OneToMany<MWipMatDef, MWipMatVen, MaterialVendor> restService = new RestService4OneToMany<>(
                    MWipMatDef.class, MWipMatVen.class, MaterialVendor.class);

    public GetListOut<MaterialVendor> getList(String id) throws Exception {
        return restService.getList(id);
    }

    public void saveList(String id, List<MaterialVendor> list, String[] fieldName) throws Exception {
        restService.saveList(id, list, fieldName);
    }

}
