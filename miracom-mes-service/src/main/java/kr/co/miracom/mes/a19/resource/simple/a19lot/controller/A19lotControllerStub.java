package kr.co.miracom.mes.a19.resource.simple.a19lot.controller;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.mes.a19.resource.simple.a19lot.model.A19lot;
import kr.co.miracom.mes.a19.resource.simple.a19lot.model.A19lotDetail;
import kr.co.miracom.mes.a19.resource.simple.a19lot.service.get_list.GetA19lotListIn;

/**
 * Setup Controller: A19lot
 * @author Zotac023
 * @since 2018. 07. 18.
 */
public class A19lotControllerStub implements A19lotController {

    @Override
    public A19lotDetail get(String id, SelectOptions options) throws Exception {
        return null;
    }

    @Override
    public void put(String id, A19lotDetail data, String[] fieldName) throws Exception {

    }

    @Override
    public GetListOut<A19lot> getList(GetA19lotListIn input) throws Exception {
        return null;
    }

    @Override
    public void saveList(List<A19lot> list, String[] fieldName) throws Exception {

    }

    @Override
    public void deleteList(List<A19lot> list) throws Exception {

    }

}
