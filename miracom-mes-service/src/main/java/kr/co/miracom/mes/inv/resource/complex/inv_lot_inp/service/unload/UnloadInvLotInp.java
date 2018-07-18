package kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.unload;

import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.exception.BizException;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.model.LoadInv;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.valid_lot.ValidInvLotInpLot;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.valid_lot.ValidInvLotInpLotIn;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.service.input.InputInvLot;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.service.input.InputInvLotIn;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.service.unload.UnloadInvLot;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.service.unload.UnloadInvLotIn;
import kr.co.miracom.mes.wip.model.MWipLinDef;

/**
 * Post Service: InvLotInput
 * @author gom
 * @since 2018. 07. 04.
 */
public class UnloadInvLotInp {
    public UnloadInvLotInpOut unload(UnloadInvLotInpIn input) throws Exception {
        ValueUtils.checkNotEmpty(input, "lineCode", "list");
        
        if ( ValueUtils.isEmpty(input.getOperCode()) ) {
            
            Query query = new Query();
            query.setFilter("factoryCode", AuthUtils.getFactoryCode());
            query.setFilter("lineCode", input.getLineCode());
            
            MWipLinDef linDef = DbUtils.select(MWipLinDef.class, query);
            
            if ( ValueUtils.isEmpty(linDef) ) {
                
                throw new BizException("라인 정보가 존재하지 않습니다.");
                
            }
            
            if ( ValueUtils.isEmpty(linDef.getInpMatOperCode()) ){
                
                throw new BizException("라인 자재 투입 창고가 지정되어 있지 않습니다.");
                
            }
            
            input.setOperCode(linDef.getInpMatOperCode());
            
        }
        
        UnloadInvLotIn inputUnload;
        for ( LoadInv loadInv : input.getList() ) {
            
            inputUnload = new UnloadInvLotIn();
            ValueUtils.populate(input, inputUnload);
            inputUnload.setInvLotId(loadInv.getInvLotId());            
            
            BeanUtils.get(UnloadInvLot.class).unload(inputUnload);
            
        }
        
        UnloadInvLotInpOut output = new UnloadInvLotInpOut();
        return output;
    }
}
