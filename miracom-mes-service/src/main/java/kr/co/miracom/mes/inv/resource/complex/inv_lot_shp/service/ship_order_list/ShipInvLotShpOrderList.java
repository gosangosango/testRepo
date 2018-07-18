package kr.co.miracom.mes.inv.resource.complex.inv_lot_shp.service.ship_order_list;

import java.util.List;

import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_shp.model.ShipOrd;

/**
 * Get Service: InvLotShp
 * @author gom
 * @since 2018. 07. 10.
 */
public class ShipInvLotShpOrderList {
    public ShipInvLotShpOrderListOut shipOrderList(ShipInvLotShpOrderListIn input) throws Exception {
        
        ValueUtils.checkNotEmpty(input, "startDate", "endDate");
        Query query = new Query();
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        query.setFilter("startDate", input.getStartDate());
        query.setFilter("endDate", input.getEndDate());
        query.setFilter("customerCode", input.getCustomerCode());
        query.setFilter("matCode", input.getMatCode());
        
        List<ShipOrd> list = DbUtils.selectList(DbUtils.toSqlPath(ShipInvLotShpOrderList.class, "select_ship_order_list.sql"), query, ShipOrd.class);
        
        ShipInvLotShpOrderListOut output = new ShipInvLotShpOrderListOut();
        output.setList(list);
        
        return output;
    }
}
