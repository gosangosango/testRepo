package kr.co.miracom.mes.ras.resource.simple.equipment.service;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.RestService;
import kr.co.miracom.mes.ras.model.MRasEqpDef;
import kr.co.miracom.mes.ras.resource.simple.equipment.model.Equipment;
import kr.co.miracom.mes.ras.resource.simple.equipment.model.EquipmentDetail;

/**
 * Setup Service: Equipment
 * @author myjung.jung
 * @since 2018. 06. 11.
 */
public class EquipmentService {
    private RestService<MRasEqpDef, Equipment, EquipmentDetail> restService = new RestService<>(MRasEqpDef.class,
                    Equipment.class, EquipmentDetail.class);

    public EquipmentDetail get(String id, SelectOptions options) throws Exception {
        return restService.get(id, options);
    }

    public void post(String id, EquipmentDetail data, String[] fieldName) throws Exception {
        restService.post(id, data, fieldName);
    }

    public void put(String id, EquipmentDetail data, String[] fieldName) throws Exception {
        restService.put(id, data, fieldName);
    }

    public void delete(String id, EquipmentDetail data) throws Exception {
        restService.delete(id, data);
    }

    public void postList(List<Equipment> list, String[] fieldName) throws Exception {
        restService.postList(list, fieldName);
    }

    public void putList(List<Equipment> list, String[] fieldName) throws Exception {
        restService.putList(list, fieldName);
    }

    public void saveList(List<Equipment> list, String[] fieldName) throws Exception {
        restService.saveList(list, fieldName);
    }

    public void deleteList(List<Equipment> list) throws Exception {
        restService.deleteList(list);
    }

}
