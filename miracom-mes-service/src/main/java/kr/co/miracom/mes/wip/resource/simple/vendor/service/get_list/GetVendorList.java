package kr.co.miracom.mes.wip.resource.simple.vendor.service.get_list;

import kr.co.miracom.dbist.dml.Page;
import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.wip.model.MWipVenDef;
import kr.co.miracom.mes.wip.resource.simple.vendor.model.Vendor;

/**
 * Get List Service: Vendor
 * @author myjung.jung
 * @since 2018. 06. 14.
 */
public class GetVendorList {
    public GetListOut<Vendor> getList(GetVendorListIn input) throws Exception {
        Query query = DbUtils.newQuery(input);
        query.addFilter("factoryCode", AuthUtils.getFactoryCode());
        DbUtils.addContains(query, "vendorCode", input.getVendorCode());
        DbUtils.addContains(query, "vendorDesc", input.getVendorDesc());
        // DbUtils.addContains(query, "createUserId", input.getCreateUserId());
        // DbUtils.addEquals(query, "createTime", input.getCreateTime());
        // DbUtils.addContains(query, "updateUserId", input.getUpdateUserId());
        // DbUtils.addEquals(query, "updateTime", input.getUpdateTime());
        query.addOrder("vendorCode", true);
        Page<Vendor> page = DbUtils.selectPage(MWipVenDef.class, query, Vendor.class);
        GetListOut<Vendor> output = new GetListOut<>();
        ValueUtils.populate(page, output);
        return output;
    }
}
