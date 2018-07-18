package kr.co.miracom.mes.ras.resource.simple.tool.service;

import java.util.List;

import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.exception.BizException;
import kr.co.miracom.framework.service.RestService;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.Property;
import kr.co.miracom.mes.ras.model.MRasEqpTol;
import kr.co.miracom.mes.ras.model.MRasTolDef;
import kr.co.miracom.mes.ras.resource.simple.tool.model.Tool;
import kr.co.miracom.mes.ras.resource.simple.tool.model.ToolDetail;

/**
 * Setup Service: Tool
 *
 * @author hhk
 * @since 2018. 07. 03.
 */
public class ToolService {
    private RestService<MRasTolDef, Tool, ToolDetail> restService = new RestService<>(MRasTolDef.class, Tool.class, ToolDetail.class);

    public ToolDetail get(String id, SelectOptions options) throws Exception {
        return restService.get(id, options);
    }

    public void post(String id, ToolDetail data, String[] fieldName) throws Exception {
        restService.post(id, data, fieldName);
    }

    public void put(String id, ToolDetail data, String[] fieldName) throws Exception {
        restService.put(id, data, fieldName);
    }

    public void delete(String id, ToolDetail data) throws Exception {
        restService.delete(id, data);
    }

    public void postList(List<Tool> list, String[] fieldName) throws Exception {
        restService.postList(list, fieldName);
    }

    public void putList(List<Tool> list, String[] fieldName) throws Exception {
        restService.putList(list, fieldName);
    }

    public void saveList(List<Tool> list, String[] fieldName) throws Exception {
        restService.saveList(list, fieldName);
    }

    public void deleteList(List<Tool> list) throws Exception {

        List<MRasEqpTol> eqptolList;
        {
            for (Tool tool : list) {
                Query query = new Query();
                query.addFilter("factoryCode", AuthUtils.getFactoryCode());
                query.addFilter("toolCode", tool.getToolCode());
                eqptolList = DbUtils.selectList(MRasEqpTol.class, query);
                if(eqptolList.size() > 0)
                    throw new BizException("MIC-00102");
            }
        }
        
        restService.deleteList(list);
    }

}
