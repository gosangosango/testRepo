package kr.co.miracom.mes.inv.resource.simple.ret_mat.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.mes.inv.resource.simple.ret_mat.model.RetMat;
import kr.co.miracom.mes.inv.resource.simple.ret_mat.model.RetMatDetail;
import kr.co.miracom.mes.inv.resource.simple.ret_mat.service.RetMatService;
import kr.co.miracom.mes.inv.resource.simple.ret_mat.service.get_list.GetRetMatList;
import kr.co.miracom.mes.inv.resource.simple.ret_mat.service.get_list.GetRetMatListIn;
import kr.co.miracom.mes.inv.resource.simple.ret_mat.service.ret_list.RetMatList;
import kr.co.miracom.mes.inv.resource.simple.ret_mat.service.ret_list.RetMatListIn;
import kr.co.miracom.mes.inv.resource.simple.ret_mat.service.ret_list.RetMatListOut;

/**
 * Setup Controller: RetMat
 * @author mo21.kim
 * @since 2018. 07. 06.
 */
@CrossOrigin
@RestController
public class RetMatControllerImpl implements RetMatController {

    @Override
    public RetMatDetail get(@PathVariable String id, SelectOptions options) throws Exception {
        return BeanUtils.get(RetMatService.class).get(id, options);
    }

    @Override
    public void put(@PathVariable String id, @RequestBody RetMatDetail data, @RequestParam(required = false) String[] fieldName) throws Exception {
        BeanUtils.get(RetMatService.class).put(id, data, fieldName);
    }

    @Override
    public GetListOut<RetMat> getList(GetRetMatListIn input) throws Exception {
        return BeanUtils.get(GetRetMatList.class).getList(input);
    }

    @Override
    public RetMatListOut retMatList(@RequestBody RetMatListIn list) throws Exception {
        return BeanUtils.get(RetMatList.class).ret(list);
    }

    @Override
    public void deleteList(@RequestBody List<RetMat> list) throws Exception {
        BeanUtils.get(RetMatService.class).deleteList(list);
    }

}
