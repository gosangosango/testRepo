package kr.co.miracom.mes.ras.resource.simple.tool.controller;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.mes.ras.resource.simple.tool.model.Tool;
import kr.co.miracom.mes.ras.resource.simple.tool.model.ToolDetail;
import kr.co.miracom.mes.ras.resource.simple.tool.service.ToolService;
import kr.co.miracom.mes.ras.resource.simple.tool.service.get_list.GetToolList;
import kr.co.miracom.mes.ras.resource.simple.tool.service.get_list.GetToolListIn;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Setup Controller: Tool
 * @author hhk
 * @since 2018. 07. 03.
 */
@CrossOrigin
@RestController
public class ToolControllerImpl implements ToolController {

    @Override
    public ToolDetail get(@PathVariable String id, SelectOptions options) throws Exception {
        return BeanUtils.get(ToolService.class).get(id, options);
    }

    @Override
    public void put(@PathVariable String id, @RequestBody ToolDetail data, @RequestParam(required = false) String[] fieldName) throws Exception {
        BeanUtils.get(ToolService.class).put(id, data, fieldName);
    }

    @Override
    public GetListOut<Tool> getList(GetToolListIn input) throws Exception {
        return BeanUtils.get(GetToolList.class).getList(input);
    }

    @Override
    public void saveList(@RequestBody List<Tool> list, @RequestParam(required = false) String[] fieldName) throws Exception {
        BeanUtils.get(ToolService.class).saveList(list, fieldName);
    }

    @Override
    public void deleteList(@RequestBody List<Tool> list) throws Exception {
        BeanUtils.get(ToolService.class).deleteList(list);
    }

}
