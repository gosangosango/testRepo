package kr.co.miracom.mes.wip.resource.simple.lot.service.ship;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
import kr.co.miracom.mes.wip.resource.complex.lot_pak.service.post.PostLotPakIn;
import kr.co.miracom.mes.wip.resource.simple.lot.model.Pack;

public class ShipLot {
    
    public ShipLotOut ship(ShipLotIn input) throws Exception {
        
        ValueUtils.checkNotEmpty(input, "packLabelId", "packLabelType", "packCount", "list");
        
        MInvLblDef lblDef = checkPackInfo(input);
        
        updateLblLot(input);
        
        ShipLotOut output = new ShipLotOut();
        output.setPackLabelId(lblDef.getLabelId());
        
        return output;
    }
    
    private MInvLblDef checkPackInfo(ShipLotIn input) throws Exception {
       
        if ( input.getPackCount() == 0.0 ) {
            
            throw new BizException("MIC-00081");
            
        }
        
        if ( ValueUtils.isEmpty(input.getPackLabelId()) ) {
            
            throw new BizException("MIC-00082");                
            
        }
            
        Query query = new Query();
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        query.setFilter("labelId", input.getPackLabelId());
        
        MInvLblDef lblDef = DbUtils.select(MInvLblDef.class, query);
        
        if ( !lblDef.getLabelStatus().equals("CREATE") ) {
            
            throw new BizException("MIC-00078");
            
        }
        
        lblDef.setLabelStatus("PROCESS");
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); 
        Date now = Calendar.getInstance().getTime(); 
        
        String dateString = sdf.format(now);
        lblDef.setPackDate(dateString);
        
        return lblDef;
        
    }
    
    private void updateLblLot(ShipLotIn input) throws Exception {
        
        Query query = new Query();
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        
        String labelType = input.getList().get(0).getLabelType();
        String filter = "labelId";
        switch ( labelType ) {
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
        
        int level = getBoxLevel(input.getPackLabelType());
        
        MInvLblLot lblLot;
        for ( Pack pack : input.getList() ) {
            
            query.setFilter(filter, pack.getLabelId());
            
            lblLot = DbUtils.select(MInvLblLot.class,  query);
            
            if ( lblLot == null ) {
                
                throw new BizException("라벨 릴레이션 정보가 존재하지 않습니다.");
                
            }
            
            checkBoxError(lblLot, level);
            
            switch ( level ) {
            case 2 : 
                
                lblLot.setSmallLabelId(input.getPackLabelId());
                break;
                
            case 3 :
                
                lblLot.setBoxNo(input.getPackLabelId());
                break;
                
            case 4 :
                
                lblLot.setLargeLabelId(input.getPackLabelId());
                break;
                
            case 5 :
                
                lblLot.setPalletNo(input.getPackLabelId());
                break;
                
            }
            
            DbUtils.update(lblLot);
            
        }
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
    
    public void checkBoxError(MInvLblLot lblLot, int level) throws Exception {
        
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
            
            throw new BizException("이미 포장 되었습니다.");
            
        }
        
    }
    
}
