package kr.co.miracom.mes.wip.resource.complex.lot_req_ins.service.get;

import java.util.List;

import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.exception.BizException;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.bas.resource.simple.code_data.model.CodeData;
import kr.co.miracom.mes.bas.resource.simple.code_data.service.get_list.GetCodeDataList;
import kr.co.miracom.mes.bas.resource.simple.code_data.service.get_list.GetCodeDataListIn;
import kr.co.miracom.mes.wip.resource.complex.lot_req_ins.model.MatReqIns;

/**
 * Get Service: LotReqIns
 * @author gom
 * @since 2018. 07. 05.
 */
public class GetLotReqIns {
    public GetLotReqInsOut get(GetLotReqInsIn input) throws Exception {
        
        ValueUtils.checkNotEmpty(input, "fromDate", "toDate");
        
        Query query = new Query();
        query.setFilter("factoryCode", AuthUtils.getFactoryCode());
        query.setFilter("fromDate", input.getFromDate());
        query.setFilter("toDate", input.getToDate());
        query.setFilter("lineCode", input.getLineCode());            
        query.setFilter("matCode", input.getMatCode());            
        
        if ( !ValueUtils.isEmpty(input.getOrderNo()) ) {
            
            query.setFilter("fromDate", null);
            query.setFilter("toDate", null);
            query.setFilter("lineCode", null);            
            query.setFilter("matCode", null);
            query.setFilter("orderNo", input.getOrderNo());            
            
        } else {
            
            query.setFilter("orderNo", null);
            
        }
        
        GetCodeDataListIn inputGcm = new GetCodeDataListIn();
        inputGcm.setTableName("INSP_TYPE");
        inputGcm.setKey1("OQC");
        
        GetListOut codeData = BeanUtils.get(GetCodeDataList.class).getList(inputGcm);
       
        if ( codeData == null ) {
            
            throw new BizException("OQC 유형이 정의되지 않았습니다.");
               
        }   
          
        
        CodeData data = (CodeData) codeData.getList().get(0);
        
        query.setFilter("labelType", data.getData1());
        
        List<MatReqIns> list = DbUtils.selectList(DbUtils.toSqlPath(GetLotReqIns.class, "select_request_inspection_list.sql"), query, MatReqIns.class);
        
        GetLotReqInsOut output = new GetLotReqInsOut();
        output.setList(list);
        
        return output;
    }
}
