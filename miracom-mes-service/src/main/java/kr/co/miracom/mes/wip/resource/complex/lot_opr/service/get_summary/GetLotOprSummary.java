package kr.co.miracom.mes.wip.resource.complex.lot_opr.service.get_summary;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import kr.co.miracom.dbist.dml.Order;
import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.exception.BizException;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.BeanUtils;
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
import kr.co.miracom.mes.wip.resource.complex.lot_opr.service.load_list.LoadLotOprList;
import kr.co.miracom.mes.wip.resource.complex.lot_opr.service.load_list.LoadLotOprListIn;

/**
 * Get List Service: Lot_opr
 * @author gom
 * @since 2018. 06. 25.
 */
public class GetLotOprSummary {
    public GetLotOprSummaryOut getSummary(GetLotOprSummaryIn input) throws Exception {
    
        SummaryInfo summaryInfo = getSummaryInfo(input);
        
        GetLotOprSummaryOut output = new GetLotOprSummaryOut();
        output.setDayOrdQty(summaryInfo.getDayOrdQty());
        output.setDayOutQty(summaryInfo.getDayOutQty());
        output.setDayRemainQty(summaryInfo.getDayOrdQty() - summaryInfo.getDayOutQty());
        output.setDayGoodQty(summaryInfo.getDayGoodQty());
        output.setDayLossQty(summaryInfo.getDayLossQty());
        output.setDayOperOutQty(summaryInfo.getDayOperOutQty());
        output.setDayEquipOutQty(summaryInfo.getDayEquipOutQty());
        
        if ( input.getOperCode() != null && !"".equals(input.getOperCode())) {
            
            if ( input.isInvMatFlag() ) {
                //BackFlush 자재 목록을 조회 한다.
                //output.setInvMatList(getBFMatList(input));
                
                LoadLotOprListIn inputMat = new LoadLotOprListIn();
                ValueUtils.populate(input, inputMat);
                output.setList(BeanUtils.get(LoadLotOprList.class).getLoadMatList(inputMat).getList());
                                
            }
                      
        }
        return output;
    }
    
    private SummaryInfo getSummaryInfo(GetLotOprSummaryIn input) throws Exception {
     
        SummaryInfo summaryInfo = new SummaryInfo();
        
        Calendar now = Calendar.getInstance();
        StringBuffer nowDate = new StringBuffer(); 
        nowDate.append(now.get(Calendar.YEAR))
               .append(now.get(Calendar.MONTH) < 9 ? "0" : "").append(now.get(Calendar.MONTH) + 1)
               .append(now.get(Calendar.DATE) < 10 ? "0" : "").append(now.get(Calendar.DATE));
        
        Query query = new Query();
        //query.setFilter("workDate", ValueUtils.toDateString(ValueUtils.getDate(), ValueUtils.DATEPATTERN_DATE_SHORT));
        query.setFilter("ordDate", nowDate.toString());
        query.setFilter("lineCode", input.getLineCode());
        
        List<MWipOrdSts> ordStsList = DbUtils.selectList(MWipOrdSts.class, query);
        
        Double planQty = 0.0;
        
        for ( MWipOrdSts ordSts : ordStsList ) {
            
            planQty += ordSts.getOrdQty();
            
        }
        
        summaryInfo.setDayOrdQty(planQty);
        
        query = new Query();
        //query.setFilter("workDate", ValueUtils.toDateString(ValueUtils.getDate(), ValueUtils.DATEPATTERN_DATE_SHORT));
        query.setFilter("workDate", nowDate.toString());
        query.setFilter("lineCode", input.getLineCode());
        
        List<MWipLinOph> linOphList = DbUtils.selectList(MWipLinOph.class, query);
        
        Double acrsQty = 0.0;
        Double goodQty = 0.0;
        Double lossQty = 0.0;
        Double operAcrsQty = 0.0;
        Double equipQty = 0.0;
        
        for ( MWipLinOph linOph : linOphList ) {
            
            acrsQty += linOph.getOutQty();
            goodQty += linOph.getGoodQty();
            lossQty += linOph.getLossQty();
            
            if ( input.getOperCode() != null && linOph.getOperCode().equals(input.getOperCode()) ) {
                
                operAcrsQty += linOph.getOutQty();
                
            }
            
            if ( input.getEquipCode() != null && !"".equals(linOph.getEquipCode()) && linOph.getEquipCode().equals(input.getEquipCode()) ) {
                
                equipQty += linOph.getOutQty();
                
            }
            
            
        }
        
        summaryInfo.setDayOutQty(acrsQty);
        summaryInfo.setDayGoodQty(goodQty);
        summaryInfo.setDayLossQty(lossQty);
        summaryInfo.setDayOperOutQty(operAcrsQty);
        summaryInfo.setDayEquipOutQty(equipQty);
        
        return summaryInfo;
        
    }
    
    private List<InputBFMat> getBFMatList(GetLotOprSummaryIn input) throws Exception {
        
        Query query = new Query();
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        query.setFilter("orderNo", input.getOrderNo());
        //query.setFilter("operCode", input.getOperCode());
        
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
        
        return bfList;
        
    }
    
    
}
