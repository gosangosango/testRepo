package kr.co.miracom.mes.wip.resource.complex.lot_end.service.post;

import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.exception.BizException;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.inv.model.MInvLblDef;
import kr.co.miracom.mes.wip.model.MWipFlwDef;
import kr.co.miracom.mes.wip.model.MWipLotSts;
import kr.co.miracom.mes.wip.resource.complex.lot_end.service.deduct_inv.DeductLotEndInv;
import kr.co.miracom.mes.wip.resource.complex.lot_end.service.deduct_inv.DeductLotEndInvIn;
import kr.co.miracom.mes.wip.resource.complex.lot_end.service.generate_summary.GenerateLotEndSummary;
import kr.co.miracom.mes.wip.resource.complex.lot_end.service.generate_summary.GenerateLotEndSummaryIn;
import kr.co.miracom.mes.wip.resource.simple.lot.service.end.EndLot;
import kr.co.miracom.mes.wip.resource.simple.lot.service.end.EndLotIn;

/**
 * Post Service: LotEnd
 * @author gom
 * @since 2018. 06. 25.
 */
public class PostLotEnd {
    public PostLotEndOut post(PostLotEndIn input) throws Exception {
        
        ValueUtils.checkNotEmpty(input, "lotId", "operCode", "matCode", "goodType");
        
        Query query = new Query();
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        query.setFilter("lotId", input.getLotId());
        
        MWipLotSts lotSts = DbUtils.select(MWipLotSts.class, query);
        
        if ( lotSts == null ) {
            
            throw new BizException("LOT이 존재하지 않습니다.");
            
        }
        
        query= new Query();
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        query.setFilter("flowCode", lotSts.getFlowCode());
        
        MWipFlwDef flwDef = DbUtils.select(MWipFlwDef.class, query);
        
        if ( flwDef == null ) {
            
            throw new BizException("MIC-00016");
            
        }
        
        boolean lastFlag = false;
        
        if ( flwDef.getLastOperCode().equals(input.getOperCode())) {
            
            lastFlag = true;
            
        }
        
        
        EndLotIn inputEnd = new EndLotIn();
        ValueUtils.populate(input, inputEnd);
        
        inputEnd.setTranTime(ValueUtils.getZonedDateTime());
        
        DeductLotEndInvIn inputDeduction = new DeductLotEndInvIn();
        inputDeduction.setLastFlag(lastFlag);
        
        ValueUtils.populate(input, inputDeduction);
        
        BeanUtils.get(DeductLotEndInv.class).deductInv(inputDeduction);
        
        
        GenerateLotEndSummaryIn inputSummary = new GenerateLotEndSummaryIn();
        ValueUtils.populate(input, inputSummary);
        inputSummary.setLastFlag(lastFlag);
        inputSummary.setTranTime(ValueUtils.getZonedDateTime());
        
        //집계 정보를 생성한다.
        BeanUtils.get(GenerateLotEndSummary.class).generateSummary(inputSummary);
        
        //LOT END를 처리 한다.
        BeanUtils.get(EndLot.class).end(inputEnd);
        
        if ( lastFlag ) {
            
            query = new Query();
            query.setFilter("factoryCode", AuthUtils.getFactoryCode());
            query.setFilter("labelId", input.getLotId());
            query.setFilter("labelType", "LOT");
            
            MInvLblDef lblDef = DbUtils.select(MInvLblDef.class, query);
            
            if ( lblDef != null ) {
                
                lblDef.setProdCompleteFlag(true);
                lblDef.setLabelStatus("PROCESS");
                
                DbUtils.update(lblDef);
                
            }
            
        }
        
        
        PostLotEndOut output = new PostLotEndOut();
        output.setLotId(input.getLotId());
        
        return output;
    }
}
