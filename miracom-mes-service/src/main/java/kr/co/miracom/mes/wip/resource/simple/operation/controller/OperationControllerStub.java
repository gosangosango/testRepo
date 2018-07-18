package kr.co.miracom.mes.wip.resource.simple.operation.controller;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.mes.wip.resource.simple.operation.model.Operation;
import kr.co.miracom.mes.wip.resource.simple.operation.model.OperationDetail;
import kr.co.miracom.mes.wip.resource.simple.operation.model.OperationEquip;
import kr.co.miracom.mes.wip.resource.simple.operation.service.get_list.GetOperationListIn;

/**
 * Setup Controller: Operation
 * @author myjung.jung
 * @since 2018. 06. 05.
 */
public class OperationControllerStub implements OperationController {

    @Override
    public OperationDetail get(String id, SelectOptions options) throws Exception {
        return null;
    }

    @Override
    public void put(String id, OperationDetail data, String[] fieldName) throws Exception {

    }

    @Override
    public GetListOut<Operation> getList(GetOperationListIn input) throws Exception {
        return null;
    }

    @Override
    public void saveList(List<Operation> list, String[] fieldName) throws Exception {

    }

    @Override
    public void deleteList(List<Operation> list) throws Exception {

    }

    @Override
    public GetListOut<OperationEquip> getEquipList(String id) throws Exception {
        return null;
    }

    @Override
    public void saveEquipList(String id, List<OperationEquip> list, String[] fieldName) throws Exception {

    }

}
