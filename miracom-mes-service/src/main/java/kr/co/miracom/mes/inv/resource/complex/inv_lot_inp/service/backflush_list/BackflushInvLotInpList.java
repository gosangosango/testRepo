package kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.backflush_list;

import java.util.List;

import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.model.BackflushMat;

/**
 * Get Service: InvLotInput
 * @author gom
 * @since 2018. 07. 04.
 */
public class BackflushInvLotInpList {
    public BackflushInvLotInpListOut backflushList(BackflushInvLotInpListIn input) throws Exception {
        
        ValueUtils.checkNotEmpty(input, "lineCode", "orderNo");
        
        Query query = new Query();
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        query.setFilter("lineCode", input.getLineCode());
        query.setFilter("orderNo", input.getOrderNo());
        
        List<BackflushMat> list = DbUtils.selectList(DbUtils.toSqlPath(BackflushInvLotInpList.class, "select_backflush_list.sql"), query, BackflushMat.class);
        
        BackflushInvLotInpListOut output = new BackflushInvLotInpListOut();
        output.setList(list);
        
        return output;
    }
}
