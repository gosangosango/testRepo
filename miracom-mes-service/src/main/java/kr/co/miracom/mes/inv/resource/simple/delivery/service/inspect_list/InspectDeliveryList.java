package kr.co.miracom.mes.inv.resource.simple.delivery.service.inspect_list;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.exception.BizException;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.Property;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.inv.model.MInvDlvDtl;
import kr.co.miracom.mes.inv.model.MInvDlvMst;
import kr.co.miracom.mes.inv.model.MInvLblDef;
import kr.co.miracom.mes.inv.model.MInvLotSts;
import kr.co.miracom.mes.inv.resource.simple.delivery.model.Delivery;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.service.create.CreateInvLot;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.service.create.CreateInvLotIn;
import kr.co.miracom.mes.wip.model.MWipMatDef;

public class InspectDeliveryList {
    public InspectDeliveryListOut inspect(InspectDeliveryListIn input) throws Exception {
        if (ValueUtils.isEmpty(input.getList())) {
            return new InspectDeliveryListOut();
        }

        ValueUtils.checkNotEmpty(input, "operCode");

        Set<String> checkedMats = new HashSet<>();

        List<MInvDlvMst> dlvList = new ArrayList<>();
        List<CreateInvLotIn> lotList = new ArrayList<>();
        for (Delivery item : input.getList()) {
            MInvDlvMst data = DbUtils.select(MInvDlvMst.class, item,
                            new SelectOptions().addSelect("factoryCode", "dlvNo", "purInspCompFlag"), true);
            if (data.isPurInspCompFlag()) {
                throw new BizException("XXXXX", "이미 구매 검수 처리 되었습니다.", new Property("dlvNo", item.getDlvNo()));
            }

            List<MInvLblDef> labelList;
            {
                Query query = new Query();
                query.addFilter("factoryCode", AuthUtils.getFactoryCode());
                query.addFilter("dlvNo", item.getDlvNo());
                query.addFilter("labelType", "LOT");
                query.addOrder("dlvSeq", true);
                labelList = DbUtils.selectList(MInvLblDef.class, query);
            }

            if (ValueUtils.isEmpty(labelList)) {
                throw new BizException("XXXXX", "라벨 정보를 찾을 수 없습니다.", new Property("dlvNo", item.getDlvNo()));
            }

            for (MInvLblDef label : labelList) {
                MInvLotSts invLot = DbUtils.select(MInvLotSts.class, label.getLabelId());
                if (invLot != null) {
                    throw new BizException("XXXXX", "이미 자재Lot이 있습니다.", new Property("invLotId", invLot.getInvLotId()));
                }

                MInvDlvDtl detail = DbUtils.select(MInvDlvDtl.class, label.getDlvNo() + "," + label.getDlvSeq(), null,
                                true);

                ValueUtils.checkNotEmpty(detail, "matCode");

                if (!checkedMats.contains(detail.getMatCode())) {
                    DbUtils.checkFound(MWipMatDef.class, detail.getMatCode());
                    checkedMats.add(detail.getMatCode());
                }

                CreateInvLotIn reqIn = new CreateInvLotIn();
                reqIn.setInvLotId(label.getLabelId());
                reqIn.setOperCode(input.getOperCode());
                reqIn.setMatCode(detail.getMatCode());
                reqIn.setQty(detail.getDlvQty());
                reqIn.setVendorCode(data.getVendorCode());
                lotList.add(reqIn);
            }

            data.setPurInspUserId(AuthUtils.getUserId());
            data.setPurInspTime(ValueUtils.getZonedDateTime());
            data.setPurInspDate(ValueUtils.getDateStr());
            data.setPurInspCompFlag(true);
            dlvList.add(data);
        }

        for (CreateInvLotIn reqIn : lotList) {
            BeanUtils.get(CreateInvLot.class).create(reqIn);
        }
        DbUtils.updateBatch(dlvList, new String[] { "purInspUserId", "purInspTime", "purInspCompFlag" });

        InspectDeliveryListOut output = new InspectDeliveryListOut();
        return output;
    }
}
