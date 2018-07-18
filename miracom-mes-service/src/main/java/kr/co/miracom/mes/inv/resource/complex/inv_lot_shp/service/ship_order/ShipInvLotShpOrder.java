package kr.co.miracom.mes.inv.resource.complex.inv_lot_shp.service.ship_order;

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
public class ShipInvLotShpOrder {
    public ShipInvLotShpOrderOut shipOrder(ShipInvLotShpOrderIn input) throws Exception {
        
        ValueUtils.checkNotEmpty(input, "shipOrdNo");
        Query query = new Query();
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        query.setFilter("shipOrdNo", input.getShipOrdNo());
        
        ShipOrd shipOrd = DbUtils.select(DbUtils.toSqlPath(ShipInvLotShpOrder.class, "select_ship_order.sql"), query, ShipOrd.class);
        
        ShipInvLotShpOrderOut output = new ShipInvLotShpOrderOut();
        ValueUtils.populate(shipOrd, output);
        
        return output;
    }
}
