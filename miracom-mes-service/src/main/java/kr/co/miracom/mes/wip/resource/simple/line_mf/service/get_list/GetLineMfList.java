package kr.co.miracom.mes.wip.resource.simple.line_mf.service.get_list;

import kr.co.miracom.dbist.dml.Page;
import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.wip.model.MWipMatFlw;
import kr.co.miracom.mes.wip.resource.simple.line_mf.model.LineMf;

/**
 * Get List Service: LineMf
 * @author mo21.kim
 * @since 2018. 07. 03.
 */
public class GetLineMfList {
    public GetListOut<LineMf> getList(GetLineMfListIn input) throws Exception {
        Query query = DbUtils.newQuery(input);
        query.addFilter("factoryCode", AuthUtils.getFactoryCode());
        if (ValueUtils.isEmpty(input.getLineCode())){
            query.addFilter("lineCode","<>","NONE");
        }
         DbUtils.addEquals(query, "matCode", input.getMatCode());
         DbUtils.addEquals(query, "flowCode", input.getFlowCode());
         DbUtils.addEquals(query, "lineCode", input.getLineCode());
        // DbUtils.addEquals(query, "seqNo", input.getSeqNo());
        // DbUtils.addContains(query, "createUserId", input.getCreateUserId());
        // DbUtils.addEquals(query, "createTime", input.getCreateTime());
        // DbUtils.addContains(query, "updateUserId", input.getUpdateUserId());
        // DbUtils.addEquals(query, "updateTime", input.getUpdateTime());
         query.addOrder("lineCode", true);
         query.addOrder("matCode", true);
         query.addOrder("flowCode", true);
         query.addOrder("seqNo", true);
        Page<LineMf> page = DbUtils.selectPage(MWipMatFlw.class, query, LineMf.class);
        GetListOut<LineMf> output = new GetListOut<>();
        ValueUtils.populate(page, output);
        return output;
    }
}
