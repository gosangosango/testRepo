package kr.co.miracom.mes.wip.resource.simple.lot.service.create;

import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.exception.BizException;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.wip.model.MWipFlwDef;
import kr.co.miracom.mes.wip.model.MWipFlwOpr;
import kr.co.miracom.mes.wip.model.MWipLinDef;
import kr.co.miracom.mes.wip.model.MWipLotHis;
import kr.co.miracom.mes.wip.model.MWipLotSts;
import kr.co.miracom.mes.wip.model.MWipMatDef;
import kr.co.miracom.mes.wip.model.MWipOrdSts;

public class CreateLot {
    public CreateLotOut create(CreateLotIn input) throws Exception {
        ValueUtils.checkNotEmpty(input, "orderNo", "matCode");
        ValueUtils.checkPositive(input, "qty");

        MWipOrdSts order = DbUtils.selectWithLock(MWipOrdSts.class, input,
                        new SelectOptions().addSelect("orderNo", "ordQty", "ordInQty"), true);

        DbUtils.checkFound(MWipLinDef.class, input.getLineCode());
        DbUtils.checkFound(MWipMatDef.class, input.getMatCode());
        DbUtils.checkFound(MWipFlwDef.class, input.getFlowCode());

        order.setOrdInQty(order.getOrdInQty() + 1);

        MWipLotSts lot = DbUtils.selectWithLock(MWipLotSts.class, input);
        if (lot != null) {
            throw new BizException("XXXXXX", "이미 생성된 Lot 입니다.");
        }
        lot = new MWipLotSts();
        lot.setFactoryCode(AuthUtils.getFactoryCode());
        ValueUtils.populate(input, lot);
        lot.setLastTranCode("CREATE");
        lot.setLastTranTime(ValueUtils.getZonedDateTime());
        lot.setCreateQty(lot.getQty());
        lot.setCreateTime(ValueUtils.getZonedDateTime());

        if (!ValueUtils.isEmpty(input.getFlowCode())) {
            Query query = new Query(0, 1);
            query.addSelect("operCode");
            query.addFilter("factoryCode", AuthUtils.getFactoryCode());
            query.addFilter("flowCode", input.getFlowCode());
            query.addOrder("seqNo", true);
            MWipFlwOpr oper = DbUtils.select(MWipFlwOpr.class, query);
            if (oper == null) {
                throw new BizException("XXXXXX", "플로우에 설정된 공정이 하나도 없습니다.");
            }
            lot.setOperCode(oper.getOperCode());
        }

        MWipLotHis hist;
        {
            Query query = new Query(0, 1);
            query.addFilter("factoryCode", AuthUtils.getFactoryCode());
            query.addFilter("lotId", input.getLotId());
            query.addOrder("histSeq", false);
            hist = DbUtils.select(MWipLotHis.class, query);

            if (hist == null) {
                hist = new MWipLotHis();
            }
        }
        ValueUtils.populate(lot, hist);
        hist.setTranCode(lot.getLastTranCode());
        hist.setTranTime(lot.getLastTranTime());
        hist.setHistSeq(hist.getHistSeq() + 1);

        DbUtils.update(order, new String[] { "ordInQty" });

        DbUtils.insert(lot);

        DbUtils.insert(hist);

        CreateLotOut output = new CreateLotOut();
        output.setLotId(lot.getLotId());
        return output;
    }
}
