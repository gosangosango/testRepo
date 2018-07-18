package kr.co.miracom.mes.wip.resource.simple.operation.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.mes.wip.resource.simple.operation.model.Operation;
import kr.co.miracom.mes.wip.resource.simple.operation.model.OperationDetail;
import kr.co.miracom.mes.wip.resource.simple.operation.model.OperationEquip;
import kr.co.miracom.mes.wip.resource.simple.operation.service.OperationEquipService;
import kr.co.miracom.mes.wip.resource.simple.operation.service.OperationService;
import kr.co.miracom.mes.wip.resource.simple.operation.service.get_list.GetOperationList;
import kr.co.miracom.mes.wip.resource.simple.operation.service.get_list.GetOperationListIn;

/**
 * Setup Controller: Operation
 * @author myjung.jung
 * @since 2018. 06. 05.
 */
@CrossOrigin
@RestController
public class OperationControllerImpl implements OperationController {

    @Override
    public OperationDetail get(@PathVariable String id, SelectOptions options) throws Exception {
        return BeanUtils.get(OperationService.class).get(id, options);
    }

    @Override
    public void put(@PathVariable String id, @RequestBody OperationDetail data,
                    @RequestParam(required = false) String[] fieldName) throws Exception {
        BeanUtils.get(OperationService.class).put(id, data, fieldName);
    }

    @Override
    public GetListOut<Operation> getList(GetOperationListIn input) throws Exception {
        return BeanUtils.get(GetOperationList.class).getList(input);
    }

    @Override
    public void saveList(@RequestBody List<Operation> list, @RequestParam(required = false) String[] fieldName)
                    throws Exception {
        BeanUtils.get(OperationService.class).saveList(list, fieldName);
    }

    @Override
    public void deleteList(@RequestBody List<Operation> list) throws Exception {
        BeanUtils.get(OperationService.class).deleteList(list);
    }

    @Override
    public GetListOut<OperationEquip> getEquipList(@PathVariable String id) throws Exception {
        return BeanUtils.get(OperationEquipService.class).getList(id);
    }

    @Override
    public void saveEquipList(@PathVariable String id, @RequestBody List<OperationEquip> list,
                    @RequestParam(required = false) String[] fieldName) throws Exception {
        BeanUtils.get(OperationEquipService.class).saveList(id, list, fieldName);
    }

}
