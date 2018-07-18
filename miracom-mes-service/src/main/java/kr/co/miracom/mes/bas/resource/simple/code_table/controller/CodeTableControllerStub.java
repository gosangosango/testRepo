package kr.co.miracom.mes.bas.resource.simple.code_table.controller;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.mes.bas.resource.simple.code_table.model.CodeTable;
import kr.co.miracom.mes.bas.resource.simple.code_table.model.CodeTableDetail;
import kr.co.miracom.mes.bas.resource.simple.code_table.service.get_list.GetCodeTableListIn;

/**
 * Setup Controller: CodeTable
 * @author myjung.jung
 * @since 2018. 06. 05.
 */
public class CodeTableControllerStub implements CodeTableController {

    @Override
    public CodeTableDetail get(String id, SelectOptions options) throws Exception {
        return null;
    }

    @Override
    public void put(String id, CodeTableDetail data, String[] fieldName) throws Exception {

    }

    @Override
    public GetListOut<CodeTable> getList(GetCodeTableListIn input) throws Exception {
        return null;
    }

    @Override
    public void saveList(List<CodeTable> list, String[] fieldName) throws Exception {

    }

    @Override
    public void deleteList(List<CodeTable> list) throws Exception {

    }

}
