package kr.co.miracom.mes.wip.util;

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
import kr.co.miracom.mes.wip.model.MWipLotHis;
import kr.co.miracom.mes.wip.model.MWipLotSts;

public class LotUtils {
    public static MWipLotHis insertInvLotHistory(MWipLotSts lotSts) throws Exception {
        
        Query query = new Query(0, 1);
        query.addFilter("factoryCode", AuthUtils.getFactoryCode());
        query.addFilter("lotId", lotSts.getLotId());
        query.addOrder("histSeq", false);
        MWipLotHis hist = DbUtils.select(MWipLotHis.class, query);

        if (hist == null) {
            hist = new MWipLotHis();
        }
        
        ValueUtils.populate(lotSts, hist);
        hist.setTranCode(lotSts.getLastTranCode());
        hist.setTranTime(lotSts.getLastTranTime());
        hist.setHistSeq(hist.getHistSeq() + 1);
        DbUtils.insert(hist);

        return hist;
    }
  
    public static String getPackFilter(String labelType) throws Exception {
        
        String filter = "";
        switch ( labelType ) {
        case "LOT" :
            filter = "labelId";
        case "SMALL" : 
            filter = "smallPackId";
            break;
        case "BOX" : 
            filter = "boxNo";
            break;
        case "LARGE" :
            filter = "largePackId";
            break;
        case "PALLET" :
            filter = "palletNo";
            break;
            
        }
        
        return filter;
        
    }
    
}
