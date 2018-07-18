package kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.post;

import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.model.LoadInv;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.valid_lot.ValidInvLotInpLot;
import kr.co.miracom.mes.inv.resource.complex.inv_lot_inp.service.valid_lot.ValidInvLotInpLotIn;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.service.input.InputInvLot;
import kr.co.miracom.mes.inv.resource.simple.inv_lot.service.input.InputInvLotIn;

/**
 * Post Service: InvLotInput
 * @author gom
 * @since 2018. 07. 04.
 */
public class PostInvLotInp {
    public PostInvLotInpOut post(PostInvLotInpIn input) throws Exception {
        ValueUtils.checkNotEmpty(input, "lineCode", "operCode", "orderNo", "list");
        
        ValidInvLotInpLotIn inputValid;
        InputInvLotIn inputInv;
        for ( LoadInv loadInv : input.getList() ) {
            
            inputValid = new ValidInvLotInpLotIn();
            ValueUtils.populate(input, inputValid);
            
            inputValid.setInvLotId(loadInv.getInvLotId());
            
            BeanUtils.get(ValidInvLotInpLot.class).validLot(inputValid);
            
            inputInv = new InputInvLotIn();
            ValueUtils.populate(inputValid, inputInv);
            inputInv.setLineCode(input.getLineCode());
            inputInv.setOperCode(input.getOperCode());
                        
            BeanUtils.get(InputInvLot.class).input(inputInv);
            
        }
        
        PostInvLotInpOut output = new PostInvLotInpOut();
        return output;
    }
}
