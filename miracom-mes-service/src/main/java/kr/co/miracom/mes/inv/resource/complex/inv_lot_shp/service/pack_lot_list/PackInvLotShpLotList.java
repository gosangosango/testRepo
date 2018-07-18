package kr.co.miracom.mes.inv.resource.complex.inv_lot_shp.service.pack_lot_list;

import java.util.ArrayList;
import java.util.List;

import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.exception.BizException;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.inv.model.MInvLblDef;
import kr.co.miracom.mes.inv.model.MInvLblLot;
import kr.co.miracom.mes.inv.model.MInvLotSts;
import kr.co.miracom.mes.inv.model.MInvShpOrd;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_shp.model.ShipLotDetail;
import kr.co.miracom.mes.wip.model.MWipOprDef;
import kr.co.miracom.mes.wip.util.LotUtils;

/**
 * Get Service: InvLotShp
 * @author gom
 * @since 2018. 07. 10.
 */
public class PackInvLotShpLotList {
    public PackInvLotShpLotListOut get(PackInvLotShpLotListIn input) throws Exception {
        ValueUtils.checkNotEmpty(input, "labelId");
        
        Query query = new Query();
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        query.setFilter("labelId", input.getLabelId());
        
        MInvLblDef lblDef = DbUtils.select(MInvLblDef.class, query);
        
        if ( ValueUtils.isEmpty(lblDef) ) {
            
            throw new BizException("라벨 정의 정보가 존재하지 않습니다.");
            
        }
        
        if ( "LOT".equals(lblDef.getLabelType()) ) {
            
            throw new BizException("LOT은 출하할 수 없습니다.");
            
        }
        
        query = new Query();
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        query.setFilter(LotUtils.getPackFilter(lblDef.getLabelType()), lblDef.getLabelId());
        
        List<MInvLblLot> lblLotList = DbUtils.selectList(MInvLblLot.class, query);
        
        if ( ValueUtils.isEmpty(lblLotList) ) {
            
            throw new BizException("라벨 Relation 정보가 존재하지 않습니다.");
            
        }
                
        MInvLotSts invLot;
        List<ShipLotDetail> list = new ArrayList<ShipLotDetail>();
        ShipLotDetail lotDetail;
        for ( MInvLblLot lblLot : lblLotList ) {
            
            query = new Query();
            query.setFilter("factoryCode", AuthUtils.getFactoryCode());
            query.setFilter("invLotId", lblLot.getLabelId());
            
            invLot = DbUtils.select(MInvLotSts.class, query);
            
            if ( ValueUtils.isEmpty(invLot) ) {
                
                throw new BizException("자재 LOT이 존재하지 않습니다.");                
                
            }
            
            if ( invLot.isLotDelFlag() ) {
                
                throw new BizException("자재 LOT이 삭제 되었습니다.");
                
            }
                
            lotDetail = new ShipLotDetail();
            ValueUtils.populate(invLot, lotDetail);
            
            list.add(lotDetail);
            
        }
        
        PackInvLotShpLotListOut output = new PackInvLotShpLotListOut();
        output.setList(list);
        
        return output;
    }
}
