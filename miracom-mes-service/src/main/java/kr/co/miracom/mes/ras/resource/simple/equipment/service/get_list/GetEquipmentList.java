package kr.co.miracom.mes.ras.resource.simple.equipment.service.get_list;

import kr.co.miracom.dbist.dml.Page;
import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.ras.model.MRasEqpDef;
import kr.co.miracom.mes.ras.resource.simple.equipment.model.Equipment;

/**
 * Get List Service: Equipment
 * @author myjung.jung
 * @since 2018. 06. 11.
 */
public class GetEquipmentList {
    public GetListOut<Equipment> getList(GetEquipmentListIn input) throws Exception {
        Query query = DbUtils.newQuery(input);
        query.addFilter("factoryCode", AuthUtils.getFactoryCode());
        DbUtils.addContains(query, "equipCode", input.getEquipCode());
        DbUtils.addContains(query, "equipDesc", input.getEquipDesc());
        DbUtils.addEquals(query, "equipType", input.getEquipType());
        // DbUtils.addContains(query, "areaCode", input.getAreaCode());
        DbUtils.addContains(query, "lineCode", input.getLineCode());
        // DbUtils.addEquals(query, "pmSchFlag", input.getPmSchFlag());
        // DbUtils.addEquals(query, "maxProcCount", input.getMaxProcCount());
        if (!input.isIncludeDeleteFlag()) {
            query.addFilter("deleteFlag", false);
        }
        // DbUtils.addContains(query, "deleteUserId", input.getDeleteUserId());
        // DbUtils.addEquals(query, "deleteTime", input.getDeleteTime());
        // DbUtils.addContains(query, "createUserId", input.getCreateUserId());
        // DbUtils.addEquals(query, "createTime", input.getCreateTime());
        // DbUtils.addContains(query, "updateUserId", input.getUpdateUserId());
        // DbUtils.addEquals(query, "updateTime", input.getUpdateTime());
        query.addOrder("equipCode", true);
        Page<Equipment> page = DbUtils.selectPage(MRasEqpDef.class, query, Equipment.class);
        GetListOut<Equipment> output = new GetListOut<>();
        ValueUtils.populate(page, output);
        return output;
    }
}
