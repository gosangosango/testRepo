package kr.co.miracom.mes.bas.resource.simple.code_data.controller;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.mes.bas.resource.simple.code_data.model.CodeData;
import kr.co.miracom.mes.bas.resource.simple.code_data.model.CodeDataDetail;
import kr.co.miracom.mes.bas.resource.simple.code_data.service.get_list.GetCodeDataListIn;

/**
 * Setup Controller: CodeData
 * @author myjung.jung
 * @since 2018. 06. 05.
 */
public class CodeDataControllerStub implements CodeDataController {

    @Override
    public CodeDataDetail get(String id, SelectOptions options) throws Exception {
        return null;
    }

    @Override
    public void put(String id, CodeDataDetail data, String[] fieldName) throws Exception {

    }

    @Override
    public GetListOut<CodeData> getList(GetCodeDataListIn input) throws Exception {
        return null;
    }

    @Override
    public void saveList(List<CodeData> list, String[] fieldName) throws Exception {

    }

    @Override
    public void deleteList(List<CodeData> list) throws Exception {

    }

}
