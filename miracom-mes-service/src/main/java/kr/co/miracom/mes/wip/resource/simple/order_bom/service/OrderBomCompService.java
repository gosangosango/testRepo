package kr.co.miracom.mes.wip.resource.simple.order_bom.service;

import java.util.List;

import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.service.RestService4OneToMany;
import kr.co.miracom.mes.wip.model.MWipOrdBom;
import kr.co.miracom.mes.wip.model.MWipOrdSts;
import kr.co.miracom.mes.wip.resource.simple.order_bom.model.OrderBomComp;

/**
 * Setup Service: OrderBom
 * @author myjung.jung
 * @since 2018. 06. 26.
 */
public class OrderBomCompService {
    private RestService4OneToMany<MWipOrdSts, MWipOrdBom, OrderBomComp> restService = new RestService4OneToMany<>(
                    MWipOrdSts.class, MWipOrdBom.class, OrderBomComp.class);

    public GetListOut<OrderBomComp> getList(String id) throws Exception {
        Query query = new Query();
        query.addOrder("childMatCode", true);
        GetListOut<OrderBomComp> output = restService.getList(id, query);
        return output;
    }

    public void saveList(String id, List<OrderBomComp> list, String[] fieldName) throws Exception {
        // restService.saveList(id, list, fieldName);
    }

}
