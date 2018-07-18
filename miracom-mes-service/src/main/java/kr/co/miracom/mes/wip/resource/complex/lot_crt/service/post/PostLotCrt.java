package kr.co.miracom.mes.wip.resource.complex.lot_crt.service.post;

import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.wip.resource.simple.lot.service.create.CreateLot;
import kr.co.miracom.mes.wip.resource.simple.lot.service.create.CreateLotIn;
import kr.co.miracom.mes.wip.resource.simple.lot.service.create.CreateLotOut;

/**
 * Post Service: LotCrt
 * @author myjung.jung
 * @since 2018. 06. 25.
 */
public class PostLotCrt {
    public PostLotCrtOut post(PostLotCrtIn input) throws Exception {
        String lotId = input.getLotId();

        {
            CreateLotIn reqIn = new CreateLotIn();
            ValueUtils.populate(input, reqIn);
            CreateLotOut reqOut = BeanUtils.get(CreateLot.class).create(reqIn);
            lotId = reqOut.getLotId();
        }

        PostLotCrtOut output = new PostLotCrtOut();
        output.setLotId(lotId);
        return output;
    }
}
