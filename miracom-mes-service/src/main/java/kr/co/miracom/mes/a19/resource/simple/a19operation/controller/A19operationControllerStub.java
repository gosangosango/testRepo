package kr.co.miracom.mes.a19.resource.simple.a19operation.controller;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.mes.a19.resource.simple.a19operation.model.A19operation;
import kr.co.miracom.mes.a19.resource.simple.a19operation.model.A19operationDetail;
import kr.co.miracom.mes.a19.resource.simple.a19operation.service.get_list.GetA19operationListIn;

/**
 * Setup Controller: A19operation
 * @author Zotac023
 * @since 2018. 07. 17.
 */
public class A19operationControllerStub implements A19operationController {

    @Override
    public A19operationDetail get(String id, SelectOptions options) throws Exception {
        return null;
    }

    @Override
    public void put(String id, A19operationDetail data, String[] fieldName) throws Exception {

    }

    @Override
    public GetListOut<A19operation> getList(GetA19operationListIn input) throws Exception {
        return null;
    }

    @Override
    public void saveList(List<A19operation> list, String[] fieldName) throws Exception {

    }

    @Override
    public void deleteList(List<A19operation> list) throws Exception {

    }

}
