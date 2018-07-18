/**
 * Copyright (c) 1998-2018 Miracom Inc. All rights reserved.
 *
 * Don't copy or redistribute this source code without permission.
 * This software is provided "As Is" and any expressed or implied
 * warranties, including, but not limited to, the implied warranties of
 * merchantability and fitness for a particular purpose are disclaimed.
 * In no event shall Miracom Inc. or its contributors be liable for any
 * direct, indirect, incidental, special, exemplary, or consequential
 * damages including, but not limited to, procurement of substitute
 * goods or services; loss of use, data, or profits; or business
 * interruption) however caused and on any theory of liability, whether
 * in contract, strict liability, or tort (including negligence or otherwise)
 * arising in any way out of the use of this software, even if advised
 * of the possibility of such damage.
 *
 * For more information on this product, please see
 * http://www.miracom.co.kr
 */
package kr.co.miracom.mes.inv.resource.simple.ret_mat.service.ret_list;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import kr.co.miracom.dbist.dml.Query;
import kr.co.miracom.framework.exception.BizException;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.Property;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.inv.model.MInvLotSts;
import kr.co.miracom.mes.inv.model.MInvRetMat;
import kr.co.miracom.mes.inv.resource.simple.ret_mat.model.RetMat;
import kr.co.miracom.mes.inv.util.InvUtils;
import kr.co.miracom.mes.wip.model.MWipOprDef;

/**
 * @author mo21.kim
 *
 */
public class RetMatList {
    
    public RetMatListOut ret(RetMatListIn list) throws Exception {
        String factory = AuthUtils.getFactoryCode();
        
        for(RetMat item : list.getList()) {            
            //기 반납처리 여부 확인
            if(item.getFromOperCode().equals(item.getToOperCode())) {
                throw new BizException("XXXXX", "이미 반납처리 되었습니다.", new Property("invLotId", item.getInvLotId()));
            }
            //반납창고 창고공정인지 확인
            Query Query = new Query();
            Query.setFilter("factoryCode", factory);
            Query.setFilter("operCode", item.getToOperCode());            
            MWipOprDef oper = DbUtils.select(MWipOprDef.class, Query);
            if(!oper.isStoreFlag()) {
                throw new BizException("XXXXX", "창고 공정이 아닙니다.", new Property("operCode", item.getToOperCode()));
            }
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss"); 
            Date now = Calendar.getInstance().getTime();             
            String retTimeStr = sdf.format(now);
            
            item.setRetTimeStr(retTimeStr);
            item.setRetDate(retTimeStr.substring(0, 8));
            item.setFactoryCode(factory);
            
            //int lot 정보 수정
            Query lotQuery = new Query();
            lotQuery.setFilter("factoryCode", factory);
            lotQuery.setFilter("invLotId", item.getInvLotId());
            
            MInvLotSts invLot = DbUtils.select(MInvLotSts.class, lotQuery);            
            invLot.setInputOrderNo(null);
            invLot.setOperCode(item.getToOperCode());            
            invLot.setLastTranCode("RETURN");
            invLot.setLastTranTime(ValueUtils.getZonedDateTime());
            DbUtils.update(invLot);
            
            //history 저장
            InvUtils.insertInvLotHistory("RETURN", invLot);
            //MInvRetMat 테이블에 저장
            MInvRetMat retMat = new MInvRetMat();            
            ValueUtils.populate(item, retMat);
            DbUtils.insert(retMat);
        }        
        return new RetMatListOut();
    }
}
