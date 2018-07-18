package kr.co.miracom.mes.bas.resource.simple.code_table.service;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.RestService;
import kr.co.miracom.mes.bas.model.MGcmTblDef;
import kr.co.miracom.mes.bas.resource.simple.code_table.model.CodeTable;
import kr.co.miracom.mes.bas.resource.simple.code_table.model.CodeTableDetail;

/**
 * Setup Service: CodeTable
 * @author myjung.jung
 * @since 2018. 06. 05.
 */
public class CodeTableService {
    private RestService<MGcmTblDef, CodeTable, CodeTableDetail> restService = new RestService<>(MGcmTblDef.class,
                    CodeTable.class, CodeTableDetail.class);

    public CodeTableDetail get(String id, SelectOptions options) throws Exception {
        return restService.get(id, options);
    }

    public void post(String id, CodeTableDetail data, String[] fieldName) throws Exception {
        restService.post(id, data, fieldName);
    }

    public void put(String id, CodeTableDetail data, String[] fieldName) throws Exception {
        restService.put(id, data, fieldName);
    }

    public void delete(String id, CodeTableDetail data) throws Exception {
        restService.delete(id, data);
    }

    public void postList(List<CodeTable> list, String[] fieldName) throws Exception {
        restService.postList(list, fieldName);
    }

    public void putList(List<CodeTable> list, String[] fieldName) throws Exception {
        restService.putList(list, fieldName);
    }

    public void saveList(List<CodeTable> list, String[] fieldName) throws Exception {
        restService.saveList(list, fieldName);
    }

    public void deleteList(List<CodeTable> list) throws Exception {
        restService.deleteList(list);
    }

}
