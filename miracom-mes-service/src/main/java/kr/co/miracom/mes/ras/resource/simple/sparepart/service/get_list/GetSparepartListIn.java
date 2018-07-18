package kr.co.miracom.mes.ras.resource.simple.sparepart.service.get_list;

import kr.co.miracom.framework.service.GetListIn;

import lombok.Getter;
import lombok.Setter;

/**
 * Input V O: Spt
 * @author hhk
 * @since 2018. 07. 02.
 */
@Getter
@Setter
public class GetSparepartListIn extends GetListIn {
     private String partCode;
     private String partDesc;
     private String storeCode;
    // private String locNo;
    // private Double saftyQty;
    // private String unit;
    // private Integer stockFlag;
    // private Integer labelFlag;
    // private String createUserId;
    // private ZonedDateTime createTime;
    // private String updateUserId;
    // private ZonedDateTime updateTime;
}
