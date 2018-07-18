package kr.co.miracom.mes.a19.resource.simple.a19flow.controller;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.mes.a19.resource.simple.a19flow.model.A19flow;
import kr.co.miracom.mes.a19.resource.simple.a19flow.model.A19flowA19oper;
import kr.co.miracom.mes.a19.resource.simple.a19flow.model.A19flowDetail;
import kr.co.miracom.mes.a19.resource.simple.a19flow.service.get_list.GetA19flowListIn;

/**
 * Setup Controller: A19flow
 * @author Zotac023
 * @since 2018. 07. 17.
 */
public class A19flowControllerStub implements A19flowController {

    @Override
    public A19flowDetail get(String id, SelectOptions options) throws Exception {
        return null;
    }

    @Override
    public void put(String id, A19flowDetail data, String[] fieldName) throws Exception {

    }

    @Override
    public GetListOut<A19flow> getList(GetA19flowListIn input) throws Exception {
        return null;
    }

    @Override
    public void saveList(List<A19flow> list, String[] fieldName) throws Exception {

    }

    @Override
    public void deleteList(List<A19flow> list) throws Exception {

    }

    @Override
    public GetListOut<A19flowA19oper> getA19operList(String id) throws Exception {
        return null;
    }

    @Override
    public void saveA19operList(String id, List<A19flowA19oper> list, String[] fieldName) throws Exception {

    }

}
