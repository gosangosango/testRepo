package kr.co.miracom.mes.inv.resource.simple.inv_request.service.get_list;

import kr.co.miracom.dbist.dml.Page;
import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.inv.resource.simple.inv_request.model.InvRequest;

/**
 * Get List Service: InvRequest
 * @author User
 * @since 2018. 07. 03.
 */
public class GetInvRequestList {
    public GetListOut<InvRequest> getList(GetInvRequestListIn input) throws Exception {
        Query query = DbUtils.newQuery(input);
        query.addFilter("factoryCode", AuthUtils.getFactoryCode());
        // DbUtils.addContains(query, "reqNo", input.getReqNo());
        // DbUtils.addContains(query, "orderNo", input.getOrderNo());
        query.addFilter("fromDate", input.getFromDate());
        query.addFilter("toDate", input.getToDate());
        // DbUtils.addContains(query, "createUserId", input.getCreateUserId());
        // DbUtils.addEquals(query, "createTime", input.getCreateTime());
        // DbUtils.addContains(query, "updateUserId", input.getUpdateUserId());
        // DbUtils.addEquals(query, "updateTime", input.getUpdateTime());
        query.addFilter("lineCode", input.getLineCode());
        Page<InvRequest> page = DbUtils.selectPage(DbUtils.toSqlPath(GetInvRequestList.class, "select_inv_request_list.sql"), query, InvRequest.class);
        GetListOut<InvRequest> output = new GetListOut<>();
        ValueUtils.populate(page, output);
        return output;
    }
}
