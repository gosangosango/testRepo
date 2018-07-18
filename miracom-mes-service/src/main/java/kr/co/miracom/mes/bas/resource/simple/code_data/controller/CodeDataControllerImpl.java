package kr.co.miracom.mes.bas.resource.simple.code_data.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.mes.bas.resource.simple.code_data.model.CodeData;
import kr.co.miracom.mes.bas.resource.simple.code_data.model.CodeDataDetail;
import kr.co.miracom.mes.bas.resource.simple.code_data.service.CodeDataService;
import kr.co.miracom.mes.bas.resource.simple.code_data.service.get_list.GetCodeDataList;
import kr.co.miracom.mes.bas.resource.simple.code_data.service.get_list.GetCodeDataListIn;

/**
 * Setup Controller: CodeData
 * @author myjung.jung
 * @since 2018. 06. 05.
 */
@CrossOrigin
@RestController
public class CodeDataControllerImpl implements CodeDataController {

    @Override
    public CodeDataDetail get(@PathVariable String id, SelectOptions options) throws Exception {
        return BeanUtils.get(CodeDataService.class).get(id, options);
    }

    @Override
    public void put(@PathVariable String id, @RequestBody CodeDataDetail data,
                    @RequestParam(required = false) String[] fieldName) throws Exception {
        BeanUtils.get(CodeDataService.class).put(id, data, fieldName);
    }

    @Override
    public GetListOut<CodeData> getList(GetCodeDataListIn input) throws Exception {
        return BeanUtils.get(GetCodeDataList.class).getList(input);
    }

    @Override
    public void saveList(@RequestBody List<CodeData> list, @RequestParam(required = false) String[] fieldName)
                    throws Exception {
        BeanUtils.get(CodeDataService.class).saveList(list, fieldName);
    }

    @Override
    public void deleteList(@RequestBody List<CodeData> list) throws Exception {
        BeanUtils.get(CodeDataService.class).deleteList(list);
    }

}
