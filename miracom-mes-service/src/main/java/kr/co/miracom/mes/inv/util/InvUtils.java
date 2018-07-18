package kr.co.miracom.mes.inv.util;

import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.inv.model.MInvLotHis;
import kr.co.miracom.mes.inv.model.MInvLotMvh;
import kr.co.miracom.mes.inv.model.MInvLotSts;

public class InvUtils {
    public static MInvLotHis insertInvLotHistory(String tranCode, MInvLotSts invLot) throws Exception {
        invLot.setLastTranCode(tranCode);
        invLot.setLastTranTime(ValueUtils.getZonedDateTime());

        MInvLotHis hist;
        {
            Query query = new Query(0, 1);
            query.addFilter("factoryCode", AuthUtils.getFactoryCode());
            query.addFilter("invLotId", invLot.getInvLotId());
            query.addOrder("histSeq", false);
            hist = DbUtils.select(MInvLotHis.class, query);

            if (hist == null) {
                hist = new MInvLotHis();
            }
        }

        ValueUtils.populate(invLot, hist);
        hist.setTranCode(invLot.getLastTranCode());
        hist.setTranTime(invLot.getLastTranTime());
        hist.setHistSeq(hist.getHistSeq() + 1);
        DbUtils.insert(hist);

        return hist;
    }
    
    public static MInvLotMvh insertInvLotMoveHistory(String fromOperCode, MInvLotHis invLotHis) throws Exception {
        MInvLotMvh moveHist = ValueUtils.populate(invLotHis, new MInvLotMvh());
        moveHist.setFromOperCode(fromOperCode);
        moveHist.setTranQty(invLotHis.getQty());
        moveHist.setInvDate(ValueUtils.getDateStr());
        
        DbUtils.insert(moveHist);
        return moveHist;
    }
}
