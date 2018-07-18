package kr.co.miracom.mes.wip.resource.simple.component.service;

import java.util.List;

import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.service.RestService4OneToMany;
import kr.co.miracom.mes.wip.model.MWipBomDef;
import kr.co.miracom.mes.wip.model.MWipBomOpr;
import kr.co.miracom.mes.wip.resource.simple.component.model.ComponentOper;

/**
 * Setup Service: Component
 * @author myjung.jung
 * @since 2018. 06. 26.
 */
public class ComponentOperService {
    private RestService4OneToMany<MWipBomDef, MWipBomOpr, ComponentOper> restService = new RestService4OneToMany<>(
                    MWipBomDef.class, MWipBomOpr.class, ComponentOper.class);

    public GetListOut<ComponentOper> getList(String id) throws Exception {
        return restService.getList(id);
    }

    public void saveList(String id, List<ComponentOper> list, String[] fieldName) throws Exception {
        restService.saveList(id, list, fieldName);
    }

}
