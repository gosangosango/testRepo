package kr.co.miracom.mes.inv.resource.simple.ret_mat.controller;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.mes.inv.resource.simple.ret_mat.model.RetMat;
import kr.co.miracom.mes.inv.resource.simple.ret_mat.model.RetMatDetail;
import kr.co.miracom.mes.inv.resource.simple.ret_mat.service.get_list.GetRetMatListIn;
import kr.co.miracom.mes.inv.resource.simple.ret_mat.service.ret_list.RetMatListIn;
import kr.co.miracom.mes.inv.resource.simple.ret_mat.service.ret_list.RetMatListOut;

/**
 * Setup Controller: RetMat
 * @author mo21.kim
 * @since 2018. 07. 06.
 */
public class RetMatControllerStub implements RetMatController {

    @Override
    public RetMatDetail get(String id, SelectOptions options) throws Exception {
        return null;
    }

    @Override
    public void put(String id, RetMatDetail data, String[] fieldName) throws Exception {

    }

    @Override
    public GetListOut<RetMat> getList(GetRetMatListIn input) throws Exception {
        return null;
    }

    @Override
    public RetMatListOut retMatList(RetMatListIn list) throws Exception {
        return null;
    }

    @Override
    public void deleteList(List<RetMat> list) throws Exception {

    }

}
