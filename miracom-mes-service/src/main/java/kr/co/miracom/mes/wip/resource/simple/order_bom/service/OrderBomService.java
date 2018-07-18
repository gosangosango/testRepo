package kr.co.miracom.mes.wip.resource.simple.order_bom.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.exception.BizException;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.service.RestService;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.Property;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.wip.model.MWipBomDef;
import kr.co.miracom.mes.wip.model.MWipBomOpr;
import kr.co.miracom.mes.wip.model.MWipMatDef;
import kr.co.miracom.mes.wip.model.MWipOrdBom;
import kr.co.miracom.mes.wip.model.MWipOrdSts;
import kr.co.miracom.mes.wip.resource.simple.component.model.Component;
import kr.co.miracom.mes.wip.resource.simple.component.service.get_list.GetComponentList;
import kr.co.miracom.mes.wip.resource.simple.component.service.get_list.GetComponentListIn;
import kr.co.miracom.mes.wip.resource.simple.flow.model.FlowOper;
import kr.co.miracom.mes.wip.resource.simple.flow.service.FlowOperService;
import kr.co.miracom.mes.wip.resource.simple.order_bom.model.OrderBom;
import kr.co.miracom.mes.wip.resource.simple.order_bom.model.OrderBomDetail;

/**
 * Setup Service: OrderBom
 * @author myjung.jung
 * @since 2018. 06. 26.
 */
public class OrderBomService {
    private RestService<MWipOrdSts, OrderBom, OrderBomDetail> restService = new RestService<>(MWipOrdSts.class,
                    OrderBom.class, OrderBomDetail.class);

    public OrderBomDetail get(String id, SelectOptions options) throws Exception {
        OrderBomDetail data = restService.get(id, options);
        Query query = new Query(0, 1);
        query.addFilter("factoryCode", AuthUtils.getFactoryCode());
        query.addFilter("orderNo", data.getOrderNo());
        MWipOrdBom comp = DbUtils.select(MWipOrdBom.class, query);
        if (comp != null) {
            data.setApplyDate(comp.getApplyDate());
        }
        return data;
    }

    public void post(String id, OrderBomDetail data, String[] fieldName) throws Exception {
        // restService.post(id, data, fieldName);
        {
            Query query = new Query();
            query.addFilter("factoryCode", AuthUtils.getFactoryCode());
            query.addFilter("orderNo", id);
            if (DbUtils.selectSize(MWipOrdBom.class, query) > 0) {
                throw new BizException("MIC-00028", "이미 적용된 BOM이 있습니다.", new Property("orderNo", id));
            }
        }

        {
            Query query = new Query();
            query.addFilter("factoryCode", AuthUtils.getFactoryCode());
            query.addFilter("matCode", data.getMatCode());
            if (DbUtils.selectSize(MWipBomDef.class, query) == 0) {
                throw new BizException("XXXXXX", "제품에 설정된 BOM이 없습니다.", new Property("matCode", data.getMatCode()));
            }
        }
        GetComponentListIn reqIn = new GetComponentListIn();
        reqIn.setMatCode(data.getMatCode());
        reqIn.setApplyOnlyFlag(true);
        GetListOut<Component> reqOut = BeanUtils.get(GetComponentList.class).getList(reqIn);

        String applyDate = ValueUtils.toDateStr(ValueUtils.getDate(), "yyyyMMdd");

        List<MWipOrdBom> orderCompList = new ArrayList<>();
        for (Component comp : reqOut.getList()) {
            String operCode = null;
            {
                MWipMatDef invMat = DbUtils.select(MWipMatDef.class, comp.getChildMatCode(),
                                new SelectOptions().addSelect("deductionType"), true);
                if ("R".equals(invMat.getDeductionType())) {
                    Set<String> operCodes = new HashSet<>();
                    List<FlowOper> flowOperList = BeanUtils.get(FlowOperService.class).getList(data.getFlowCode())
                                    .getList();
                    for (FlowOper flowOper : flowOperList) {
                        operCodes.add(flowOper.getOperCode());
                    }
                    if (operCodes.isEmpty()) {
                        throw new BizException("MIC-00057", "필수관리자재의 알맞은 투입공정을 찾을 수 없습니다.",
                                        new Property("childMatCode", comp.getChildMatCode()));
                    }

                    Query query = new Query();
                    query.addSelect("operCode");
                    query.addFilter("factoryCode", AuthUtils.getFactoryCode());
                    query.addFilter("matCode", data.getMatCode());
                    query.addFilter("childMatCode", comp.getChildMatCode());
                    query.addFilter("operCode", true);
                    List<MWipBomOpr> operList = DbUtils.selectList(MWipBomOpr.class, query);
                    for (MWipBomOpr oper : operList) {
                        if (!operCodes.contains(oper.getOperCode())) {
                            continue;
                        }
                        operCode = oper.getOperCode();
                    }
                    if (operCode == null) {
                        throw new BizException("MIC-00057", "필수관리자재의 알맞은 투입공정을 찾을 수 없습니다.",
                                        new Property("childMatCode", comp.getChildMatCode()));
                    }
                }
            }

            MWipOrdBom orderComp = ValueUtils.populate(comp, new MWipOrdBom());
            orderComp.setFactoryCode(AuthUtils.getFactoryCode());
            orderComp.setOrderNo(id);
            orderComp.setApplyDate(applyDate);
            orderComp.setOperCode(operCode);
            orderCompList.add(orderComp);
        }
        DbUtils.insertBatch(orderCompList);
    }

    public void put(String id, OrderBomDetail data, String[] fieldName) throws Exception {
        // restService.put(id, data, fieldName);
        post(id, data, fieldName);
    }

    public void delete(String id, OrderBomDetail data) throws Exception {
        // restService.delete(id, data);
    }

    public void postList(List<OrderBom> list, String[] fieldName) throws Exception {
        // restService.postList(list, fieldName);
    }

    public void putList(List<OrderBom> list, String[] fieldName) throws Exception {
        // restService.putList(list, fieldName);
    }

    public void saveList(List<OrderBom> list, String[] fieldName) throws Exception {
        // restService.saveList(list, fieldName);
        postList(list, fieldName);
    }

    public void deleteList(List<OrderBom> list) throws Exception {
        // restService.deleteList(list);
    }

}
