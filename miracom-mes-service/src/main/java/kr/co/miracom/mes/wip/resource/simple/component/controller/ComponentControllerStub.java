package kr.co.miracom.mes.wip.resource.simple.component.controller;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.mes.wip.resource.simple.component.model.Component;
import kr.co.miracom.mes.wip.resource.simple.component.model.ComponentAlterMat;
import kr.co.miracom.mes.wip.resource.simple.component.model.ComponentDetail;
import kr.co.miracom.mes.wip.resource.simple.component.model.ComponentOper;
import kr.co.miracom.mes.wip.resource.simple.component.service.get_list.GetComponentListIn;
import kr.co.miracom.mes.wip.resource.simple.component.service.get_tree.GetComponentTreeIn;
import kr.co.miracom.mes.wip.resource.simple.component.service.get_tree.GetComponentTreeOut;

/**
 * Setup Controller: Component
 * @author myjung.jung
 * @since 2018. 06. 26.
 */
public class ComponentControllerStub implements ComponentController {

    @Override
    public ComponentDetail get(String id, SelectOptions options) throws Exception {
        return null;
    }

    @Override
    public void put(String id, ComponentDetail data, String[] fieldName) throws Exception {

    }

    @Override
    public GetListOut<Component> getList(GetComponentListIn input) throws Exception {
        return null;
    }

    @Override
    public GetComponentTreeOut getTree(GetComponentTreeIn input) throws Exception {
        return null;
    }

    @Override
    public void saveList(List<Component> list, String[] fieldName) throws Exception {

    }

    @Override
    public void deleteList(List<Component> list) throws Exception {

    }

    @Override
    public GetListOut<ComponentOper> getOperList(String id) throws Exception {
        return null;
    }

    @Override
    public void saveOperList(String id, List<ComponentOper> list, String[] fieldName) throws Exception {

    }

    @Override
    public GetListOut<ComponentAlterMat> getAlterMatList(String id) throws Exception {
        return null;
    }

    @Override
    public void saveAlterMatList(String id, List<ComponentAlterMat> list, String[] fieldName) throws Exception {

    }

}
