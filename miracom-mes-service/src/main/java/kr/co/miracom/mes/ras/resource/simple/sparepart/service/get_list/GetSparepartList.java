package kr.co.miracom.mes.ras.resource.simple.sparepart.service.get_list;

import kr.co.miracom.dbist.dml.Page;
import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.ras.model.MRasSptDef;
import kr.co.miracom.mes.ras.resource.simple.sparepart.model.Sparepart;

/**
 * Get List Service: Spt
 * @author hhk
 * @since 2018. 07. 02.
 */
public class GetSparepartList {
    public GetListOut<Sparepart> getList(GetSparepartListIn input) throws Exception {
        Query query = DbUtils.newQuery(input);
        query.addFilter("factoryCode", AuthUtils.getFactoryCode());
         DbUtils.addContains(query, "partCode", input.getPartCode());
         DbUtils.addContains(query, "partDesc", input.getPartDesc());
         DbUtils.addEquals(query, "storeCode", input.getStoreCode());
        // DbUtils.addContains(query, "locNo", input.getLocNo());
        // DbUtils.addEquals(query, "saftyQty", input.getSaftyQty());
        // DbUtils.addContains(query, "unit", input.getUnit());
        // DbUtils.addEquals(query, "stockFlag", input.getStockFlag());
        // DbUtils.addEquals(query, "labelFlag", input.getLabelFlag());
        // DbUtils.addContains(query, "createUserId", input.getCreateUserId());
        // DbUtils.addEquals(query, "createTime", input.getCreateTime());
        // DbUtils.addContains(query, "updateUserId", input.getUpdateUserId());
        // DbUtils.addEquals(query, "updateTime", input.getUpdateTime());
        query.addOrder("partCode", true);
        Page<Sparepart> page = DbUtils.selectPage(MRasSptDef.class, query, Sparepart.class);
        GetListOut<Sparepart> output = new GetListOut<>();
        ValueUtils.populate(page, output);
        return output;
    }
}
