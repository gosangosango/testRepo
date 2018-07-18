package kr.co.miracom.mes.ras.resource.simple.equipment.service;

import java.util.List;

import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.service.RestService4OneToMany;
import kr.co.miracom.mes.ras.model.MRasEqpDef;
import kr.co.miracom.mes.ras.model.MRasEqpOpr;
import kr.co.miracom.mes.ras.resource.simple.equipment.model.EquipmentOper;

/**
 * Setup Service: Equipment
 * @author myjung.jung
 * @since 2018. 06. 11.
 */
public class EquipmentOperService {
    private RestService4OneToMany<MRasEqpDef, MRasEqpOpr, EquipmentOper> restService = new RestService4OneToMany<>(
                    MRasEqpDef.class, MRasEqpOpr.class, EquipmentOper.class);

    public GetListOut<EquipmentOper> getList(String id) throws Exception {
        return restService.getList(id);
    }

    public void saveList(String id, List<EquipmentOper> list, String[] fieldName) throws Exception {
        restService.saveList(id, list, fieldName);
    }

}
