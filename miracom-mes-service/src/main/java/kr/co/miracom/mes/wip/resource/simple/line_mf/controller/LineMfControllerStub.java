package kr.co.miracom.mes.wip.resource.simple.line_mf.controller;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.mes.wip.resource.simple.line_mf.model.LineMf;
import kr.co.miracom.mes.wip.resource.simple.line_mf.model.LineMfDetail;
import kr.co.miracom.mes.wip.resource.simple.line_mf.service.get_list.GetLineMfListIn;

/**
 * Setup Controller: LineMf
 * @author mo21.kim
 * @since 2018. 07. 03.
 */
public class LineMfControllerStub implements LineMfController {

    @Override
    public LineMfDetail get(String id, SelectOptions options) throws Exception {
        return null;
    }

    @Override
    public void put(String id, LineMfDetail data, String[] fieldName) throws Exception {

    }

    @Override
    public GetListOut<LineMf> getList(GetLineMfListIn input) throws Exception {
        return null;
    }

    @Override
    public void saveList(List<LineMf> list, String[] fieldName) throws Exception {

    }

    @Override
    public void deleteList(List<LineMf> list) throws Exception {

    }

}
