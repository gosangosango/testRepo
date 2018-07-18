package kr.co.miracom.mes.wip.resource.simple.line.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.mes.wip.resource.simple.line.model.Line;
import kr.co.miracom.mes.wip.resource.simple.line.model.LineDetail;
import kr.co.miracom.mes.wip.resource.simple.line.service.LineService;
import kr.co.miracom.mes.wip.resource.simple.line.service.get_list.GetLineList;
import kr.co.miracom.mes.wip.resource.simple.line.service.get_list.GetLineListIn;

/**
 * Setup Controller: Line
 * @author myjung.jung
 * @since 2018. 06. 05.
 */
@CrossOrigin
@RestController
public class LineControllerImpl implements LineController {

    @Override
    public LineDetail get(@PathVariable String id, SelectOptions options) throws Exception {
        return BeanUtils.get(LineService.class).get(id, options);
    }

    @Override
    public void put(@PathVariable String id, @RequestBody LineDetail data,
                    @RequestParam(required = false) String[] fieldName) throws Exception {
        BeanUtils.get(LineService.class).put(id, data, fieldName);
    }

    @Override
    public GetListOut<Line> getList(GetLineListIn input) throws Exception {
        return BeanUtils.get(GetLineList.class).getList(input);
    }

    @Override
    public void saveList(@RequestBody List<Line> list, @RequestParam(required = false) String[] fieldName)
                    throws Exception {
        BeanUtils.get(LineService.class).saveList(list, fieldName);
    }

    @Override
    public void deleteList(@RequestBody List<Line> list) throws Exception {
        BeanUtils.get(LineService.class).deleteList(list);
    }

}
