package kr.co.miracom.mes.inv.resource.simple.inv_request.service;

import java.util.List;

import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.service.RestService4OneToMany;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.mes.inv.model.MInvReqDtl;
import kr.co.miracom.mes.inv.model.MInvReqMst;
import kr.co.miracom.mes.inv.resource.simple.inv_request.model.InvRequestMat;

/**
 * Setup Service: InvRequest
 * @author User
 * @since 2018. 07. 03.
 */
public class InvRequestMatService {
    private RestService4OneToMany<MInvReqMst, MInvReqDtl, InvRequestMat> restService = new RestService4OneToMany<>(MInvReqMst.class, MInvReqDtl.class, InvRequestMat.class);

    public GetListOut<InvRequestMat> getList(String id) throws Exception {
        MInvReqMst invReqMst = DbUtils.select(MInvReqMst.class, id, true);
        
        Query query = new Query();
        query.addFilter("factoryCode", AuthUtils.getFactoryCode());
        query.addFilter("reqNo", invReqMst.getReqNo());
        
        List<InvRequestMat> list = DbUtils.selectList(DbUtils.toSqlPath(InvRequestMatService.class, "select_inv_request_mat_list.sql"), query, InvRequestMat.class);
        
        GetListOut<InvRequestMat> out = new GetListOut<>();
        out.setTotalSize(list.size());
        out.setList(list);
        
        return out;
    }

    public void saveList(String id, List<InvRequestMat> list, String[] fieldName) throws Exception {
        restService.saveList(id, list, fieldName);
    }

}
