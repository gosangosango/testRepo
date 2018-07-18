package kr.co.miracom.mes.wip.resource.simple.line.service.get_list;

import kr.co.miracom.dbist.dml.Page;
import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.wip.model.MWipLinDef;
import kr.co.miracom.mes.wip.resource.simple.line.model.Line;

/**
 * Get List Service: Line
 * @author myjung.jung
 * @since 2018. 06. 05.
 */
public class GetLineList {
    public GetListOut<Line> getList(GetLineListIn input) throws Exception {
        Query query = DbUtils.newQuery(input);
        query.addFilter("factoryCode", AuthUtils.getFactoryCode());
        DbUtils.addContains(query, "lineCode", input.getLineCode());
        DbUtils.addContains(query, "lineDesc", input.getLineDesc());
        // DbUtils.addContains(query, "shift1Start", input.getShift1Start());
        // DbUtils.addContains(query, "erpLineCode", input.getErpLineCode());
        DbUtils.addEquals(query, "lineGrp", input.getLineGrp());
        if (!input.isIncludeDeleteFlag()) {
            query.addFilter("deleteFlag", false);
        }
        // DbUtils.addEquas(query, "prodFlag", input.getProdFlag());
        // DbUtils.addContains(query, "inpMatOperCode", input.getInpMatOperCode());
        // DbUtils.addContains(query, "toOperCode", input.getToOperCode());
        // DbUtils.addEquas(query, "deleteFlag", input.getDeleteFlag());
        // DbUtils.addContains(query, "deleteUserId", input.getDeleteUserId());
        // DbUtils.addEquas(query, "deleteTime", input.getDeleteTime());
        // DbUtils.addContains(query, "createUserId", input.getCreateUserId());
        // DbUtils.addEquas(query, "createTime", input.getCreateTime());
        // DbUtils.addContains(query, "updateUserId", input.getUpdateUserId());
        // DbUtils.addEquas(query, "updateTime", input.getUpdateTime());
        query.addOrder("lineCode", true);
        Page<Line> page = DbUtils.selectPage(MWipLinDef.class, query, Line.class);
        GetListOut<Line> output = new GetListOut<>();
        ValueUtils.populate(page, output);
        return output;
    }
}
