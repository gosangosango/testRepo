package kr.co.miracom.mes.inv.resource.simple.inv_lot.service.unload;

import java.util.ArrayList;
import java.util.List;

import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.exception.BizException;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.inv.model.MInvLotSts;
import kr.co.miracom.mes.inv.util.InvUtils;
import kr.co.miracom.mes.ras.model.MRasEqpDef;
import kr.co.miracom.mes.wip.model.MWipInvLod;
import kr.co.miracom.mes.wip.model.MWipLinDef;
import kr.co.miracom.mes.wip.model.MWipMatDef;
import kr.co.miracom.mes.wip.model.MWipOprDef;
import kr.co.miracom.mes.wip.model.MWipVenDef;

public class UnloadInvLot {
    public UnloadInvLotOut unload(UnloadInvLotIn input) throws Exception {
        ValueUtils.checkNotEmpty(input, "lineCode", "operCode", "invLotId");
        
        DbUtils.checkFound(MWipLinDef.class, input.getLineCode());
        
        if ( !ValueUtils.isEmpty(input.getOperCode()) ) {
            
            DbUtils.checkFound(MWipOprDef.class, input.getOperCode());
            
        }
        
        if ( !ValueUtils.isEmpty(input.getEquipCode()) ) {
            
            DbUtils.checkFound(MRasEqpDef.class, input.getEquipCode());
            
        }
        
        Query query = new Query();
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        query.setFilter("invLotId", input.getInvLotId());
        
        MInvLotSts invLot = DbUtils.select(MInvLotSts.class, query);
        
        if ( ValueUtils.isEmpty(invLot) ) {
            
            throw new BizException("자재 LOT이 존재하지 않습니다.");
            
        }
        
        MWipInvLod invLod = DbUtils.select(MWipInvLod.class, query);
        
        if ( ValueUtils.isEmpty(invLod) ) {
            
            throw new BizException("자재가 로드 되지 않았습니다.");
            
        }
        
        
        List<MWipInvLod> invLodList = new ArrayList<MWipInvLod>();
        invLodList.add(invLod);
        
        DbUtils.deleteBatch(invLodList);
        
        invLot.setOperCode(input.getOperCode());
        invLot.setLastTranCode("UNLOAD");
        invLot.setLastTranTime(ValueUtils.getZonedDateTime());
        
        DbUtils.update(invLot);
            
        InvUtils.insertInvLotHistory("UNLOAD", invLot);
        
        UnloadInvLotOut output = new UnloadInvLotOut();
        return output;
    }
}
