package kr.co.miracom.mes.wip.resource.simple.component.service;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.RestService;
import kr.co.miracom.mes.wip.model.MWipBomDef;
import kr.co.miracom.mes.wip.resource.simple.component.model.Component;
import kr.co.miracom.mes.wip.resource.simple.component.model.ComponentDetail;

/**
 * Setup Service: Component
 * @author myjung.jung
 * @since 2018. 06. 26.
 */
public class ComponentService {
    private RestService<MWipBomDef, Component, ComponentDetail> restService = new RestService<>(MWipBomDef.class,
                    Component.class, ComponentDetail.class);

    public ComponentDetail get(String id, SelectOptions options) throws Exception {
        return restService.get(id, options);
    }

    public void post(String id, ComponentDetail data, String[] fieldName) throws Exception {
        restService.post(id, data, fieldName);
    }

    public void put(String id, ComponentDetail data, String[] fieldName) throws Exception {
        restService.put(id, data, fieldName);
    }

    public void delete(String id, ComponentDetail data) throws Exception {
        restService.delete(id, data);
    }

    public void postList(List<Component> list, String[] fieldName) throws Exception {
        restService.postList(list, fieldName);
    }

    public void putList(List<Component> list, String[] fieldName) throws Exception {
        restService.putList(list, fieldName);
    }

    public void saveList(List<Component> list, String[] fieldName) throws Exception {
        restService.saveList(list, fieldName);
    }

    public void deleteList(List<Component> list) throws Exception {
        restService.deleteList(list);
    }

}
