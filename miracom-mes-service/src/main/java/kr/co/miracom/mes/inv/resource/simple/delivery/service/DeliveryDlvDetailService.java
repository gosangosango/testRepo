package kr.co.miracom.mes.inv.resource.simple.delivery.service;

import java.util.List;

import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.service.RestService4OneToMany;
import kr.co.miracom.mes.inv.model.MInvDlvDtl;
import kr.co.miracom.mes.inv.model.MInvDlvMst;
import kr.co.miracom.mes.inv.resource.simple.delivery.model.DeliveryDlvDetail;

/**
 * Setup Service: Delivery
 * @author myjung.jung
 * @since 2018. 06. 25.
 */
public class DeliveryDlvDetailService {
    private RestService4OneToMany<MInvDlvMst, MInvDlvDtl, DeliveryDlvDetail> restService = new RestService4OneToMany<>(
                    MInvDlvMst.class, MInvDlvDtl.class, DeliveryDlvDetail.class);

    public GetListOut<DeliveryDlvDetail> getList(String id) throws Exception {
        return restService.getList(id);
    }

    public void saveList(String id, List<DeliveryDlvDetail> list, String[] fieldName) throws Exception {
        restService.saveList(id, list, fieldName);
    }

}
