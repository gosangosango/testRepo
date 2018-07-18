package kr.co.miracom.mes.a19.resource.simple.a19lot.controller;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.mes.a19.resource.simple.a19lot.model.A19lot;
import kr.co.miracom.mes.a19.resource.simple.a19lot.model.A19lotDetail;
import kr.co.miracom.mes.a19.resource.simple.a19lot.service.A19lotService;
import kr.co.miracom.mes.a19.resource.simple.a19lot.service.get_list.GetA19lotList;
import kr.co.miracom.mes.a19.resource.simple.a19lot.service.get_list.GetA19lotListIn;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Setup Controller: A19lot
 * @author Zotac023
 * @since 2018. 07. 18.
 */
@CrossOrigin
@RestController
public class A19lotControllerImpl implements A19lotController {

    @Override
    public A19lotDetail get(@PathVariable String id, SelectOptions options) throws Exception {
        return BeanUtils.get(A19lotService.class).get(id, options);
    }

    @Override
    public void put(@PathVariable String id, @RequestBody A19lotDetail data, @RequestParam(required = false) String[] fieldName) throws Exception {
        BeanUtils.get(A19lotService.class).put(id, data, fieldName);
    }

    @Override
    public GetListOut<A19lot> getList(GetA19lotListIn input) throws Exception {
        return BeanUtils.get(GetA19lotList.class).getList(input);
    }

    @Override
    public void saveList(@RequestBody List<A19lot> list, @RequestParam(required = false) String[] fieldName) throws Exception {
        BeanUtils.get(A19lotService.class).saveList(list, fieldName);
    }

    @Override
    public void deleteList(@RequestBody List<A19lot> list) throws Exception {
        BeanUtils.get(A19lotService.class).deleteList(list);
    }

}
