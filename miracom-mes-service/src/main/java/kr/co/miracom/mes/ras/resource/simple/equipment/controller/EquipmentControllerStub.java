package kr.co.miracom.mes.ras.resource.simple.equipment.controller;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.mes.ras.resource.simple.equipment.model.Equipment;
import kr.co.miracom.mes.ras.resource.simple.equipment.model.EquipmentDetail;
import kr.co.miracom.mes.ras.resource.simple.equipment.model.EquipmentOper;
import kr.co.miracom.mes.ras.resource.simple.equipment.service.get_list.GetEquipmentListIn;

/**
 * Setup Controller: Equipment
 * @author myjung.jung
 * @since 2018. 06. 11.
 */
public class EquipmentControllerStub implements EquipmentController {

    @Override
    public EquipmentDetail get(String id, SelectOptions options) throws Exception {
        return null;
    }

    @Override
    public void put(String id, EquipmentDetail data, String[] fieldName) throws Exception {

    }

    @Override
    public GetListOut<Equipment> getList(GetEquipmentListIn input) throws Exception {
        return null;
    }

    @Override
    public void saveList(List<Equipment> list, String[] fieldName) throws Exception {

    }

    @Override
    public void deleteList(List<Equipment> list) throws Exception {

    }

    @Override
    public GetListOut<EquipmentOper> getOperList(String id) throws Exception {
        return null;
    }

    @Override
    public void saveOperList(String id, List<EquipmentOper> list, String[] fieldName) throws Exception {

    }

}
