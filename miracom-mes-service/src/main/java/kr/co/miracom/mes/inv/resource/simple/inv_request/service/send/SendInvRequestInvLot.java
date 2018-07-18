
package kr.co.miracom.mes.inv.resource.simple.inv_request.service.send;

import java.util.ArrayList;
import java.util.List;

import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.exception.BizException;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.inv.model.MInvLotHis;
import kr.co.miracom.mes.inv.model.MInvLotSts;
import kr.co.miracom.mes.inv.model.MInvMatIss;
import kr.co.miracom.mes.inv.model.MInvReqDtl;
import kr.co.miracom.mes.inv.model.MInvReqMst;
import kr.co.miracom.mes.inv.resource.simple.inv_request.model.InvRequestInvLot;
import kr.co.miracom.mes.inv.util.InvUtils;
import kr.co.miracom.mes.wip.model.MWipOprDef;

public class SendInvRequestInvLot {
    public SendInvRequestInvLotOut send(SendInvRequestInvLotIn input) throws Exception {
        /* Start Validation */
        ValueUtils.checkNotEmpty(input, "fromOperCode", "toOperCode");
        if (input.getFromOperCode().equals(input.getToOperCode())) {
            throw new BizException("XXXXXX", "동일한 창고로 불출할 수 없습니다.");
        }
        Boolean needCheckReq = false;
        if (!ValueUtils.isEmpty(input.getNoReqType())) {
            ValueUtils.checkNotEmpty(input, "reason");
            String reqNo = ValueUtils.toDateStr(ValueUtils.getDate(), "yyyyMMddHHmmssS");
            input.setReqNo(reqNo);
        } else {
            ValueUtils.checkNotEmpty(input, "reqNo");
            DbUtils.checkFound(MInvReqMst.class, input.getReqNo());
            needCheckReq = true;
        }
        
        MWipOprDef wipOprDef = DbUtils.select(MWipOprDef.class, input.getFromOperCode());
        if (wipOprDef == null) {
            throw new BizException("XXXXXX", "이 창고는 존재하지 않습니다.");
        } else if (!wipOprDef.isStoreFlag()) {
            throw new BizException("XXXXXX", "창고가 아닙니다.");
        }
        wipOprDef = DbUtils.select(MWipOprDef.class, input.getToOperCode());
        if (wipOprDef == null) {
            throw new BizException("XXXXXX", "이 창고는 존재하지 않습니다.");
        } else if (!wipOprDef.isStoreFlag()) {
            throw new BizException("XXXXXX", "창고가 아닙니다.");
        }

        List<MInvReqDtl> matList = new ArrayList<>();
        if (needCheckReq) {
            Query query = new Query();
            query.addFilter("reqNo", input.getReqNo());
            matList = DbUtils.selectList(MInvReqDtl.class, query);
        }
        
        List<MInvMatIss> issList = new ArrayList<>();
        for (InvRequestInvLot lot : input.getList()) {
            MInvLotSts invLotSts = DbUtils.select(MInvLotSts.class, lot.getInvLotId(), true);
            
            if (!input.getFromOperCode().equals(invLotSts.getOperCode())) {
                throw new BizException("XXXXXX", "자재 Lot의 위치가 일치하지 않습니다.");
            }
            
            if (needCheckReq) {
                if (!matList.stream().anyMatch(mat -> invLotSts.getMatCode().equals(mat.getReqMatCode()))) {
                    throw new BizException("XXXXXX", "요청한 자재가 아닙니다.");
                }
                MInvMatIss invMatIss = new MInvMatIss();
                invMatIss.setReqMatCode(invLotSts.getMatCode());
                invMatIss.setIssueQty(invLotSts.getQty());
                
                issList.add(invMatIss);
            }
        }
        
        for (MInvReqDtl mat : matList) {
            Query issuedQuery = new Query();
            issuedQuery.addFilter("factoryCode", AuthUtils.getFactoryCode());
            issuedQuery.addFilter("reqNo", mat.getReqNo());
            issuedQuery.addFilter("reqMatCode", mat.getReqMatCode());
            
            Double issuedQty = DbUtils.select(DbUtils.toSqlPath(SendInvRequestInvLot.class, "select_sum_issue_qty.sql"), issuedQuery, Double.class); 
            Double sendQty = issList.stream().filter(iss -> mat.getReqMatCode().equals(iss.getReqMatCode())).mapToDouble(iss -> iss.getIssueQty()).sum();
            if (issuedQty + sendQty > mat.getReqQty()) {
                throw new BizException("XXXXXX", "필요 수량을 초과하여 불출할 수 없습니다.");
            }
        }
        /* End Validation */
        
        /* Send Inv Lot */
        for (InvRequestInvLot lot : input.getList()) {
            MInvLotSts invLotSts = DbUtils.selectWithLock(MInvLotSts.class, lot.getInvLotId(), true);
            
            invLotSts.setOperCode(input.getToOperCode());
            
            MInvLotHis invLotHis = InvUtils.insertInvLotHistory("SEND", invLotSts);
            InvUtils.insertInvLotMoveHistory(input.getFromOperCode(), invLotHis);
            DbUtils.update(invLotSts);
            
            Query maxQuery = new Query(0, 1);
            maxQuery.addFilter("factoryCode", invLotSts.getFactoryCode());
            maxQuery.addFilter("reqNo", input.getReqNo());
            maxQuery.addFilter("reqMatCode", invLotSts.getMatCode());
            maxQuery.addOrder("seqNo", false);
            List<MInvMatIss> maxSeq = DbUtils.selectList(MInvMatIss.class, maxQuery);
            int seqNo = 1;
            if (maxSeq != null && !maxSeq.isEmpty()) {
                seqNo = maxSeq.get(0).getSeqNo() + 1;
            }
            
            MInvMatIss invMatIss = new MInvMatIss();
            invMatIss.setFactoryCode(AuthUtils.getFactoryCode());
            invMatIss.setReqNo(input.getReqNo());
            invMatIss.setReqMatCode(invLotSts.getMatCode());
            invMatIss.setSeqNo(seqNo);
            invMatIss.setInvLotId(invLotSts.getInvLotId());
            invMatIss.setIssueQty(invLotSts.getQty());
            invMatIss.setFromOperCode(input.getFromOperCode());
            invMatIss.setToOperCode(input.getToOperCode());
            invMatIss.setNoReqType(input.getNoReqType());
            invMatIss.setReason(input.getReason());
            
            DbUtils.insert(invMatIss);
        }
        
        SendInvRequestInvLotOut output = new SendInvRequestInvLotOut();
        return output;
    }
}
