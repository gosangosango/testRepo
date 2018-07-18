package kr.co.miracom.mes.bas.resource.simple.code_data.service;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.RestService;
import kr.co.miracom.mes.bas.model.MGcmTblDat;
import kr.co.miracom.mes.bas.resource.simple.code_data.model.CodeData;
import kr.co.miracom.mes.bas.resource.simple.code_data.model.CodeDataDetail;

/**
 * Setup Service: CodeData
 * @author myjung.jung
 * @since 2018. 06. 05.
 */
public class CodeDataService {
    private RestService<MGcmTblDat, CodeData, CodeDataDetail> restService = new RestService<>(MGcmTblDat.class, CodeData.class, CodeDataDetail.class);

    public CodeDataDetail get(String id, SelectOptions options) throws Exception {
        return restService.get(id, options);
    }

    public void post(String id, CodeDataDetail data, String[] fieldName) throws Exception {
        restService.post(id, data, fieldName);
    }

    public void put(String id, CodeDataDetail data, String[] fieldName) throws Exception {
        restService.put(id, data, fieldName);
    }

    public void delete(String id, CodeDataDetail data) throws Exception {
        restService.delete(id, data);
    }

    public void postList(List<CodeData> list, String[] fieldName) throws Exception {
        restService.postList(list, fieldName);
    }

    public void putList(List<CodeData> list, String[] fieldName) throws Exception {
        restService.putList(list, fieldName);
    }

    public void saveList(List<CodeData> list, String[] fieldName) throws Exception {
        restService.saveList(list, fieldName);
    }

    public void deleteList(List<CodeData> list) throws Exception {
        restService.deleteList(list);
    }

}
