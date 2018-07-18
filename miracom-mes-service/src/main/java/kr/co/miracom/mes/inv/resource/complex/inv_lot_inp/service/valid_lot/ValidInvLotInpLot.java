package kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.valid_lot;

import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.exception.BizException;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.inv.model.MInvLotSts;
import kr.co.miracom.mes.wip.model.MWipInvLod;
import kr.co.miracom.mes.wip.model.MWipMatDef;
import kr.co.miracom.mes.wip.model.MWipOrdBom;

/**
 * Get Service: LotEnd
 * @author gom
 * @since 2018. 06. 25.
 */
public class ValidInvLotInpLot {
    public ValidInvLotInpLotOut validLot(ValidInvLotInpLotIn input) throws Exception {
    
        ValueUtils.checkNotEmpty(input, "invLotId", "orderNo");
        
        Query query = new Query();
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        query.setFilter("invLotId", input.getInvLotId());
        
        MInvLotSts invLot = DbUtils.select(MInvLotSts.class, query);
        
        if ( invLot == null ) {
            
            throw new BizException("자재 LOT이 존재하지 않습니다.");
            
        }
        
        if ( invLot.isLotDelFlag() ) {
            
            throw new BizException("자재 LOT이 이미 삭제 되었습니다.");
            
        }
        
        if ( invLot.getQty() == 0.0 ) {
            
            throw new BizException("MIC-00058");
            
        }
        
        MWipInvLod invLod = DbUtils.select(MWipInvLod.class, query);
        
        if ( !ValueUtils.isEmpty(invLod) ) {
            
            throw new BizException("MIC-00059");
            
        }
        
        query = new Query();
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        query.setFilter("matCode", invLot.getMatCode());
        
        MWipMatDef matDef = DbUtils.select(MWipMatDef.class, query);
        
        if ( ValueUtils.isEmpty(matDef) ) {
            
            throw new BizException("자재 정보가 존재하지 않습니다.");
            
        }
        
        if ( !"B".equals(matDef.getDeductionType()) ) {
            
            throw new BizException("MIC-00061");
            
        }
        
        query = new Query();
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        query.setFilter("orderNo", input.getOrderNo());
        query.setFilter("childMatCode", invLot.getMatCode());
        
        MWipOrdBom ordBom = DbUtils.select(MWipOrdBom.class, query);
        
        if ( ValueUtils.isEmpty(ordBom) ) {
            
            throw new BizException("MIC-00060");
            
        }
        
        ValidInvLotInpLotOut output = new ValidInvLotInpLotOut();
        output.setInvLotId(invLot.getInvLotId());
        output.setQty(invLot.getQty());
        output.setMatCode(invLot.getMatCode());
        output.setMatDesc(matDef.getMatShortDesc());
        
        return output;
    }
        
}
