package kr.co.miracom.mes.wip.resource.simple.material.service;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.RestService;
import kr.co.miracom.mes.wip.model.MWipMatDef;
import kr.co.miracom.mes.wip.resource.simple.material.model.Material;
import kr.co.miracom.mes.wip.resource.simple.material.model.MaterialDetail;

/**
 * Setup Service: Material
 * @author myjung.jung
 * @since 2018. 06. 05.
 */
public class MaterialService {
    private RestService<MWipMatDef, Material, MaterialDetail> restService = new RestService<>(MWipMatDef.class,
                    Material.class, MaterialDetail.class);

    public MaterialDetail get(String id, SelectOptions options) throws Exception {
        return restService.get(id, options);
    }

    public void post(String id, MaterialDetail data, String[] fieldName) throws Exception {
        restService.post(id, data, fieldName);
    }

    public void put(String id, MaterialDetail data, String[] fieldName) throws Exception {
        restService.put(id, data, fieldName);
    }

    public void delete(String id, MaterialDetail data) throws Exception {
        restService.delete(id, data);
    }

    public void postList(List<Material> list, String[] fieldName) throws Exception {
        restService.postList(list, fieldName);
    }

    public void putList(List<Material> list, String[] fieldName) throws Exception {
        restService.putList(list, fieldName);
    }

    public void saveList(List<Material> list, String[] fieldName) throws Exception {
        restService.saveList(list, fieldName);
    }

    public void deleteList(List<Material> list) throws Exception {
        restService.deleteList(list);
    }

}
