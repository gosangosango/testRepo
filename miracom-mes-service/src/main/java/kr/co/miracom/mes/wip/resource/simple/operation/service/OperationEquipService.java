package kr.co.miracom.mes.wip.resource.simple.operation.service;

import java.util.List;

import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.service.RestService4OneToMany;
import kr.co.miracom.mes.ras.model.MRasEqpOpr;
import kr.co.miracom.mes.wip.model.MWipOprDef;
import kr.co.miracom.mes.wip.resource.simple.operation.model.OperationEquip;

/**
 * Setup Service: Operation
 * @author myjung.jung
 * @since 2018. 06. 11.
 */
public class OperationEquipService {
    private RestService4OneToMany<MWipOprDef, MRasEqpOpr, OperationEquip> restService = new RestService4OneToMany<>(
                    MWipOprDef.class, MRasEqpOpr.class, OperationEquip.class);

    public GetListOut<OperationEquip> getList(String id) throws Exception {
        return restService.getList(id);
    }

    public void saveList(String id, List<OperationEquip> list, String[] fieldName) throws Exception {
        restService.saveList(id, list, fieldName);
    }

}
