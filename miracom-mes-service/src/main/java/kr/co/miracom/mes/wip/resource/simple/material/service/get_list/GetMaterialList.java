package kr.co.miracom.mes.wip.resource.simple.material.service.get_list;

import kr.co.miracom.dbist.dml.Page;
import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.wip.model.MWipMatDef;
import kr.co.miracom.mes.wip.resource.simple.material.model.Material;

/**
 * Get List Service: Material
 * @author myjung.jung
 * @since 2018. 06. 05.
 */
public class GetMaterialList {
    public GetListOut<Material> getList(GetMaterialListIn input) throws Exception {
        Query query = DbUtils.newQuery(input);
        query.addFilter("factoryCode", AuthUtils.getFactoryCode());
        DbUtils.addContains(query, "matCode", input.getMatCode());
        DbUtils.addContains(query, "matDesc", input.getMatDesc());
        DbUtils.addContains(query, "matShortDesc", input.getMatShortDesc());
        // DbUtils.addEquas(query, "purchaseInspFlag", input.getPurchaseInspFlag());
        // DbUtils.addEquas(query, "importInspFlag", input.getImportInspFlag());
        // DbUtils.addEquas(query, "outgoingInspFlag", input.getOutgoingInspFlag());
        DbUtils.addEquals(query, "matType", input.getMatType());
        DbUtils.addEquals(query, "matGrp", input.getMatGrp());
        // DbUtils.addEquas(query, "weightNet", input.getWeightNet());
        // DbUtils.addEquas(query, "weightGross", input.getWeightGross());
        // DbUtils.addContains(query, "volumeUnit", input.getVolumeUnit());
        // DbUtils.addEquas(query, "volume", input.getVolume());
        // DbUtils.addEquas(query, "dimensionHr", input.getDimensionHr());
        // DbUtils.addContains(query, "dimensionHrUnit", input.getDimensionHrUnit());
        // DbUtils.addEquas(query, "dimensionVt", input.getDimensionVt());
        // DbUtils.addContains(query, "dimensionVtUnit", input.getDimensionVtUnit());
        // DbUtils.addEquas(query, "dimensionHt", input.getDimensionHt());
        // DbUtils.addContains(query, "dimensionHtUnit", input.getDimensionHtUnit());
        // DbUtils.addContains(query, "packType", input.getPackType());
        // DbUtils.addEquas(query, "packQty", input.getPackQty());
        // DbUtils.addContains(query, "deductionType", input.getDeductionType());
        // DbUtils.addEquas(query, "fifoFlag", input.getFifoFlag());
        if (!input.isIncludeDeleteFlag()) {
            query.addFilter("deleteFlag", false);
        }
        // DbUtils.addContains(query, "deleteUserId", input.getDeleteUserId());
        // DbUtils.addEquas(query, "deleteTime", input.getDeleteTime());
        // DbUtils.addContains(query, "createUserId", input.getCreateUserId());
        // DbUtils.addEquas(query, "createTime", input.getCreateTime());
        // DbUtils.addContains(query, "updateUserId", input.getUpdateUserId());
        // DbUtils.addEquas(query, "updateTime", input.getUpdateTime());
        query.addOrder("matCode", true);
        Page<Material> page;
        if (input.isPlanSetOnlyFlag()) {
            query.addFilter("planMonth", input.getPlanMonth());
            page = DbUtils.selectPage(DbUtils.toSqlPath(GetMaterialList.class, "select_planmat_list.sql"), query,
                            Material.class);
        } else {
            page = DbUtils.selectPage(MWipMatDef.class, query, Material.class);
        }
        GetListOut<Material> output = new GetListOut<>();
        ValueUtils.populate(page, output, input.getSelect());
        return output;
    }
}
