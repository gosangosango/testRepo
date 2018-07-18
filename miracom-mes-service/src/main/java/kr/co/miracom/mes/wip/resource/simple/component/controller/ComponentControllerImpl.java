package kr.co.miracom.mes.wip.resource.simple.component.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.mes.wip.resource.simple.component.model.Component;
import kr.co.miracom.mes.wip.resource.simple.component.model.ComponentAlterMat;
import kr.co.miracom.mes.wip.resource.simple.component.model.ComponentDetail;
import kr.co.miracom.mes.wip.resource.simple.component.model.ComponentOper;
import kr.co.miracom.mes.wip.resource.simple.component.service.ComponentAlterMatService;
import kr.co.miracom.mes.wip.resource.simple.component.service.ComponentOperService;
import kr.co.miracom.mes.wip.resource.simple.component.service.ComponentService;
import kr.co.miracom.mes.wip.resource.simple.component.service.get_list.GetComponentList;
import kr.co.miracom.mes.wip.resource.simple.component.service.get_list.GetComponentListIn;
import kr.co.miracom.mes.wip.resource.simple.component.service.get_tree.GetComponentTree;
import kr.co.miracom.mes.wip.resource.simple.component.service.get_tree.GetComponentTreeIn;
import kr.co.miracom.mes.wip.resource.simple.component.service.get_tree.GetComponentTreeOut;

/**
 * Setup Controller: Component
 * @author myjung.jung
 * @since 2018. 06. 26.
 */
@CrossOrigin
@RestController
public class ComponentControllerImpl implements ComponentController {

    @Override
    public ComponentDetail get(@PathVariable String id, SelectOptions options) throws Exception {
        return BeanUtils.get(ComponentService.class).get(id, options);
    }

    @Override
    public void put(@PathVariable String id, @RequestBody ComponentDetail data,
                    @RequestParam(required = false) String[] fieldName) throws Exception {
        BeanUtils.get(ComponentService.class).put(id, data, fieldName);
    }

    @Override
    public GetListOut<Component> getList(GetComponentListIn input) throws Exception {
        return BeanUtils.get(GetComponentList.class).getList(input);
    }

    @Override
    public GetComponentTreeOut getTree(GetComponentTreeIn input) throws Exception {
        return BeanUtils.get(GetComponentTree.class).getTree(input);
    }

    @Override
    public void saveList(@RequestBody List<Component> list, @RequestParam(required = false) String[] fieldName)
                    throws Exception {
        BeanUtils.get(ComponentService.class).saveList(list, fieldName);
    }

    @Override
    public void deleteList(@RequestBody List<Component> list) throws Exception {
        BeanUtils.get(ComponentService.class).deleteList(list);
    }

    @Override
    public GetListOut<ComponentOper> getOperList(@PathVariable String id) throws Exception {
        return BeanUtils.get(ComponentOperService.class).getList(id);
    }

    @Override
    public void saveOperList(@PathVariable String id, @RequestBody List<ComponentOper> list,
                    @RequestParam(required = false) String[] fieldName) throws Exception {
        BeanUtils.get(ComponentOperService.class).saveList(id, list, fieldName);
    }

    @Override
    public GetListOut<ComponentAlterMat> getAlterMatList(@PathVariable String id) throws Exception {
        return BeanUtils.get(ComponentAlterMatService.class).getList(id);
    }

    @Override
    public void saveAlterMatList(@PathVariable String id, @RequestBody List<ComponentAlterMat> list,
                    @RequestParam(required = false) String[] fieldName) throws Exception {
        BeanUtils.get(ComponentAlterMatService.class).saveList(id, list, fieldName);
    }

}
