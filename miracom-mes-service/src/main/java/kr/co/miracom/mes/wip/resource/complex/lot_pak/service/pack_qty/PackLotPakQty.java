package kr.co.miracom.mes.wip.resource.complex.lot_pak.service.pack_qty;

import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.exception.BizException;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.wip.model.MWipMatDef;

/**
 * Get Service: LotPak
 * @author gom
 * @since 2018. 07. 02.
 */
public class PackLotPakQty {
    public PackLotPakQtyOut packQty(PackLotPakQtyIn input) throws Exception {
        ValueUtils.checkNotEmpty(input, "matCode", "labelType");
        
        Query query = new Query();
        query.setFilter("matCode", input.getMatCode());
        
        MWipMatDef matDef = DbUtils.select(MWipMatDef.class, query);
        
        if ( matDef == null ) {
            
            throw new BizException("자제/제품 정보가 존재하지 않습니다.");
            
        }
        
        Double packQty = 0.0;
        if ( "LOT".equals(input.getLabelType()) ) {
        
            packQty = matDef.getLotQty();
            
        } else if ( "SMALL".equals(input.getLabelType())) {
            
            packQty = matDef.getSmallPackQty();
            
        } else if ( "BOX".equals(input.getLabelType())) {
            
            packQty = matDef.getPackQty();
            
        } else if ( "LARGE".equals(input.getLabelType())) {
            
            packQty = matDef.getLargePackQty();
            
        } else if ( "PALLET".equals(input.getLabelType())) {
            
            packQty = matDef.getPalletQty();
            
        }
        
        PackLotPakQtyOut output = new PackLotPakQtyOut();
        output.setPackQty(packQty);
        
        return output;
    }
}
