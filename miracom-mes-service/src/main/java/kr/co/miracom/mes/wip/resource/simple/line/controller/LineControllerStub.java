package kr.co.miracom.mes.wip.resource.simple.line.controller;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.mes.wip.resource.simple.line.model.Line;
import kr.co.miracom.mes.wip.resource.simple.line.model.LineDetail;
import kr.co.miracom.mes.wip.resource.simple.line.service.get_list.GetLineListIn;

/**
 * Setup Controller: Line
 * @author myjung.jung
 * @since 2018. 06. 05.
 */
public class LineControllerStub implements LineController {

    @Override
    public LineDetail get(String id, SelectOptions options) throws Exception {
        return null;
    }

    @Override
    public void put(String id, LineDetail data, String[] fieldName) throws Exception {

    }

    @Override
    public GetListOut<Line> getList(GetLineListIn input) throws Exception {
        return null;
    }

    @Override
    public void saveList(List<Line> list, String[] fieldName) throws Exception {

    }

    @Override
    public void deleteList(List<Line> list) throws Exception {

    }

}
