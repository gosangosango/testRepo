package kr.co.miracom.mes.a19.resource.simple.a19operation.controller;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.mes.a19.resource.simple.a19operation.model.A19operation;
import kr.co.miracom.mes.a19.resource.simple.a19operation.model.A19operationDetail;
import kr.co.miracom.mes.a19.resource.simple.a19operation.service.A19operationService;
import kr.co.miracom.mes.a19.resource.simple.a19operation.service.get_list.GetA19operationList;
import kr.co.miracom.mes.a19.resource.simple.a19operation.service.get_list.GetA19operationListIn;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Setup Controller: A19operation
 * @author Zotac023
 * @since 2018. 07. 17.
 */
@CrossOrigin
@RestController
public class A19operationControllerImpl implements A19operationController {

    @Override
    public A19operationDetail get(@PathVariable String id, SelectOptions options) throws Exception {
        return BeanUtils.get(A19operationService.class).get(id, options);
    }

    @Override
    public void put(@PathVariable String id, @RequestBody A19operationDetail data, @RequestParam(required = false) String[] fieldName) throws Exception {
        BeanUtils.get(A19operationService.class).put(id, data, fieldName);
    }

    @Override
    public GetListOut<A19operation> getList(GetA19operationListIn input) throws Exception {
        return BeanUtils.get(GetA19operationList.class).getList(input);
    }

    @Override
    public void saveList(@RequestBody List<A19operation> list, @RequestParam(required = false) String[] fieldName) throws Exception {
        BeanUtils.get(A19operationService.class).saveList(list, fieldName);
    }

    @Override
    public void deleteList(@RequestBody List<A19operation> list) throws Exception {
        BeanUtils.get(A19operationService.class).deleteList(list);
    }

}
