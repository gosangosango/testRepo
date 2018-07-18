package kr.co.miracom.mes.wip.resource.complex.lot_end.service.deduct_inv;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiModelProperty;
import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.exception.BizException;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.inv.model.MInvLotHis;
import kr.co.miracom.mes.inv.model.MInvLotIss;
import kr.co.miracom.mes.inv.model.MInvLotSts;
import kr.co.miracom.mes.inv.util.InvUtils;
import kr.co.miracom.mes.wip.model.MWipInvLod;
import kr.co.miracom.mes.wip.model.MWipLotSts;
import kr.co.miracom.mes.wip.model.MWipOrdBom;
import kr.co.miracom.mes.wip.resource.complex.lot_end.model.InputMat;

/**
 * Get Service: LotEnd
 * @author gom
 * @since 2018. 06. 25.
 */
public class DeductLotEndInv {
    public DeductLotEndInvOut deductInv(DeductLotEndInvIn input) throws Exception {
        
        ValueUtils.checkNotEmpty(input, "lotId", "orderNo", "lineCode", "operCode");
        
        ZonedDateTime tranTime;
        
        if ( input.getTranTime() == null ) {
        
             tranTime = ValueUtils.getZonedDateTime();
        
        } else {
            
            tranTime = input.getTranTime();
            
        }
        
        Query query = new Query();
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        query.setFilter("lotId", input.getLotId());
        
        MWipLotSts lotSts = DbUtils.select(MWipLotSts.class, query);
        
        if ( lotSts == null ) {
            
            throw new BizException("LOT이 존재하지 않습니다.");
            
        }
        
        if ( input.getList() != null && input.getList().size() > 0 ) {
            //관리 자재 차감
            deduct(input, lotSts, tranTime, input.getList());
        
        }
        /*
        2. B/F품 투입 자재 정보 리스트 확인
        1) MWIPORDBOM에 ORDER_NO에 해당하는 LIST를 조회한다.
          ㄴ LIST가 존재하지 않으면 에러메시지를 반환한다.(MIC-00041)
        2) 조회된 하위 품번이 MWIPINVLOD에 투입되어 있는지 조회한다.
          ㄴ MWIPINVLOD에 MAT_CODE=MWIPORDBOM.CHILD_MAT_CODE, LINE_CODE, OPER_CODE, EQUIP_CODE에 해당하는 정보 존재 여부 체크(LOAD_TIME ASC)
          ㄴ 존재 하지 않을경우 MAT_CODE=MWIPORDBOM.CHILD_MAT_CODE, LINE_CODE, OPER_CODE에 해당하는 정보 존재 여부 체크 (LOAD_TIME ASC)
          ㄴ 존재하지 않고 LAST_FLAG = 'Y'인 경우 MAT_CODE=MWIPORDBOM.CHILD_MAT_CODE, LINE_CODE에 OPER_CODE =' '에 해당하는 정보 존재 여부 체크(LOAD_TIME ASC)
        3) MWIPINVLOD에 존재시 MINVLOTSTS에 INV_LOT_ID = MWIPINVLOD.LABEL_ID에 해당하는 정보를 조회한다.
        4) 자재 LOT ID별 자재 출고/출하이력 생성 서비스 호출(기능ID : 4004000311)
           ㄴ PROD_LOT_ID = LOT_ID
           ㄴ 자재필요소요량보다 MINVLOTSTS.QTY수량이 작은 경우  동일한 품목의 다음 LABEL ID를 차아서 차감한다.
        5) MINVLOTSTS.QTY수량이 0인경우 투입해제를 서비스를 호출한다.(기능ID:4005000105)
        */
        query = new Query();
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        query.setFilter("orderNo", input.getOrderNo());
        
        if ( input.isLastFlag() ) {
            
            query.setFilter("operCode", "is", null);
            
        } else {
            
            query.setFilter("operCode", input.getOperCode());
            
        }
        
        List<MWipOrdBom> ordBomList = DbUtils.selectList(MWipOrdBom.class, query);
        
        List<InputMat> list = new ArrayList<InputMat>();
        List<MWipInvLod> invLodList;
        InputMat inputMat;
        for ( MWipOrdBom ordBom : ordBomList ) {
            
            query = new Query();
            query.setFilter("factoryCode", AuthUtils.getFactoryCode());
            query.setFilter("matCode", ordBom.getChildMatCode());
            query.setFilter("lineCode", input.getLineCode());
            
            if ( input.isLastFlag() ) {
                
                query.setFilter("operCode", "is", null);
                query.setFilter("equipCode", "is", null);
                
            } else {
                
                query.setFilter("operCode", input.getOperCode());
                if ( input.getEquipCode() != null && !"".equals(input.getEquipCode())) {
                    
                    query.setFilter("equipCode", input.getOperCode());
                    
                } else {
                    
                    query.setFilter("equipCode", "is", null);
                    
                }
                
            }
            
            invLodList = DbUtils.selectList(MWipInvLod.class, query);
            
            for ( MWipInvLod invLod : invLodList ) {
                
                inputMat = new InputMat();
                ValueUtils.populate(invLod, inputMat);
                
                list.add(inputMat);
                
            }
            
        }
        
        if ( list.size() > 0 ) {
            //관리 자재 차감
            deduct(input, lotSts, tranTime, list);
        
        }
        
        
        DeductLotEndInvOut output = new DeductLotEndInvOut();
        
        return output;
    }
    
