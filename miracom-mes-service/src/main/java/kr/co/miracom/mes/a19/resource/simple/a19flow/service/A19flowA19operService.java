package kr.co.miracom.mes.a19.resource.simple.a19flow.service;

import java.util.List;

import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.service.RestService4OneToMany;
import kr.co.miracom.mes.a19.model.MA19FlwDef;
import kr.co.miracom.mes.a19.model.MA19FlwOpr;
import kr.co.miracom.mes.a19.resource.simple.a19flow.model.A19flowA19oper;

/**
 * Setup Service: A19flow
 * @author Zotac023
 * @since 2018. 07. 17.
 */
public class A19flowA19operService {
    private RestService4OneToMany<MA19FlwDef, MA19FlwOpr, A19flowA19oper> restService = new RestService4OneToMany<>(MA19FlwDef.class, MA19FlwOpr.class, A19flowA19oper.class);

    public GetListOut<A19flowA19oper> getList(String id) throws Exception {
        return restService.getList(id);
    }

    public void saveList(String id, List<A19flowA19oper> list, String[] fieldName) throws Exception {
        restService.saveList(id, list, fieldName);
    }

}
