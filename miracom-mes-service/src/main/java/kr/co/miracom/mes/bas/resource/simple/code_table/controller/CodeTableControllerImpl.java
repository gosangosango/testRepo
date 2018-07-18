package kr.co.miracom.mes.bas.resource.simple.code_table.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.mes.bas.resource.simple.code_table.model.CodeTable;
import kr.co.miracom.mes.bas.resource.simple.code_table.model.CodeTableDetail;
import kr.co.miracom.mes.bas.resource.simple.code_table.service.CodeTableService;
import kr.co.miracom.mes.bas.resource.simple.code_table.service.get_list.GetCodeTableList;
import kr.co.miracom.mes.bas.resource.simple.code_table.service.get_list.GetCodeTableListIn;

/**
 * Setup Controller: CodeTable
 * @author myjung.jung
 * @since 2018. 06. 05.
 */
@CrossOrigin
@RestController
public class CodeTableControllerImpl implements CodeTableController {

    @Override
    public CodeTableDetail get(@PathVariable String id, SelectOptions options) throws Exception {
        return BeanUtils.get(CodeTableService.class).get(id, options);
    }

    @Override
    public void put(@PathVariable String id, @RequestBody CodeTableDetail data,
                    @RequestParam(required = false) String[] fieldName) throws Exception {
        BeanUtils.get(CodeTableService.class).put(id, data, fieldName);
    }

    @Override
    public GetListOut<CodeTable> getList(GetCodeTableListIn input) throws Exception {
        return BeanUtils.get(GetCodeTableList.class).getList(input);
    }

    @Override
    public void saveList(@RequestBody List<CodeTable> list, @RequestParam(required = false) String[] fieldName)
                    throws Exception {
        BeanUtils.get(CodeTableService.class).saveList(list, fieldName);
    }

    @Override
    public void deleteList(@RequestBody List<CodeTable> list) throws Exception {
        BeanUtils.get(CodeTableService.class).deleteList(list);
    }

}
