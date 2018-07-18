package kr.co.miracom.mes.wip.resource.simple.component.service;

import java.util.List;

import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.service.RestService4OneToMany;
import kr.co.miracom.mes.wip.model.MWipBomAlt;
import kr.co.miracom.mes.wip.model.MWipBomDef;
import kr.co.miracom.mes.wip.resource.simple.component.model.ComponentAlterMat;

/**
 * Setup Service: Component
 * @author myjung.jung
 * @since 2018. 07. 04.
 */
public class ComponentAlterMatService {
    private RestService4OneToMany<MWipBomDef, MWipBomAlt, ComponentAlterMat> restService = new RestService4OneToMany<>(
                    MWipBomDef.class, MWipBomAlt.class, ComponentAlterMat.class);

    public GetListOut<ComponentAlterMat> getList(String id) throws Exception {
        return restService.getList(id);
    }

    public void saveList(String id, List<ComponentAlterMat> list, String[] fieldName) throws Exception {
        restService.saveList(id, list, fieldName);
    }

}
