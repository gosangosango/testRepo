package kr.co.miracom.mes.wip.resource.simple.line_mf.controller;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.mes.wip.resource.simple.line_mf.model.LineMf;
import kr.co.miracom.mes.wip.resource.simple.line_mf.model.LineMfDetail;
import kr.co.miracom.mes.wip.resource.simple.line_mf.service.LineMfService;
import kr.co.miracom.mes.wip.resource.simple.line_mf.service.get_list.GetLineMfList;
import kr.co.miracom.mes.wip.resource.simple.line_mf.service.get_list.GetLineMfListIn;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Setup Controller: LineMf
 * @author mo21.kim
 * @since 2018. 07. 03.
 */
@CrossOrigin
@RestController
public class LineMfControllerImpl implements LineMfController {

    @Override
    public LineMfDetail get(@PathVariable String id, SelectOptions options) throws Exception {
        return BeanUtils.get(LineMfService.class).get(id, options);
    }

    @Override
    public void put(@PathVariable String id, @RequestBody LineMfDetail data, @RequestParam(required = false) String[] fieldName) throws Exception {
        BeanUtils.get(LineMfService.class).put(id, data, fieldName);
    }

    @Override
    public GetListOut<LineMf> getList(GetLineMfListIn input) throws Exception {
        return BeanUtils.get(GetLineMfList.class).getList(input);
    }

    @Override
    public void saveList(@RequestBody List<LineMf> list, @RequestParam(required = false) String[] fieldName) throws Exception {
        BeanUtils.get(LineMfService.class).saveList(list, fieldName);
    }

    @Override
    public void deleteList(@RequestBody List<LineMf> list) throws Exception {
        BeanUtils.get(LineMfService.class).deleteList(list);
    }

}
