package kr.co.miracom.mes.wip.resource.complex.lot_opr.service.load_list;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import kr.co.miracom.dbist.dml.Order;
import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.exception.BizException;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.inv.model.MInvLotSts;
import kr.co.miracom.mes.wip.model.MWipInvLod;
import kr.co.miracom.mes.wip.model.MWipLinOph;
import kr.co.miracom.mes.wip.model.MWipMatDef;
import kr.co.miracom.mes.wip.model.MWipOrdBom;
import kr.co.miracom.mes.wip.model.MWipOrdSts;
import kr.co.miracom.mes.wip.resource.complex.lot_end.model.InputBFMat;
import kr.co.miracom.mes.wip.resource.complex.lot_opr.model.SummaryInfo;

/**
 * Get List Service: Lot_opr
 * @author gom
 * @since 2018. 06. 25.
 */
public class LoadLotOprList {
    public LoadLotOprListOut getLoadMatList(LoadLotOprListIn input) throws Exception {
    
        Query query = new Query();
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        query.setFilter("orderNo", input.getOrderNo());
        
        if ( input.getOperCode() != null && !"".equals(input.getOperCode()) ) {
            
            query.setFilter("operCode", input.getOperCode());
            
        }
        
        List<MWipOrdBom> ordBomList = DbUtils.selectList(MWipOrdBom.class, query);
        
        if ( ordBomList == null || ordBomList.size() == 0 ) {
            
            throw new BizException("MIC-00041", "작업지시 BOM 정보가 없습니다.");
            
        }
        
        List<InputBFMat> bfList = new ArrayList<InputBFMat>();
        String matCode = "";
        MWipMatDef matDef = new MWipMatDef();
        InputBFMat bfMat;
        for ( MWipOrdBom ordBom : ordBomList ) {
                
            if ( !ordBom.getChildMatCode().equals(matCode) ) {
                query = new Query();
                query.setFilter("factoryCode", AuthUtils.getFactoryCode());
                query.setFilter("matCode", ordBom.getMatCode());
                
                
                matDef = DbUtils.select(MWipMatDef.class, query);
                
                if ( matDef == null ) {
                 
                    throw new BizException("존재하지 않는 자재 코드입니다.");
                    
                }
                
                matCode = ordBom.getChildMatCode();
                    
            }
            
            query = new Query();
            query.setFilter("factoryCode", AuthUtils.getFactoryCode());
            query.setFilter("matCode", matCode);
            
            if ( input.getEquipCode() != null && !"".equals(input.getEquipCode()) ) {
                
                query.setFilter("equipCode", input.getEquipCode());
                
            }
            
            query.addOrder(new Order("matCode", true), new Order("equipCode", true));
            
            List<MWipInvLod> lodList = DbUtils.selectList(MWipInvLod.class, query);
            
            bfMat = new InputBFMat();
            ValueUtils.populate(ordBom, bfMat);
            bfMat.setMatShortDesc(matDef.getMatShortDesc());
            bfMat.setQty(0.0);
            
            if ( lodList == null || lodList.size() == 0 ) {
                
                bfList.add(bfMat);
                
            } else {
                
                for ( MWipInvLod invLod : lodList ) {
                    
                    //bfMat.setInvLotId(invLod.getInvLotId());
                
                    query = new Query();
                    query.setFilter("factoryCode", AuthUtils.getFactoryCode());
                    query.setFilter("invLotId", invLod.getInvLotId());
                    
                    MInvLotSts invLot = DbUtils.select(MInvLotSts.class, query);
                    
                    if ( invLot == null ) {
                        
                        throw new BizException("자재가 존재하지 않습니다.");
                        
                    }
                    
                    bfMat.setQty(bfMat.getQty() + invLot.getQty());
                    
                }
                
                bfList.add(bfMat);
                
            }
        }
        
        LoadLotOprListOut output = new LoadLotOprListOut();
        output.setList(bfList);
        
        return output;
    }
    
}
