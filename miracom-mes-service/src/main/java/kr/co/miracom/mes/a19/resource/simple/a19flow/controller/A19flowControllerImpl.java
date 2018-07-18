package kr.co.miracom.mes.a19.resource.simple.a19flow.controller;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.mes.a19.resource.simple.a19flow.model.A19flow;
import kr.co.miracom.mes.a19.resource.simple.a19flow.model.A19flowA19oper;
import kr.co.miracom.mes.a19.resource.simple.a19flow.model.A19flowDetail;
import kr.co.miracom.mes.a19.resource.simple.a19flow.service.A19flowA19operService;
import kr.co.miracom.mes.a19.resource.simple.a19flow.service.A19flowService;
import kr.co.miracom.mes.a19.resource.simple.a19flow.service.get_list.GetA19flowList;
import kr.co.miracom.mes.a19.resource.simple.a19flow.service.get_list.GetA19flowListIn;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Setup Controller: A19flow
 * @author Zotac023
 * @since 2018. 07. 17.
 */
@CrossOrigin
@RestController
public class A19flowControllerImpl implements A19flowController {

    @Override
    public A19flowDetail get(@PathVariable String id, SelectOptions options) throws Exception {
        return BeanUtils.get(A19flowService.class).get(id, options);
    }

    @Override
    public void put(@PathVariable String id, @RequestBody A19flowDetail data, @RequestParam(required = false) String[] fieldName) throws Exception {
        BeanUtils.get(A19flowService.class).put(id, data, fieldName);
    }

    @Override
    public GetListOut<A19flow> getList(GetA19flowListIn input) throws Exception {
        return BeanUtils.get(GetA19flowList.class).getList(input);
    }

    @Override
    public void saveList(@RequestBody List<A19flow> list, @RequestParam(required = false) String[] fieldName) throws Exception {
        BeanUtils.get(A19flowService.class).saveList(list, fieldName);
    }

    @Override
    public void deleteList(@RequestBody List<A19flow> list) throws Exception {
        BeanUtils.get(A19flowService.class).deleteList(list);
    }

    @Override
    public GetListOut<A19flowA19oper> getA19operList(@PathVariable String id) throws Exception {
        return BeanUtils.get(A19flowA19operService.class).getList(id);
    }

    @Override
    public void saveA19operList(@PathVariable String id, @RequestBody List<A19flowA19oper> list, @RequestParam(required = false) String[] fieldName) throws Exception {
        BeanUtils.get(A19flowA19operService.class).saveList(id, list, fieldName);
    }

}
