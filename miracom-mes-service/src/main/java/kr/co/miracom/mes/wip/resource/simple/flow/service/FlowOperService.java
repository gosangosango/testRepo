package kr.co.miracom.mes.wip.resource.simple.flow.service;

import java.util.ArrayList;
import java.util.List;

import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.service.RestService4OneToMany;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.mes.wip.model.MWipFlwDef;
import kr.co.miracom.mes.wip.model.MWipFlwOpr;
import kr.co.miracom.mes.wip.resource.simple.flow.model.FlowOper;

/**
 * Setup Service: Flow
 * @author myjung.jung
 * @since 2018. 06. 05.
 */
public class FlowOperService {
    private RestService4OneToMany<MWipFlwDef, MWipFlwOpr, FlowOper> restService = new RestService4OneToMany<>(
                    MWipFlwDef.class, MWipFlwOpr.class, FlowOper.class);

    public GetListOut<FlowOper> getList(String id) throws Exception {
        return restService.getList(id);
    }

    public void saveList(String id, List<FlowOper> list, String[] fieldName) throws Exception {
        restService.saveList(id, list, fieldName);

        MWipFlwDef flow = DbUtils.select(MWipFlwDef.class, id,
                        new SelectOptions().addUnselect("firstOper", "lastOper"));

        flow.setFirstOperCode(null);
        flow.setLastOperCode(null);

        List<MWipFlwOpr> operList = DbUtils.selectOneToManyList(MWipFlwOpr.class, flow, MWipFlwOpr.class,
                        new Query().addUnselect("oper", "prevOper", "nextOper"));

        List<MWipFlwOpr> uOperList = new ArrayList<>();
        int size = operList.size();
        for (int i = 0; i < size; i++) {
            MWipFlwOpr oper = operList.get(i);
            if (i == 0) {
                flow.setFirstOperCode(oper.getOperCode());
                if (oper.getPrevOperCode() != null) {
                    oper.setPrevOperCode(null);
                    if (!uOperList.contains(oper)) {
                        uOperList.add(oper);
                    }
                }
            }

            if (i == size - 1) {
                flow.setLastOperCode(oper.getOperCode());
                if (oper.getNextOperCode() != null) {
                    oper.setNextOperCode(null);
                    if (!uOperList.contains(oper)) {
                        uOperList.add(oper);
                    }
                }
            } else {
                MWipFlwOpr nextOper = operList.get(i + 1);
                if (nextOper.getPrevOperCode() == null || !nextOper.getPrevOperCode().equals(oper.getOperCode())) {
                    nextOper.setPrevOperCode(oper.getOperCode());
                    if (!uOperList.contains(nextOper)) {
                        uOperList.add(nextOper);
                    }
                }
                if (oper.getNextOperCode() == null || !oper.getNextOperCode().equals(nextOper.getOperCode())) {
                    oper.setNextOperCode(nextOper.getOperCode());
                    if (!uOperList.contains(oper)) {
                        uOperList.add(oper);
                    }
                }
            }
        }

        DbUtils.updateBatch(uOperList, new String[] { "prevOperCode", "nextOperCode" });
        DbUtils.update(flow, new String[] { "firstOperCode", "lastOperCode" });
    }

