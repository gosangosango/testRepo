package kr.co.miracom.mes.wip.resource.complex.lot_pak.service.get;

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
import kr.co.miracom.mes.wip.model.MWipMatDef;
import kr.co.miracom.mes.wip.model.MWipOrdSts;

/**
 * Get Service: LotPak
 * @author gom
 * @since 2018. 07. 02.
 */
public class GetLotPak {
    public GetLotPakOut get(GetLotPakIn input) throws Exception {
        Query query = new Query();
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        query.setFilter("labelId", input.getLabelId());
        
        MInvLblDef lblDef = DbUtils.select(MInvLblDef.class, query);
        
        if ( lblDef == null ) {
            
            throw new BizException("MIC-00069");
            
        }
        
        GetLotPakOut output = new GetLotPakOut();
        if ( "PACK".equals(input.getViewType()) ) {
            
            if ( !"CREATE".equals(lblDef.getLabelStatus()) ) {
                
                throw new BizException("MIC-00078");
                
            }
            
            
        } else if ( "LABEL".equals(input.getViewType()) ) {
            
            if ( !"PROCESS".equals(lblDef.getLabelStatus()) ) {
                
                throw new BizException("MIC-00079");
                
            }
            
            GetCodeDataListIn inputGcm = new GetCodeDataListIn();
            inputGcm.setTableName("LABEL_TYPE");
            inputGcm.setKey1(input.getPackLabelType());
            
            GetListOut codeData = BeanUtils.get(GetCodeDataList.class).getList(inputGcm);
           
            if ( codeData == null ) {
                
                throw new BizException("포장 유형이 정의되지 않았습니다.");
                
            }
            
            CodeData data = (CodeData) codeData.getList().get(0);
            
            int gcmLevel = Integer.parseInt(data.getData1());
            int level = 0;
            switch ( lblDef.getLabelType() ) {
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
            
            if ( gcmLevel <= level ) {
                
                throw new BizException("MIC-00080");
                
            }
            
        }
          
        query = new Query();
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        query.setFilter("matCode", lblDef.getMatCode());
        
        MWipMatDef matDef = DbUtils.select(MWipMatDef.class, query);
        
        if ( matDef == null ) {
            
            throw new BizException("자재/제품 정보가 존재하지 않습니다.");
            
        }
        
        ValueUtils.populate(lblDef, output);
        output.setMatCode(matDef.getMatCode());
        output.setMatDesc(matDef.getMatShortDesc());
        output.setQty(lblDef.getPackQty());
            
        return output;
    }
}
