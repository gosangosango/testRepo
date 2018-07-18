package kr.co.miracom.mes.wip.resource.simple.operation.service.get_list;

import kr.co.miracom.dbist.dml.Page;
import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.wip.model.MWipOprDef;
import kr.co.miracom.mes.wip.resource.simple.operation.model.Operation;

/**
 * Get List Service: Operation
 * @author myjung.jung
 * @since 2018. 06. 05.
 */
public class GetOperationList {
    public GetListOut<Operation> getList(GetOperationListIn input) throws Exception {
        Query query = DbUtils.newQuery(input);
        query.addFilter("factoryCode", AuthUtils.getFactoryCode());
        DbUtils.addContains(query, "operCode", input.getOperCode());
        DbUtils.addContains(query, "operDesc", input.getOperDesc());
        // DbUtils.addContains(query, "unit", input.getUnit());
        // DbUtils.addEquals(query, "startRequireFlag", input.getStartRequireFlag());
        // DbUtils.addEquals(query, "pushPullFlag", input.getPushPullFlag());
        // DbUtils.addEquals(query, "erpIfFlag", input.getErpIfFlag());
        // DbUtils.addContains(query, "erpOperCode", input.getErpOperCode());
        if (input.isStoreOnlyFlag()) {
            query.addFilter("storeFlag", true);
        } else if (input.isOperOnlyFlag()) {
            query.addFilter("storeFlag", false);
        }
        DbUtils.addEquals(query, "storeGrp", input.getStoreGrp());
        // DbUtils.addContains(query, "createUserId", input.getCreateUserId());
        // DbUtils.addEquals(query, "createTime", input.getCreateTime());
        // DbUtils.addContains(query, "updateUserId", input.getUpdateUserId());
        // DbUtils.addEquals(query, "updateTime", input.getUpdateTime());
        query.addOrder("operCode", true);
        Page<Operation> page;
        if (input.isEquipSetOnlyFlag()) {
            page = DbUtils.selectPage(DbUtils.toSqlPath(GetOperationList.class, "select_equipoper_list.sql"), query,
                            Operation.class);
        } else {
            page = DbUtils.selectPage(MWipOprDef.class, query, Operation.class);
        }
        GetListOut<Operation> output = new GetListOut<>();
        ValueUtils.populate(page, output);
        return output;
    }
}
