package kr.co.miracom.mes.inv.resource.simple.delivery.service.get_list;

import kr.co.miracom.dbist.dml.Page;
import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.inv.model.MInvDlvMst;
import kr.co.miracom.mes.inv.resource.simple.delivery.model.Delivery;

/**
 * Get List Service: Delivery
 * @author myjung.jung
 * @since 2018. 06. 25.
 */
public class GetDeliveryList {
    public GetListOut<Delivery> getList(GetDeliveryListIn input) throws Exception {
        ValueUtils.checkNotEmpty(input, "fromDate", "toDate");

        Query query = DbUtils.newQuery(input);
        query.addFilter("factoryCode", AuthUtils.getFactoryCode());
        DbUtils.addContains(query, "dlvNo", input.getDlvNo());
        DbUtils.addContains(query, "poNo", input.getPoNo());
        // DbUtils.addEquals(query, "poSeq", input.getPoSeq());
        // DbUtils.addContains(query, "dlvExpDate", input.getDlvExpDate());
        DbUtils.addFilter(query, "dlvExpDate", ">=", input.getFromDate());
        DbUtils.addFilter(query, "dlvExpDate", "<=", input.getToDate());
        DbUtils.addEquals(query, "vendorCode", input.getVendorCode());
        // DbUtils.addContains(query, "purInspUserId", input.getPurInspUserId());
        // DbUtils.addEquals(query, "purInspTime", input.getPurInspTime());
        if (input.isPurInspCompOnlyFlag()) {
            DbUtils.addEquals(query, "purInspCompFlag", true);
        } else if (input.isPurInspIncompOnlyFlag()) {
            DbUtils.addEquals(query, "purInspCompFlag", false);
        }
        // DbUtils.addContains(query, "purInspDate", input.getPurInspDate());
        // DbUtils.addContains(query, "createUserId", input.getCreateUserId());
        // DbUtils.addEquals(query, "createTime", input.getCreateTime());
        // DbUtils.addContains(query, "updateUserId", input.getUpdateUserId());
        // DbUtils.addEquals(query, "updateTime", input.getUpdateTime());
        query.addOrder("dlvNo", true);
        Page<Delivery> page = DbUtils.selectPage(MInvDlvMst.class, query, Delivery.class);
        GetListOut<Delivery> output = new GetListOut<>();
        ValueUtils.populate(page, output);
        return output;
    }
}
