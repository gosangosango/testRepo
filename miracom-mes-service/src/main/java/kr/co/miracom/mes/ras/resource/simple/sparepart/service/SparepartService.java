package kr.co.miracom.mes.ras.resource.simple.sparepart.service;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.RestService;
import kr.co.miracom.mes.ras.model.MRasSptDef;
import kr.co.miracom.mes.ras.resource.simple.sparepart.model.Sparepart;
import kr.co.miracom.mes.ras.resource.simple.sparepart.model.SparepartDetail;

/**
 * Setup Service: Spt
 * @author hhk
 * @since 2018. 07. 02.
 */
public class SparepartService {
    private RestService<MRasSptDef, Sparepart, SparepartDetail> restService = new RestService<>(MRasSptDef.class, Sparepart.class, SparepartDetail.class);

    public SparepartDetail get(String id, SelectOptions options) throws Exception {
        return restService.get(id, options);
    }

    public void post(String id, SparepartDetail data, String[] fieldName) throws Exception {
        restService.post(id, data, fieldName);
    }

    public void put(String id, SparepartDetail data, String[] fieldName) throws Exception {
        restService.put(id, data, fieldName);
    }

    public void delete(String id, SparepartDetail data) throws Exception {
        restService.delete(id, data);
    }

    public void postList(List<Sparepart> list, String[] fieldName) throws Exception {
        restService.postList(list, fieldName);
    }

    public void putList(List<Sparepart> list, String[] fieldName) throws Exception {
        restService.putList(list, fieldName);
    }

    public void saveList(List<Sparepart> list, String[] fieldName) throws Exception {
        restService.saveList(list, fieldName);
    }

    public void deleteList(List<Sparepart> list) throws Exception {
        restService.deleteList(list);
    }

}
