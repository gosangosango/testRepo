package kr.co.miracom.mes.inv.resource.simple.ret_mat.service.get_list;

import kr.co.miracom.dbist.dml.Page;
import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.inv.model.MInvRetMat;
import kr.co.miracom.mes.inv.resource.simple.ret_mat.model.RetMat;

/**
 * Get List Service: RetMat
 * @author mo21.kim
 * @since 2018. 07. 06.
 */
public class GetRetMatList {
    public GetListOut<RetMat> getList(GetRetMatListIn input) throws Exception {
        Query query = DbUtils.newQuery(input);
        query.addFilter("factoryCode", AuthUtils.getFactoryCode());
        // DbUtils.addContains(query, "retTimeStr", input.getRetTimeStr());
        // DbUtils.addContains(query, "invLotId", input.getInvLotId());
        // DbUtils.addContains(query, "matCode", input.getMatCode());
        // DbUtils.addContains(query, "retDate", input.getRetDate());
        // DbUtils.addContains(query, "orderNo", input.getOrderNo());
        // DbUtils.addContains(query, "fromOperCode", input.getFromOperCode());
        // DbUtils.addContains(query, "toOperCode", input.getToOperCode());
        // DbUtils.addEquals(query, "retType", input.getRetType());
        // DbUtils.addContains(query, "retCode", input.getRetCode());
        // DbUtils.addContains(query, "retReason", input.getRetReason());
        // DbUtils.addContains(query, "createUserId", input.getCreateUserId());
        // DbUtils.addEquals(query, "createTime", input.getCreateTime());
        // DbUtils.addContains(query, "updateUserId", input.getUpdateUserId());
        // DbUtils.addEquals(query, "updateTime", input.getUpdateTime());
        query.addOrder("retTimeStr", true);
        query.addOrder("invLotId", true);
        Page<RetMat> page = DbUtils.selectPage(MInvRetMat.class, query, RetMat.class);
        GetListOut<RetMat> output = new GetListOut<>();
        ValueUtils.populate(page, output);
        return output;
    }
}
