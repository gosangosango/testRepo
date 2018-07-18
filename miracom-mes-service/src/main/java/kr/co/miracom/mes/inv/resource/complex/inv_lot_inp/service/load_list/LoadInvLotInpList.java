package kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.load_list;

import java.util.ArrayList;
import java.util.List;

import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.exception.BizException;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.inv.model.MInvLotSts;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.model.LoadInv;
import kr.co.miracom.mes.wip.model.MWipInvLod;
import kr.co.miracom.mes.wip.resource.complex.lot_pak.model.PackLot;

/**
 * Get Service: InvLotInput
 * @author gom
 * @since 2018. 07. 04.
 */
public class LoadInvLotInpList {
    public LoadInvLotInpListOut loadList(LoadInvLotInpListIn input) throws Exception {
        
        ValueUtils.checkNotEmpty(input, "lineCode", "matCode");
        
        Query query = new Query();
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        query.setFilter("lineCode", input.getLineCode());
        query.setFilter("matCode", input.getMatCode());
        
        List<MWipInvLod> invLodList = DbUtils.selectList(MWipInvLod.class, query);
        MInvLotSts invLot;
        LoadInv loadInv;
        List<LoadInv> list = new ArrayList<LoadInv>();
        for ( MWipInvLod invLod : invLodList ) {
            
            query = new Query();
            query.setFilter("factoryCode", AuthUtils.getFactoryCode());
            query.setFilter("invLotId", invLod.getInvLotId());
            
            invLot = DbUtils.select(MInvLotSts.class, query);
            
            if ( invLot == null ) {
                
                throw new BizException("자재 LOT이 존재하지 않습니다.");
                
            }
            
            loadInv = new LoadInv();
            ValueUtils.populate(invLot, loadInv);
            
            loadInv.setQty(invLot.getQty());
            
            list.add(loadInv);
            
        }
        
        LoadInvLotInpListOut output = new LoadInvLotInpListOut();
        output.setList(list);
        
        return output;
    }
}
