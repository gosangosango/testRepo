package kr.co.miracom.mes.wip.resource.simple.lot.service.end;

import java.time.ZonedDateTime;

import io.swagger.annotations.ApiModelProperty;
import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.exception.BizException;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.bas.resource.simple.code_data.model.CodeData;
import kr.co.miracom.mes.bas.resource.simple.code_data.service.CodeDataService;
import kr.co.miracom.mes.bas.resource.simple.code_data.service.get_list.GetCodeDataList;
import kr.co.miracom.mes.bas.resource.simple.code_data.service.get_list.GetCodeDataListIn;
import kr.co.miracom.mes.ras.model.MRasEqpDef;
import kr.co.miracom.mes.wip.model.MWipFlwDef;
import kr.co.miracom.mes.wip.model.MWipFlwOpr;
import kr.co.miracom.mes.wip.model.MWipLinDef;
import kr.co.miracom.mes.wip.model.MWipLotHis;
import kr.co.miracom.mes.wip.model.MWipLotSts;
import kr.co.miracom.mes.wip.model.MWipMatDef;
import kr.co.miracom.mes.wip.model.MWipMatFlw;
import kr.co.miracom.mes.wip.model.MWipOprDef;
import kr.co.miracom.mes.wip.model.MWipOrdSts;

public class EndLot {
    
    public EndLotOut end(EndLotIn input) throws Exception {
        
        ValueUtils.checkNotEmpty(input, "lotId", "operCode", "matCode", "goodType");
        
        MWipLotSts lotSts = getLotStatus(input);
        ZonedDateTime tranTime;
        
        if ( input.getTranTime() == null ) {
        
             tranTime = ValueUtils.getZonedDateTime();
        
        } else {
            
            tranTime = input.getTranTime();
            
        }
        
        //기준정보를 체크한다.
        checkSetupData(input, lotSts);
    
        //LOT 상태를 점검한다.
        checkEndLotStatus(input, lotSts);
        
        //GOOD_TYPE = 'G'
        if ( "G".equals(input.getGoodType()) ) {
            
            processGoodLot(input, lotSts, tranTime);

            updateOrder(input, lotSts);
            
        //GOOD_TYPE = 'L'
        } else if ( "L".equals(input.getGoodType())){
            
            lotSts.setLastTranCode("HOLD");
            lotSts.setHoldFlag(true);
            
        } else {
            
            throw new BizException("Good Type 미지정");
            
        }
        
        lotSts.setLastTranTime(tranTime);
        
        DbUtils.update(lotSts);
        
        //LOT 이력을 생성한다.
        createLotHis(lotSts);
        
        EndLotOut out = new EndLotOut();
        out.setLotId(lotSts.getLotId());
        
        return out;
    }
    
    private void updateOrder(EndLotIn input, MWipLotSts lotSts) throws Exception {
        
        Query query = new Query();
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        query.setFilter("orderNo", lotSts.getOrderNo());
                        
        MWipOrdSts ordSts = DbUtils.select(MWipOrdSts.class, query);
        
        if ( ordSts == null ) {
            
            throw new BizException("작업지시가 존재하지 않습니다.");
            
        }
        
        Double outQty = ordSts.getOrdOutQty() + input.getQty();
        
        GetCodeDataListIn inputGcm = new GetCodeDataListIn();
        inputGcm.setTableName("ORD_QTY_OVERBALANCE_FLAG");
        
        GetListOut output = BeanUtils.get(GetCodeDataList.class).getList(inputGcm);
        
        if ( output.getList() != null ) {
            
            CodeData code = (CodeData) output.getList().get(0);
            if ( !"Y".equals(code.getData1()) ) {
                
                if ( ordSts.getOrdQty() < outQty ) {
                    
                    throw new BizException("MIC-00049");
                    
                }

            }
            
        }
        
        if ( ordSts.getOrdQty() == outQty ) {
        
            inputGcm = new GetCodeDataListIn();
            inputGcm.setTableName("ORDER_AUTO_CLOSE_FLAG");
            
            output = BeanUtils.get(GetCodeDataList.class).getList(inputGcm);
            
            if ( output.getList() != null ) {
                
                CodeData code = (CodeData) output.getList().get(0);
                if ( "Y".equals(code.getData1()) ) {
                    
                    ordSts.setOrdStatus("CLOSE");
    
                }
                
            }
        }
        
        DbUtils.update(ordSts);
        
    }
    
    private void checkSetupData(EndLotIn input, MWipLotSts lotSts) throws Exception {
        
        Query query = new Query();
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        query.setFilter("lineCode", lotSts.getLineCode());
        
        MWipLinDef linDef = DbUtils.select(MWipLinDef.class, query);
        
        if ( linDef == null ) {
            
            throw new BizException("MIC-00017");
            
        }
        
        query = new Query();
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        query.setFilter("matCode", input.getMatCode());
        
        MWipMatDef matDef = DbUtils.select(MWipMatDef.class, query);
        
        if ( matDef == null ) {
            
            throw new BizException("MIC-00048");
            
        }
        
        if ( lotSts.getFlowCode() == null || "".equals(lotSts.getFlowCode()) ) {
            
            throw new BizException("Lot에 플로우가 존재하지 않습니다.");
            
        }
        
        query= new Query();
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        query.setFilter("flowCode", lotSts.getFlowCode());
        
        MWipFlwDef flwDef = DbUtils.select(MWipFlwDef.class, query);
        
        if ( flwDef == null ) {
            
            throw new BizException("MIC-00016");
            
        }
        
        if ( input.getEquipCode() != null && !"".equals(input.getEquipCode()) ) {
            
            query= new Query();
            query.setFilter("factoryCode", AuthUtils.getFactoryCode());
            query.setFilter("equipCode", input.getEquipCode());
            
            MRasEqpDef eqpDef = DbUtils.select(MRasEqpDef.class, query);
            
            if ( eqpDef == null ) {
                
                throw new BizException("MIC-00025");
                
            }   
            
            
        }
        
    }
    
