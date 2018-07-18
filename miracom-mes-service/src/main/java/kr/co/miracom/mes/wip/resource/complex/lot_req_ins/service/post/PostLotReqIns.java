package kr.co.miracom.mes.wip.resource.complex.lot_req_ins.service.post;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.wip.resource.simple.lot.service.request_inspection.RequestInspectionLot;
import kr.co.miracom.mes.wip.resource.simple.lot.service.request_inspection.RequestInspectionLotIn;

/**
 * Post Service: LotReqIns
 * @author gom
 * @since 2018. 07. 05.
 */
public class PostLotReqIns {
    public PostLotReqInsOut post(PostLotReqInsIn input) throws Exception {
        
        ValueUtils.checkNotEmpty(input, "inspType", "inspReqQty", "matCode", "list");
        
        String inspNo = generateInspNo();
        
        RequestInspectionLotIn inputReq = new RequestInspectionLotIn();
        ValueUtils.populate(input, inputReq);
        inputReq.setInspNo(inspNo);
        
        BeanUtils.get(RequestInspectionLot.class).requestInspection(inputReq);
        
        PostLotReqInsOut output = new PostLotReqInsOut();
        output.setInspNo(inspNo);
        
        return output;
    }
    
    private String generateInspNo() throws Exception {
        
        String inspNo = "";
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss"); 
        Date now = Calendar.getInstance().getTime(); 
        
        String dateString = sdf.format(now);
        inspNo = "INSP" + dateString;
        
        return inspNo;
        
    }
    
}
