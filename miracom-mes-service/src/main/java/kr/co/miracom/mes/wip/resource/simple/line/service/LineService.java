package kr.co.miracom.mes.wip.resource.simple.line.service;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.RestService;
import kr.co.miracom.mes.wip.model.MWipLinDef;
import kr.co.miracom.mes.wip.resource.simple.line.model.Line;
import kr.co.miracom.mes.wip.resource.simple.line.model.LineDetail;

/**
 * Setup Service: Line
 * 
 * @author myjung.jung
 * @since 2018. 06. 05.
 */
public class LineService {
    private RestService<MWipLinDef, Line, LineDetail> restService = new RestService<>(MWipLinDef.class, Line.class, LineDetail.class);

    public LineDetail get(String id, SelectOptions options) throws Exception {
        return restService.get(id, options);
    }

    public void post(String id, LineDetail data, String[] fieldName) throws Exception {
        restService.post(id, data, fieldName);
    }

    public void put(String id, LineDetail data, String[] fieldName) throws Exception {
        restService.put(id, data, fieldName);
    }

    public void delete(String id, LineDetail data) throws Exception {
        restService.delete(id, data);
    }

    public void postList(List<Line> list, String[] fieldName) throws Exception {
        restService.postList(list, fieldName);
    }

    public void putList(List<Line> list, String[] fieldName) throws Exception {
        restService.putList(list, fieldName);
    }

    public void saveList(List<Line> list, String[] fieldName) throws Exception {
        restService.saveList(list, fieldName);
    }

    public void deleteList(List<Line> list) throws Exception {
        restService.deleteList(list);
    }

}