    private MWipLotSts getLotStatus(EndLotIn input) throws Exception {
       
        Query query = new Query();
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        query.setFilter("lotId", input.getLotId());
        
        MWipLotSts lotSts = DbUtils.select(MWipLotSts.class, query);
    
        if ( lotSts.isProdCompleteFlag() ) {
            
            throw new BizException("생산이 완료된 LOT 입니다.");
            
        }
        
        return lotSts;
        
    }
    
    private void checkEndLotStatus(EndLotIn input, MWipLotSts lotSts) throws Exception {
        
        if ( input.getEquipCode() != null && !"".equals(input.getEquipCode())) {
            
            //설비코드가 있을 경우 MRASEQPOPR에서 해당 공정에서 생산 가능한지 점검한다.
            
            
        }
        
        //LOT의 현재 공정과 입력 공정이 같은지 확인 한다.
        if ( lotSts.getFlowCode() == null || "".equals(lotSts.getFlowCode()) ) {
            
            throw new BizException("LOT에 플로우가 지정되어 있지 않습니다.");
            
        }
        
        //LOT의 현재 공정과 입력 공정이 같은지 확인 한다.
        if ( !lotSts.getOperCode().equals(input.getOperCode())) {
            
            throw new BizException("LOT의 공정과 입력 공정이 다릅니다.");
            
        }
        
        //MFO에 존재하는지 확인한다.
        Query query = new Query();
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        query.setFilter("lineCode", lotSts.getLineCode());
        query.setFilter("matCode", lotSts.getMatCode());
        query.setFilter("flowCode", lotSts.getFlowCode());
        
        MWipMatFlw matFlw = DbUtils.select(MWipMatFlw.class, query);
        
        if ( matFlw == null ) {
            
            query = new Query();
            query.setFilter("factoryCode", AuthUtils.getFactoryCode());
            query.setFilter("matCode", lotSts.getMatCode());
            query.setFilter("flowCode", lotSts.getFlowCode());
            
            matFlw = DbUtils.select(MWipMatFlw.class, query);
            
            if ( matFlw == null ) {
                
                throw new BizException("MIC-00045");
                
            }
            
        }
        
    }
    
    private void processGoodLot(EndLotIn input, MWipLotSts lotSts, ZonedDateTime tranTime) throws Exception {
    
        lotSts.setEndTime(ValueUtils.getZonedDateTime());
        lotSts.setStartFlag(false);
        lotSts.setLotStatus("WAIT");
        
        Query query = new Query();
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        query.setFilter("operCode", input.getOperCode());
        
        MWipOprDef oprDef = DbUtils.select(MWipOprDef.class, query);
        
        if ( oprDef == null ) {
            
            throw new BizException("존재하지 않는 공정입니다.");
            
        }
        
        query = new Query();
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        query.setFilter("flowCode", lotSts.getFlowCode());
        query.setFilter("operCode", input.getOperCode());
        
        MWipFlwOpr flwOpr = DbUtils.select(MWipFlwOpr.class, query);
        
        if ( flwOpr == null ) {
            
            throw new BizException("해당 플로우에 존재하지 않는 공정입니다.");
            
        }
        
        String nextOper = flwOpr.getNextOperCode();
        
        if ( input.isLastFlag() ) {
            
            nextOper = input.getOperCode();
            lotSts.setProdCompleteFlag(true);
            
        }
                
        if ( !oprDef.isPushPullFlag() ) {
             
             lotSts.setOperCode(nextOper);
             lotSts.setEndEquipCode("");
             lotSts.setEndFlag(false);
             
         } else {
        
             lotSts.setEndEquipCode(input.getEquipCode());
             lotSts.setEndFlag(true);
             
         }
         
         lotSts.setStartFlag(false);
         lotSts.setEndTime(tranTime);
         lotSts.setLastTranCode("END");
         
    }
    
    private void createLotHis(MWipLotSts lotSts) throws Exception {
        
        MWipLotHis lotHis = new MWipLotHis();
        
        Query query = new Query(0, 1);
        query.addFilter("factoryCode", AuthUtils.getFactoryCode());
        query.addFilter("lotId", lotSts.getLotId());
        query.addOrder("histSeq", false);
        
        MWipLotHis hist = DbUtils.select(MWipLotHis.class, query);
        
        ValueUtils.populate(lotSts, lotHis);
        
        lotHis.setTranCode(lotSts.getLastTranCode());
        lotHis.setTranTime(lotSts.getLastTranTime());
        
        if ( hist == null ) {
            
            lotHis.setHistSeq(1);
            
        } else {
            
            lotHis.setHistSeq(hist.getHistSeq() + 1);
            
        }
        
        DbUtils.insert(lotHis);
        
    }
    
}
