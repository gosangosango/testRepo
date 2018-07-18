package kr.co.miracom.mes.wip.resource.simple.material.service;

import java.util.List;

import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.service.RestService4OneToMany;
import kr.co.miracom.mes.wip.model.MWipMatDef;
import kr.co.miracom.mes.wip.model.MWipMatFlw;
import kr.co.miracom.mes.wip.resource.simple.material.model.MaterialFlow;

/**
 * Setup Service: Material
 * @author myjung.jung
 * @since 2018. 06. 05.
 */
public class MaterialFlowService {
    private RestService4OneToMany<MWipMatDef, MWipMatFlw, MaterialFlow> restService = new RestService4OneToMany<>(
                    MWipMatDef.class, MWipMatFlw.class, MaterialFlow.class);

    public GetListOut<MaterialFlow> getList(String id) throws Exception {
        Query query = new Query();
        query.addFilter("lineCode", "NONE");
        return restService.getList(id, query);
    }

    public void saveList(String id, List<MaterialFlow> list, String[] fieldName) throws Exception {
        Query query = new Query();
        query.addFilter("lineCode", "NONE");
        restService.saveList(id, query, list, fieldName);
    }

}
