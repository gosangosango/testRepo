package kr.co.miracom.mes.wip.resource.simple.operation.service;

import java.util.List;

import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.exception.BizException;
import kr.co.miracom.framework.service.RestService;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.mes.inv.model.MInvLotSts;
import kr.co.miracom.mes.ras.model.MRasEqpOpr;
import kr.co.miracom.mes.wip.model.MWipFlwOpr;
import kr.co.miracom.mes.wip.model.MWipLotSts;
import kr.co.miracom.mes.wip.model.MWipOprDef;
import kr.co.miracom.mes.wip.resource.simple.operation.model.Operation;
import kr.co.miracom.mes.wip.resource.simple.operation.model.OperationDetail;

/**
 * Setup Service: Operation
 * @author myjung.jung
 * @since 2018. 06. 05.
 */
public class OperationService {
    private RestService<MWipOprDef, Operation, OperationDetail> restService = new RestService<>(MWipOprDef.class,
                    Operation.class, OperationDetail.class);

    public OperationDetail get(String id, SelectOptions options) throws Exception {
        return restService.get(id, options);
    }

    public void post(String id, OperationDetail data, String[] fieldName) throws Exception {
        restService.post(id, data, fieldName);
    }

    public void put(String id, OperationDetail data, String[] fieldName) throws Exception {
        restService.put(id, data, fieldName);
    }

    public void delete(String id, OperationDetail data) throws Exception {
        checkDelete(id);
        restService.delete(id, data);
    }

    public void postList(List<Operation> list, String[] fieldName) throws Exception {
        restService.postList(list, fieldName);
    }

    public void putList(List<Operation> list, String[] fieldName) throws Exception {
        restService.putList(list, fieldName);
    }

    public void saveList(List<Operation> list, String[] fieldName) throws Exception {
        restService.saveList(list, fieldName);
    }

    public void deleteList(List<Operation> list) throws Exception {
        for (Operation oper : list) {
            checkDelete(oper.getOperCode());
        }
        restService.deleteList(list);
    }

    private void checkDelete(String operCode) throws Exception {
        MWipOprDef wipOprDef = DbUtils.select(MWipOprDef.class, operCode);
        
        Query query = new Query(0, 1);
        query.addFilter("factoryCode", AuthUtils.getFactoryCode());
        query.addFilter("operCode", wipOprDef.getOperCode());
        query.addFilter("lotDelFlag", false);
        
        if (wipOprDef.isStoreFlag()) {
            if (DbUtils.select(MInvLotSts.class, query) != null) {
                throw new BizException("XXXXXX", "해당 창고에 자재Lot이 존재하므로 삭제할 수 없습니다.");
            }
        } else {
            if (DbUtils.select(MWipLotSts.class, query) != null) {
                throw new BizException("XXXXXX", "해당 공정에 Lot이 존재하므로 삭제할 수 없습니다.");
            }
            
            Query relQuery = new Query(0, 1);
            relQuery.addFilter("factoryCode", AuthUtils.getFactoryCode());
            relQuery.addFilter("operCode", wipOprDef.getOperCode());
            
            if (DbUtils.select(MWipFlwOpr.class, relQuery) != null) {
                throw new BizException("XXXXXX", "해당 공정을 사용하는 플로우가 존재하므로 삭제할 수 없습니다.");
            }
            
            if (DbUtils.select(MRasEqpOpr.class, relQuery) != null) {
                throw new BizException("XXXXXX", "해당 공정에 설비가 연결되어 있으므로 삭제할 수 없습니다.");
            }
        }
        
        
    }
}
