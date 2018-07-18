package kr.co.miracom.mes.ras.resource.simple.equipment.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.mes.ras.resource.simple.equipment.model.Equipment;
import kr.co.miracom.mes.ras.resource.simple.equipment.model.EquipmentDetail;
import kr.co.miracom.mes.ras.resource.simple.equipment.model.EquipmentOper;
import kr.co.miracom.mes.ras.resource.simple.equipment.service.EquipmentOperService;
import kr.co.miracom.mes.ras.resource.simple.equipment.service.EquipmentService;
import kr.co.miracom.mes.ras.resource.simple.equipment.service.get_list.GetEquipmentList;
import kr.co.miracom.mes.ras.resource.simple.equipment.service.get_list.GetEquipmentListIn;

/**
 * Setup Controller: Equipment
 * @author myjung.jung
 * @since 2018. 06. 11.
 */
@CrossOrigin
@RestController
public class EquipmentControllerImpl implements EquipmentController {

    @Override
    public EquipmentDetail get(@PathVariable String id, SelectOptions options) throws Exception {
        return BeanUtils.get(EquipmentService.class).get(id, options);
    }

    @Override
    public void put(@PathVariable String id, @RequestBody EquipmentDetail data,
                    @RequestParam(required = false) String[] fieldName) throws Exception {
        BeanUtils.get(EquipmentService.class).put(id, data, fieldName);
    }

    @Override
    public GetListOut<Equipment> getList(GetEquipmentListIn input) throws Exception {
        return BeanUtils.get(GetEquipmentList.class).getList(input);
    }

    @Override
    public void saveList(@RequestBody List<Equipment> list, @RequestParam(required = false) String[] fieldName)
                    throws Exception {
        BeanUtils.get(EquipmentService.class).saveList(list, fieldName);
    }

    @Override
    public void deleteList(@RequestBody List<Equipment> list) throws Exception {
        BeanUtils.get(EquipmentService.class).deleteList(list);
    }

    @Override
    public GetListOut<EquipmentOper> getOperList(@PathVariable String id) throws Exception {
        return BeanUtils.get(EquipmentOperService.class).getList(id);
    }

    @Override
    public void saveOperList(@PathVariable String id, @RequestBody List<EquipmentOper> list,
                    @RequestParam(required = false) String[] fieldName) throws Exception {
        BeanUtils.get(EquipmentOperService.class).saveList(id, list, fieldName);
    }

}
