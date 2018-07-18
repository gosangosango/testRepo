package kr.co.miracom.mes.wip.resource.complex.lot_end.service.generate_summary;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.exception.BizException;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.wip.model.MWipLinOph;
import kr.co.miracom.mes.wip.model.MWipLotSts;
import kr.co.miracom.mes.wip.model.MWipOrdSts;

/**
 * Get Service: LotEnd
 * @author gom
 * @since 2018. 06. 25.
 */
public class GenerateLotEndSummary {
    public GenerateLotEndSummaryOut generateSummary(GenerateLotEndSummaryIn input) throws Exception {
        
        ValueUtils.checkNotEmpty(input, "lotId", "orderNo", "lineCode", "operCode", "goodType");
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss"); 
        Date now = Calendar.getInstance().getTime(); 
        
        String dateString = sdf.format(now);
        
        sdf = new SimpleDateFormat("yyyyMMdd");
        String nowDate = sdf.format(now);
        
        
        Query query = new Query();
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        query.setFilter("lotId", input.getLotId());
        
        MWipLotSts lotSts = DbUtils.select(MWipLotSts.class, query);
        
        if ( lotSts == null ) {
            
            throw new BizException("LOT이 존재하지 않습니다.");
            
        }

        query = new Query();
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        query.setFilter("orderNo", lotSts.getOrderNo());
        
        MWipOrdSts ordSts = DbUtils.select(MWipOrdSts.class, query);
        
        if ( ordSts == null ) {
            
            throw new BizException("작업지시가 존재하지 않습니다.");
            
        }
        
        query = new Query();
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        query.setFilter("workDate", nowDate);
        query.setFilter("lineCode", lotSts.getLineCode());
        query.setFilter("orderNo", lotSts.getOrderNo());
        query.setFilter("operCode", input.getOperCode());
        
        if ( input.getEquipCode() != null && "".equals(input.getEquipCode())) {
            
            query.setFilter("equipCode", input.getEquipCode());
            
        } 
        
        MWipLinOph linOph = DbUtils.select(MWipLinOph.class, query); 
        
        boolean insertFlag = false;
        if ( linOph == null ) {
            
            linOph = new MWipLinOph();
            linOph.setFactoryCode(AuthUtils.getFactoryCode());
            linOph.setWorkDate(nowDate);
            linOph.setWorkShift(null);
            linOph.setLineCode(lotSts.getLineCode());
            linOph.setOperCode(input.getOperCode());
            
            if ( input.getEquipCode() != null && "".equals(input.getEquipCode()) ) {
                
                linOph.setEquipCode(input.getEquipCode());
                
            } else {
                
                linOph.setEquipCode(null);
                
            }
            linOph.setOrderNo(lotSts.getOrderNo());
            linOph.setMatCode(ordSts.getMatCode());
            linOph.setStartTimeStr(dateString);
            
            linOph.setOutQty(0.0);
            linOph.setGoodQty(0.0);
            linOph.setLossQty(0.0);
            
            insertFlag = true;
        }  
        
        linOph.setEndTimeStr(dateString);
        linOph.setOutQty(linOph.getOutQty() + input.getQty());
        
        if ( input.getGoodType().equals("G") ) {
            
            linOph.setGoodQty(linOph.getGoodQty() + input.getQty());
            
        } 
        
        if ( insertFlag ) {
            
            DbUtils.insert(linOph);
            
        } else {
            
            DbUtils.update(linOph);
            
        }
        
        GenerateLotEndSummaryOut output = new GenerateLotEndSummaryOut();
        
        return output;
    }
            
}