    private void deduct(DeductLotEndInvIn input, MWipLotSts lotSts, ZonedDateTime tranTime, List<InputMat> list) throws Exception {
        
        Map<String, Double> qtyCheckMap = new HashMap<String, Double>();
        
        Query query;
        MInvLotSts invLot;
        Double qty;
        for ( InputMat inputMat : list ) {
            
            query = new Query();
            query.setFilter("factoryCode", AuthUtils.getFactoryCode());
            query.setFilter("invLotId", inputMat.getInvLotId());
            
            invLot = DbUtils.select(MInvLotSts.class, query);
            
            if ( invLot.isLotDelFlag() ) {
                
                throw new BizException("자재 LOT이 삭제 되었습니다.");
                
            }
            
            if ( invLot.getQty() == 0.0 ) {
                
                throw new BizException("자재 LOT의 수량이 0입니다.");
                
            }
            
            inputMat.setQty(invLot.getQty());
            if ( qtyCheckMap.get(inputMat.getMatCode()) != null ){
                
                qty = qtyCheckMap.get(inputMat.getMatCode()) + invLot.getQty();
                
            } else {
                
                qty = invLot.getQty();
                
            }
            
            qtyCheckMap.put(inputMat.getMatCode(), qty);
            
        }
        
        MWipOrdBom ordBom;
        Map<String, Double> deductMap = new HashMap<String, Double>(); 
        for ( String key : qtyCheckMap.keySet() ) {
        
            query = new Query();
            query.setFilter("factoryCode", AuthUtils.getFactoryCode());
            query.setFilter("orderNo", input.getOrderNo());
            query.setFilter("matCode", lotSts.getMatCode());
            query.setFilter("childMatCode", key);
            
            ordBom = DbUtils.select(MWipOrdBom.class, query);
            
            if ( ordBom == null ) {
                
                throw new BizException("작업지시 Bom에 자재 정보가 존재하지 않습니다.");
                
            }
            
            if ( ordBom.getOperCode() != null && !"".equals(ordBom.getOperCode()) && !ordBom.getOperCode().equals(input.getOperCode())) {
                
                throw new BizException("해당 공정에 투입가능한 자재가 아닙니다.");
                
            }
            
            
            if ( qtyCheckMap.get(key) < ordBom.getComponentQty() ) {
                
                throw new BizException("MIC-00051");
                
            }
            
            deductMap.put(key, ordBom.getComponentQty());
            
        }
        
        Double deductQty = 0.0;
        for ( InputMat inputMat : list ) {
            
            deductQty = deductMap.get(inputMat.getMatCode());
            
            if ( deductQty == 0.0 ) {
                
                continue;
                
            } else if ( deductQty < inputMat.getQty() ) {
                
                deductMap.put(inputMat.getMatCode(), 0.0);
                issInvLot(input, lotSts, inputMat, tranTime, deductQty);
                
            } else {
                
                deductMap.put(inputMat.getMatCode(), deductQty - inputMat.getQty());
                issInvLot(input, lotSts, inputMat, tranTime, inputMat.getQty());
                
            }
            
        }
            
        
        
    }
    
    private void issInvLot(DeductLotEndInvIn input, MWipLotSts lotSts, InputMat inputMat, ZonedDateTime tranTime, Double deductQty) throws Exception {
        
        Query query = new Query();
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        query.setFilter("invLotId", inputMat.getInvLotId());
        
        MInvLotSts invLot = DbUtils.select(MInvLotSts.class, query);
                        
        invLot.setQty(invLot.getQty() - deductQty);
        invLot.setLastTranCode("ASSY");
        invLot.setLastTranTime(tranTime);
        
        DbUtils.update(invLot);
        MInvLotHis invHis = InvUtils.insertInvLotHistory("ASSY", invLot);
        
        MInvLotIss lotIss = new MInvLotIss();
        ValueUtils.populate(invLot, lotIss);
        lotIss.setHistSeq(invHis.getHistSeq());
        lotIss.setOperCode(input.getOperCode());
        lotIss.setTranCode(invLot.getLastTranCode());
        lotIss.setTranTime(invLot.getLastTranTime());
        lotIss.setTranQty(deductQty);
        lotIss.setProdLotId(input.getLotId());
        lotIss.setLineCode(lotSts.getLineCode());
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); 
        Date now = Calendar.getInstance().getTime(); 
        String nowDate = sdf.format(now);
        
        lotIss.setInvDate(nowDate);
        
        DbUtils.insert(lotIss);
        
    }
            
}
