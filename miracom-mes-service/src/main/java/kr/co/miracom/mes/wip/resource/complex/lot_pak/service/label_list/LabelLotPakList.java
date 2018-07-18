package kr.co.miracom.mes.wip.resource.complex.lot_pak.service.label_list;

import java.util.List;

import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.exception.BizException;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.wip.resource.complex.lot_pak.model.PackLot;

/**
 * Get Service: LotPak
 * @author gom
 * @since 2018. 07. 02.
 */
public class LabelLotPakList {
    public LabelLotPakListOut labelList(LabelLotPakListIn input) throws Exception {
        
        ValueUtils.checkNotEmpty(input, "lineCode", "matCode", "labelType");
        
        if ( !"LOT".equals(input.getLabelType()) ) {
            
            throw new BizException("MIC-00002");
            
        }
        
        Query query = new Query();
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        query.setFilter("lineCode", input.getLineCode());
        query.setFilter("matCode", input.getMatCode());
        query.setFilter("labelType", input.getLabelType());
        
        List<PackLot> packLotList = DbUtils.selectList(DbUtils.toSqlPath(LabelLotPakList.class, "select_label_list.sql"), query, PackLot.class);
        
        LabelLotPakListOut output = new LabelLotPakListOut();
        output.setList(packLotList);
        
        return output;
    }
}
