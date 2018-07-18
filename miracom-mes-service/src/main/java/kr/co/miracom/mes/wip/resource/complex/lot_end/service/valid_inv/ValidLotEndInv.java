package kr.co.miracom.mes.wip.resource.complex.lot_end.service.valid_inv;

import java.util.List;

import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.exception.BizException;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.inv.model.MInvLotSts;
import kr.co.miracom.mes.wip.model.MWipBomOpr;
import kr.co.miracom.mes.wip.model.MWipFlwDef;
import kr.co.miracom.mes.wip.model.MWipInvLod;
import kr.co.miracom.mes.wip.model.MWipLotSts;
import kr.co.miracom.mes.wip.model.MWipMatDef;
import kr.co.miracom.mes.wip.model.MWipOrdBom;
import kr.co.miracom.mes.wip.model.MWipOrdSts;
import kr.co.miracom.mes.wip.resource.simple.lot.service.create.CreateLot;
import kr.co.miracom.mes.wip.resource.simple.lot.service.create.CreateLotIn;

/**
 * Get Service: LotEnd
 * @author gom
 * @since 2018. 06. 25.
 */
public class ValidLotEndInv {
    public ValidLotEndInvOut validInv(ValidLotEndInvIn input) throws Exception {
        
        ValueUtils.checkNotEmpty(input, "invLotId", "orderNo", "operCode");
        
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
            
            throw new BizException("자재의 수량이 0입니다.");
            
        }
        
        query = new Query();
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        query.setFilter("matCode", invLot.getMatCode());
        
        MWipMatDef matDef = DbUtils.select(MWipMatDef.class, query);
        
        if ( matDef == null ) {
            
            throw new BizException("자재 정보가 존재하지 않습니다.");
            
        }
        
        if ( matDef.isDeleteFlag() ) {
            
            throw new BizException("단종된 자재 입니다.");
            
        }
        
        //자재 투입 여부를 체크 한다.
        checkInputLot(input);
        
        //작업지시 BOM 정보를 체크 한다.
        checkOrdBom(input, invLot);
        
        //선입선출을 체크한다.
        checkFifo(input);
        
        ValidLotEndInvOut output = new ValidLotEndInvOut();
        output.setInvLotId(invLot.getInvLotId());
        output.setQty(invLot.getQty());
        output.setMatCode(invLot.getMatCode());
        
        return output;
    }
    
    private void checkFifo(ValidLotEndInvIn input) throws Exception {
        
        
    }
    
    private void checkOrdBom(ValidLotEndInvIn input, MInvLotSts invLot) throws Exception {
        
        Query query = new Query();
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        query.setFilter("orderNo", input.getOrderNo());
        query.setFilter("childMatCode", invLot.getMatCode());
        
        MWipOrdBom ordBom = DbUtils.select(MWipOrdBom.class, query);
        
        if ( ordBom == null ) {
            
            throw new BizException("작업지시 BOM에 자재 정보가 없습니다.");
            
        }
        
        query = new Query();
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        query.setFilter("matCode", ordBom.getMatCode());
        query.setFilter("childMatCode", invLot.getMatCode());
        
        List<MWipBomOpr> bomOprList = DbUtils.selectList(MWipBomOpr.class, query);
        
        if ( bomOprList != null && bomOprList.size() > 0 ) {
            boolean operCheck = false;
            
            for ( MWipBomOpr bomOpr : bomOprList ) {
                
                if ( bomOpr.getOperCode().equals(input.getOperCode()) ) {
                    
                    operCheck = true;
                    break;
                    
                }
                
            }

            if ( !operCheck ) {
                
                throw new BizException("해당 공정에 투입 가능한 자재가 아닙니다.");
                
            }
            
        }
        
    }
    
    private void checkInputLot(ValidLotEndInvIn input) throws Exception {
        
        Query query = new Query();
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        query.setFilter("invLotId", input.getInvLotId());
        
        MWipInvLod invLod = DbUtils.select(MWipInvLod.class, query);
        
        if ( invLod != null ) {
            
            throw new BizException("이미 투입된 자재입니다.");            
            
        }
        
        
    }
    
        
}
