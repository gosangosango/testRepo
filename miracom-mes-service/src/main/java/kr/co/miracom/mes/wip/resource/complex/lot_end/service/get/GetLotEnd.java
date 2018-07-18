package kr.co.miracom.mes.wip.resource.complex.lot_end.service.get;

import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.exception.BizException;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.wip.model.MWipFlwDef;
import kr.co.miracom.mes.wip.model.MWipLotSts;
import kr.co.miracom.mes.wip.model.MWipMatDef;
import kr.co.miracom.mes.wip.model.MWipOrdSts;
import kr.co.miracom.mes.wip.resource.simple.lot.service.create.CreateLot;
import kr.co.miracom.mes.wip.resource.simple.lot.service.create.CreateLotIn;

/**
 * Get Service: LotEnd
 * @author gom
 * @since 2018. 06. 25.
 */
public class GetLotEnd {
    public GetLotEndOut get(GetLotEndIn input) throws Exception {
        
        Query query = new Query();
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        query.setFilter("lotId", input.getLotId());
        
        MWipLotSts lotSts = DbUtils.select(MWipLotSts.class, query);
        
        query = new Query();
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        query.setFilter("orderNo", input.getOrderNo());
        
        MWipOrdSts ordSts = DbUtils.select(MWipOrdSts.class, query);
        
        if ( ordSts == null ) {
            
            throw new BizException("작업지시가 존재하지 않습니다.");
            
        }
        
        if ( !ordSts.getLineCode().equals(input.getLineCode()) ) {
            
            throw new BizException("작업지시의 라인과 작업 라인이 일치하지 않습니다.");
            
        }
        
        //Lot 정보가 없으면 Lot을 생성한다.
        if ( lotSts == null ) {
            
            lotSts = createLot(input, ordSts);
            
        } else {
            
            if ( !ordSts.getOrderNo().equals(lotSts.getOrderNo()) ) {
                
                throw new BizException("LOT의 작업지시 정보가 일치하지 않습니다.");
                
            } 
            
        }
        
        GetLotEndOut output = new GetLotEndOut();
        output.setQty(lotSts.getQty());
        
        return output;
    }
    
    private MWipLotSts createLot(GetLotEndIn input, MWipOrdSts ordSts) throws Exception {
        
        Query query = new Query();
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        query.setFilter("flowCode", ordSts.getFlowCode());
        
        MWipFlwDef flwDef = DbUtils.select(MWipFlwDef.class, query);
        
        if ( flwDef == null ) {
            
            throw new BizException("플로우 정보가 존재하지 않습니다.");
            
        }
        
        if ( flwDef.getFirstOperCode() == null || "".equals(flwDef.getFirstOperCode()) ) {
            
            throw new BizException("플로우에 첫공정 정보가 없습니다.");
            
        }
        
        if ( !input.getOperCode().equals(flwDef.getFirstOperCode()) ) {
            
            throw new BizException("Lot 정보가 존재하지 않습니다.");
            
        }
        
        query = new Query();
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        query.setFilter("matCode", input.getMatCode());
        
        MWipMatDef matDef = DbUtils.select(MWipMatDef.class, query);
        
        if ( matDef == null ) {
            
            throw new BizException("제품 정보가 존재하지 않습니다.");
            
        }
        
        if ( matDef.getLotQty() == null || matDef.getLotQty() == 0 ) {
            
            throw new BizException("제품 정보에 LOT 기준 수량이 존재하지 않습니다.");
            
        }
        
        CreateLotIn inputCreate = new CreateLotIn();
        ValueUtils.populate(input, inputCreate);
        
        inputCreate.setFlowCode(ordSts.getFlowCode());
        
        inputCreate.setQty(matDef.getLotQty());
                
        BeanUtils.get(CreateLot.class).create(inputCreate);
        
        query = new Query();
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        query.setFilter("lotId", input.getLotId());
        
        return DbUtils.select(MWipLotSts.class, query);
        
    }
    
    
}
