package kr.co.miracom.mes.wip.resource.simple.customer.service.get_list;

import kr.co.miracom.dbist.dml.Page;
import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.wip.model.MWipCusDef;
import kr.co.miracom.mes.wip.resource.simple.customer.model.Customer;

/**
 * Get List Service: Customer
 * @author myjung.jung
 * @since 2018. 06. 14.
 */
public class GetCustomerList {
    public GetListOut<Customer> getList(GetCustomerListIn input) throws Exception {
        Query query = DbUtils.newQuery(input);
        query.addFilter("factoryCode", AuthUtils.getFactoryCode());
        DbUtils.addContains(query, "customerCode", input.getCustomerCode());
        DbUtils.addContains(query, "customerDesc", input.getCustomerDesc());
        // DbUtils.addContains(query, "createUserId", input.getCreateUserId());
        // DbUtils.addEquals(query, "createTime", input.getCreateTime());
        // DbUtils.addContains(query, "updateUserId", input.getUpdateUserId());
        // DbUtils.addEquals(query, "updateTime", input.getUpdateTime());
        query.addOrder("customerCode", true);
        Page<Customer> page = DbUtils.selectPage(MWipCusDef.class, query, Customer.class);
        GetListOut<Customer> output = new GetListOut<>();
        ValueUtils.populate(page, output);
        return output;
    }
}
