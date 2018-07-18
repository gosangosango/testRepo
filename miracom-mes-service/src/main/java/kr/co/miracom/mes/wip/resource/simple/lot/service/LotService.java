package kr.co.miracom.mes.wip.resource.simple.lot.service;

import java.util.List;

import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.RestService;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.mes.wip.model.MWipFlwDef;
import kr.co.miracom.mes.wip.model.MWipLotSts;
import kr.co.miracom.mes.wip.resource.simple.lot.model.Lot;
import kr.co.miracom.mes.wip.resource.simple.lot.model.LotDetail;

/**
 * Setup Service: Lot
 * @author gom
 * @since 2018. 06. 21.
 */
public class LotService {
    private RestService<MWipLotSts, Lot, LotDetail> restService = new RestService<>(MWipLotSts.class, Lot.class, LotDetail.class);

    public LotDetail get(String id, SelectOptions options) throws Exception {
        return restService.get(id, options);
    }
    
    public void post(String id, LotDetail data, String[] fieldName) throws Exception {
        restService.post(id, data, fieldName);
    }
    
    public void put(String id, LotDetail data, String[] fieldName) throws Exception {
        
        
        restService.put(id, data, fieldName);
    }
    
    public void delete(String id, LotDetail data) throws Exception {
        restService.delete(id, data);
    }

    public void postList(List<Lot> list, String[] fieldName) throws Exception {
        restService.postList(list, fieldName);
    }

    public void putList(List<Lot> list, String[] fieldName) throws Exception {
        restService.putList(list, fieldName);
    }

    public void saveList(List<Lot> list, String[] fieldName) throws Exception {
        restService.saveList(list, fieldName);
    }

    public void deleteList(List<Lot> list) throws Exception {
        restService.deleteList(list);
    } 
    
}
