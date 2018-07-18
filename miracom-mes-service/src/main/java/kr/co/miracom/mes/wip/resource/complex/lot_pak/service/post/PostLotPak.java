package kr.co.miracom.mes.wip.resource.complex.lot_pak.service.post;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
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
import kr.co.miracom.mes.wip.resource.simple.lot.model.Pack;
import kr.co.miracom.mes.wip.resource.simple.lot.service.pack.PackLot;
import kr.co.miracom.mes.wip.resource.simple.lot.service.pack.PackLotIn;
import kr.co.miracom.mes.wip.resource.simple.lot.service.pack.PackLotOut;

/**
 * Post Service: LotPak
 * @author gom
 * @since 2018. 07. 02.
 */
public class PostLotPak {
    public PostLotPakOut post(PostLotPakIn input) throws Exception {
        
        ValueUtils.checkNotEmpty(input, "packLabelType", "packCount", "list");
        
        String packLabelId = "";
        if ( input.isAutoNumberingFlag() ) {
            
            if ( input.getPackLabelId() == null || "".equals(input.getPackLabelId()) ) {
                
                packLabelId = generatePackLabelId(input);
            
                createLblDef(input, packLabelId);
                
            } else {
                
                packLabelId = input.getPackLabelId();
                
            }
            
            input.setPackLabelId(packLabelId);
            
        }
        
        PackLotIn inputPack = new PackLotIn();
        ValueUtils.populate(input, inputPack);
        
        PackLotOut outputPack = BeanUtils.get(PackLot.class).pack(inputPack);
        
        PostLotPakOut output = new PostLotPakOut();
        output.setPackLabelId(outputPack.getPackLabelId());
        
        return output;
    }
    
    private String generatePackLabelId(PostLotPakIn input) throws Exception {
        
        String packLabelId = input.getPackLabelType();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss"); 
        Date now = Calendar.getInstance().getTime(); 
        
        String dateString = sdf.format(now);
        packLabelId = packLabelId + dateString;
        
        return packLabelId;
        
    }
    
    private void createLblDef(PostLotPakIn input, String packLabelId) throws Exception {
        
        Query query = new Query();
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        query.setFilter("labelId", packLabelId);
        
        MInvLblDef lblDef = DbUtils.select(MInvLblDef.class, query);
        
        if ( lblDef != null ) {
            
            throw new BizException("MIC-00039");
            
        }
        
        lblDef = new MInvLblDef();
        lblDef.setFactoryCode(AuthUtils.getFactoryCode());
        lblDef.setLabelId(packLabelId);
        lblDef.setLabelType(input.getPackLabelType());
        lblDef.setMatCode(input.getMatCode());
        lblDef.setPackQty(input.getPackCount());
        lblDef.setLabelStatus("CREATE");
        lblDef.setLabelCode("PACK");
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); 
        Date now = Calendar.getInstance().getTime(); 
        
        String dateString = sdf.format(now);
        lblDef.setPackDate(dateString);
        
        DbUtils.insert(lblDef);
        
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
