package kr.co.miracom.mes.wip.resource.simple.lot.service.request_inspection;

import java.util.List;

import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.exception.BizException;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.bas.resource.simple.code_data.model.CodeData;
import kr.co.miracom.mes.bas.resource.simple.code_data.service.get_list.GetCodeDataList;
import kr.co.miracom.mes.bas.resource.simple.code_data.service.get_list.GetCodeDataListIn;
import kr.co.miracom.mes.inv.model.MInvLblDef;
import kr.co.miracom.mes.inv.model.MInvLblLot;
import kr.co.miracom.mes.qcm.model.MQcmReqDef;
import kr.co.miracom.mes.qcm.model.MQcmReqDtl;
import kr.co.miracom.mes.wip.resource.simple.lot.model.ReqLot;

public class RequestInspectionLot {
    
    public RequestInspectionLotOut requestInspection(RequestInspectionLotIn input) throws Exception {
        
        ValueUtils.checkNotEmpty(input, "inspType", "inspReqQty", "matCode", "inspNo", "list");
        
        MQcmReqDef reqDef = new MQcmReqDef();
        reqDef.setFactoryCode(AuthUtils.getFactoryCode());
        reqDef.setInspNo(input.getInspNo());
        reqDef.setInspType(input.getInspType());
        reqDef.setInspStatus("REQUEST");
        reqDef.setInspReqDate(ValueUtils.getDateStr());
        reqDef.setInspReqTime(ValueUtils.getZonedDateTime());
        reqDef.setInspReqUserId(AuthUtils.getUserId());
        reqDef.setInspReqQty(input.getInspReqQty());
        reqDef.setMatCode(input.getMatCode());
        
        DbUtils.insert(reqDef);
        
        MQcmReqDtl reqDtl;
        int seq = 1;
        MInvLblDef lblDef;
        for ( ReqLot reqLot : input.getList() ) {
            
            lblDef = checkReqStatus(reqLot);
            
            reqDtl = new MQcmReqDtl();
            reqDtl.setFactoryCode(AuthUtils.getFactoryCode());
            reqDtl.setInspNo(input.getInspNo());
            reqDtl.setInspSeq(seq);
            reqDtl.setMatCode(reqLot.getMatCode());
            reqDtl.setInspReqQty(reqLot.getInspReqQty());
            reqDtl.setLabelId(reqLot.getLabelId());
            
            DbUtils.update(reqDtl);
            
            lblDef.setInspNo(input.getInspNo());
            
            DbUtils.update(lblDef);
            
            seq++;
            
        }
        
        RequestInspectionLotOut output = new RequestInspectionLotOut();
        
        return output;
    }
    
    private MInvLblDef checkReqStatus(ReqLot reqLot) throws Exception {
        
        Query query = new Query();
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        query.setFilter("labelId", reqLot.getLabelId());
        
        MInvLblDef lblDef = DbUtils.select(MInvLblDef.class, query);
        
        if ( ValueUtils.isEmpty(lblDef) ) {
            
            throw new BizException("라벨 정의 정보가 존재하지 않습니다.");
            
        }

        if ( "LOT".equals(lblDef.getLabelType()) ) {
            
            throw new BizException("LOT은 제품 입고 검사의뢰를 할 수 없습니다.");
            
        }
        
        query = new Query(0, 1);
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        query.setFilter("labelId", reqLot.getLabelId());
        query.addOrder("inspNo", false);
        
        MQcmReqDtl reqDtl = DbUtils.select(MQcmReqDtl.class, query);
        
        if ( !ValueUtils.isEmpty(reqDtl) ) {
            
            query = new Query();
            query.setFilter("factoryCode", AuthUtils.getFactoryCode());
            query.setFilter("inspNo", reqDtl.getInspNo());
            
            MQcmReqDef reqDef = DbUtils.select(MQcmReqDef.class, query);
            
            if ( !"COMPLETE".equals(reqDef.getInspType()) ) {
                
                throw new BizException("제품 입고 검사가 진행 중입니다.");
                
            } else {
                
                if ( !"NG".equals(reqDef.getJudgResultCode()) ) {
                    
                    throw new BizException("이미 제품입고 검사가 완료 되었습니다.");
                    
                }
                
            }
            
        }
        
        query = new Query();
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        String filter = "";
        switch ( lblDef.getLabelType() ) {
        
        case "LOT" : 
            filter = "labelId";
            break;
        case "SMALL" : 
            filter = "smallLabelId";
            break;
        case "BOX" : 
            filter = "boxNo";
            break;
        case "LARGE" : 
            filter = "largeLabelId";
            break;
        case "PALLET" : 
            filter = "palletNo";
            break;
        }
        query.setFilter(filter, reqLot.getLabelId());
        
        List<MInvLblLot> lblList = DbUtils.selectList(MInvLblLot.class, query);
        
        if ( ValueUtils.isEmpty(lblList) ) {
            
            throw new BizException("라벨 릴레이션 정보가 존재하지 않습니다.");
            
        }
        
        MInvLblLot lblLot = lblList.get(0);
        
        int level = getBoxLevel(lblDef.getLabelType());
        
        boolean errorFlag = false;
        if ( level == 2 ) {
            
            if ( !ValueUtils.isEmpty(lblLot.getSmallLabelId()) ) {
                
                errorFlag = true;
                
            }
            
        } else if ( level == 3 ) {
            
            if ( !ValueUtils.isEmpty(lblLot.getSmallLabelId()) || !ValueUtils.isEmpty(lblLot.getBoxNo())) {
                
                errorFlag = true;
                
            }
            
        } else if ( level == 4 ) {
            
            if ( !ValueUtils.isEmpty(lblLot.getSmallLabelId()) || !ValueUtils.isEmpty(lblLot.getBoxNo()) || !ValueUtils.isEmpty(lblLot.getLargeLabelId())) {
                
                errorFlag = true;
                
            }
            
        } else if ( level == 5 ) {
            
            if ( !ValueUtils.isEmpty(lblLot.getSmallLabelId()) || !ValueUtils.isEmpty(lblLot.getBoxNo()) || !ValueUtils.isEmpty(lblLot.getLargeLabelId()) || !ValueUtils.isEmpty(lblLot.getPalletNo())) {
                
                errorFlag = true;
                
            }
            
        }
        
        if ( errorFlag ) {
            
            throw new BizException("최상위 박스가 아닙니다.");
            
        }
        
        return lblDef;
    }
    
    public int getBoxLevel(String labelType) throws Exception {
        
        GetCodeDataListIn inputGcm = new GetCodeDataListIn();
        inputGcm.setTableName("LABEL_TYPE");
        inputGcm.setKey1(labelType);
        
        GetListOut codeData = BeanUtils.get(GetCodeDataList.class).getList(inputGcm);
       
        if ( codeData == null ) {
            
            throw new BizException("포장 유형이 정의되지 않았습니다.");
            
        }
        
        CodeData data = (CodeData) codeData.getList().get(0);
        
        int gcmLevel = Integer.parseInt(data.getData1());
        int level = 0;
        switch ( labelType ) {
        case "LOT" :
            level = 1;
        case "SMALL" : 
            level = 2;
            break;
        case "BOX" : 
            level = 3;
            break;
        case "LARGE" :
            level = 4;
            break;
        case "PALLET" :
            level = 5;
            break;
            
        }
        
        return level;
        
    }
    
}
