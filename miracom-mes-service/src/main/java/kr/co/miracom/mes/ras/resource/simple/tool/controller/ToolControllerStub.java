package kr.co.miracom.mes.ras.resource.simple.tool.controller;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.mes.ras.resource.simple.tool.model.Tool;
import kr.co.miracom.mes.ras.resource.simple.tool.model.ToolDetail;
import kr.co.miracom.mes.ras.resource.simple.tool.service.get_list.GetToolListIn;

/**
 * Setup Controller: Tool
 * @author hhk
 * @since 2018. 07. 03.
 */
public class ToolControllerStub implements ToolController {

    @Override
    public ToolDetail get(String id, SelectOptions options) throws Exception {
        return null;
    }

    @Override
    public void put(String id, ToolDetail data, String[] fieldName) throws Exception {

    }

    @Override
    public GetListOut<Tool> getList(GetToolListIn input) throws Exception {
        return null;
    }

    @Override
    public void saveList(List<Tool> list, String[] fieldName) throws Exception {

    }

    @Override
    public void deleteList(List<Tool> list) throws Exception {

    }

}