    // public void postList(String flowCode, List<Oper> list, String[] fieldName) throws Exception {
    // DbUtils.select(MWipFlwDef.class, flowCode, new SelectOptions().addSelect("flowCode"), MWipFlwDef.class, true);
    //
    // int newSize = list == null ? 0 : list.size();
    // List<MWipFlwOpr> operList = getOperList(flowCode);
    // int oldSize = operList.size();
    //
    // // 이미 추가된 operCode Set
    // Set<String> set = new HashSet<>();
    // // 삭제할 대상
    // List<MWipFlwOpr> dlist = new ArrayList<>();
    // // 수정할 대상
    // List<MWipFlwOpr> ulist = new ArrayList<>();
    // // 추가할 대상
    // List<MWipFlwOpr> ilist = new ArrayList<>();
    //
    // String prevOperCode = null;
    //
    // for (int i = 0; i < oldSize;) {
    // MWipFlwOpr oper = operList.get(i++);
    //
    // // 같은 플로우에 같은 공정이 두 개 있을 수 없음
    // if (set.contains(oper.getOperCode())) {
    // dlist.add(oper);
    // }
    // set.add(oper.getOperCode());
    //
    // MWipFlwOpr changed = null;
    // if (oper.getSeqNo() != i) {
    // oper.setSeqNo(i);
    // changed = oper;
    // }
    //
    // // prevOperCode 확인
    // if (i == 1) {
    // if (!ValueUtils.isEmpty(oper.getPrevOperCode())) {
    // oper.setPrevOperCode(null);
    // changed = oper;
    // }
    // } else {
    // if (!prevOperCode.equals(oper.getPrevOperCode())) {
    // oper.setPrevOperCode(prevOperCode);
    // changed = oper;
    // }
    // }
    //
    // // nextOperCode 확인
    // if (oldSize > i) {
    // MWipFlwOpr nextOper = operList.get(i);
    // if (!nextOper.getOperCode().equals(oper.getNextOperCode())) {
    // oper.setNextOperCode(nextOper.getOperCode());
    // changed = oper;
    // }
    // } else {
    // if (newSize > 0) {
    // oper.setNextOperCode(list.get(0).getOperCode());
    // changed = oper;
    // } else if (!ValueUtils.isEmpty(oper.getNextOperCode())) {
    // oper.setNextOperCode(null);
    // changed = oper;
    // }
    // }
    //
    // if (changed != null) {
    // ulist.add(changed);
    // }
    //
    // prevOperCode = oper.getOperCode();
    // }
    //
    // for (int i = 0; i < newSize;) {
    // Oper item = list.get(i++);
    //
    // // 같은 플로우에 같은 공정이 두 개 있을 수 없음
    // if (set.contains(item.getOperCode())) {
    // throw new BizException("XXXXXX");
    // }
    // set.add(item.getOperCode());
    //
    // MWipFlwOpr oper = new MWipFlwOpr();
    // oper.setFactoryCode(AuthUtils.getFactoryCode());
    // oper.setFlowCode(flowCode);
    // oper.setOperCode(item.getOperCode());
    // oper.setSeqNo(oldSize + i);
    // oper.setPrevOperCode(prevOperCode);
    // if (newSize > i) {
    // Oper nextOper = list.get(i);
    // oper.setNextOperCode(nextOper.getOperCode());
    // }
    // ilist.add(oper);
    //
    // prevOperCode = item.getOperCode();
    // }
    //
    // DbUtils.deleteBatch(dlist);
    // DbUtils.updateBatch(ulist);
    // DbUtils.insertBatch(ilist, fieldName);
    // }
    //
    // public void deleteList(String flowCode, List<Oper> list) throws Exception {
    // if (ValueUtils.isEmpty(list)) {
    // return;
    // }
    // List<String> operCodes = new ArrayList<>();
    // for (Oper oper : list) {
    // operCodes.add(oper.getOperCode());
    // }
    // if (ValueUtils.isEmpty(operCodes)) {
    // return;
    // }
    //
    // Query query = new Query();
    // query.addFilter("factoryCode", AuthUtils.getFactoryCode());
    // query.addFilter("flowCode", flowCode);
    // query.addFilter("operCode", operCodes);
    // DbUtils.deleteList(MWipFlwOpr.class, query);
    //
    // // 데이터 보정
    // postList(flowCode, null, null);
    // }
    //
    // public void up(String flowCode, String operCode, Oper oper) throws Exception {
    // // 데이터 보정
    // postList(flowCode, null, null);
    //
    // List<MWipFlwOpr> list = getOperList(flowCode);
    // List<MWipFlwOpr> ulist = new ArrayList<>();
    // MWipFlwOpr prevPrevOper = null;
    // MWipFlwOpr prevOper = null;
    // int i = 0;
    // for (MWipFlwOpr item : list) {
    // i++;
    // if (item.getOperCode().equals(operCode)) {
    // // 이전 공정이 없으면 바로 끝
    // if (prevOper == null) {
    // break;
    // }
    //
    // MWipFlwOpr nextOper = i < list.size() ? list.get(i) : null;
    //
    // ulist.add(item);
    //
    // // 이전이전 공정과 Up 공정과 관계
    // if (prevPrevOper != null) {
    // item.setPrevOperCode(prevPrevOper.getOperCode());
    // prevPrevOper.setNextOperCode(item.getOperCode());
    // ulist.add(prevPrevOper);
    // }
    //
    // // 이전 공정과 Up 공정과 관계
    // item.setNextOperCode(prevOper.getOperCode());
    // prevOper.setPrevOperCode(item.getOperCode());
    // ulist.add(prevOper);
    //
    // // 다음 공정과 이전 공정과 관계
    // if (nextOper == null) {
    // prevOper.setNextOperCode(null);
    // } else {
    // prevOper.setNextOperCode(nextOper.getOperCode());
    // nextOper.setPrevOperCode(prevOper.getOperCode());
    // ulist.add(nextOper);
    // }
    //
    // break;
    // }
    //
    // prevPrevOper = prevOper;
    // prevOper = item;
    // }
    //
    // DbUtils.updateBatch(ulist);
    // }
    //
    // public void down(String flowCode, String operCode, Oper oper) throws Exception {
    // // 데이터 보정
    // postList(flowCode, null, null);
    //
    // List<MWipFlwOpr> list = getOperList(flowCode);
    // List<MWipFlwOpr> ulist = new ArrayList<>();
    // MWipFlwOpr prevOper = null;
    // int i = 0;
    // for (MWipFlwOpr item : list) {
    // i++;
    // if (item.getOperCode().equals(operCode)) {
    // MWipFlwOpr nextOper = i < list.size() ? list.get(i) : null;
    // // 다음 공정이 없으면 바로 끝
    // if (nextOper == null) {
    // break;
    // }
    //
    // i++;
    // MWipFlwOpr nextNextOper = i < list.size() ? list.get(i) : null;
    //
    // ulist.add(item);
    //
    // // 이후이후 공정과 Down 공정과 관계
    // if (nextNextOper != null) {
    // item.setNextOperCode(nextNextOper.getOperCode());
    // nextNextOper.setPrevOperCode(item.getOperCode());
    // ulist.add(nextNextOper);
    // }
    //
    // // 이후 공정과 Down 공정과 관계
    // item.setPrevOperCode(nextOper.getOperCode());
    // nextOper.setNextOperCode(item.getOperCode());
    // ulist.add(nextOper);
    //
    // // 다음 공정과 이전 공정과 관계
    // prevOper.setNextOperCode(nextOper.getOperCode());
    // nextOper.setPrevOperCode(prevOper.getOperCode());
    // ulist.add(nextOper);
    //
    // break;
    // }
    //
    // prevOper = item;
    // }
    //
    // DbUtils.updateBatch(ulist);
    // }
    //
    // private static List<MWipFlwOpr> getOperList(String flowCode) throws Exception {
    // MWipFlwDef flow = new MWipFlwDef();
    // flow.setFlowCode(flowCode);
    // List<MWipFlwOpr> operList = DbUtils.selectOneToManyList(MWipFlwOpr.class, flow, MWipFlwOpr.class,
    // new Query().addOrder("seqNo", true));
    // return operList;
    // }

}
