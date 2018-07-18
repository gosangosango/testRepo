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
package kr.co.miracom.mes.ras.model;

import java.time.ZonedDateTime;

import kr.co.miracom.dbist.annotation.DbistRelation;
import kr.co.miracom.mes.bas.model.subtype.GcmTblDat;
import kr.co.miracom.mes.wip.model.subtype.WipOprDef;
import lombok.Data;

/**
 * @author hhk
 *
 */
@Data
public class MRasSptDef {

    // /** 공장. */
    // private Mwipfacdef mwipfacdef;
    private String factoryCode;

    /** Spare Part 코드. */
    private String partCode;

    /** Spare Part명. */
    private String partDesc;

    /** 창고코드. MWIPOPRDEF O0001,O0003 공정테이블*/
    private String storeCode;
    @DbistRelation(field = { "factoryCode", "storeCode" })
    private WipOprDef store;

    /** 적재위치코드. SP-001, SP-002, SP-003 MGCMTBLDAT 코드 데이터 */
    private String locNo;
    @DbistRelation(field = { "factoryCode", "value:LOAD_LOC_CODE", "locNo", "value:NONE", "value:NONE", "value:NONE", "value:NONE" })
    private GcmTblDat loc;

    /** 안전재고수량. */
    private Double saftyQty;

    /** 단위. MGCMTBLDAT EA, PCS, KG 코드 데이터 */
    private String unit;
    @DbistRelation(field = { "factoryCode", "value:UNIT", "unit", "value:NONE", "value:NONE", "value:NONE", "value:NONE" })
    private GcmTblDat unitData;

    /** 재고관리여부. */
    private boolean stockFlag;
    
    /** 재고수량. */
    private Integer stockQty;

    /** 라벨발행여부. */
    private boolean labelFlag;

    /** 생성자 ID. */
    private String createUserId;

    /** 생성일시. */
    private ZonedDateTime createTime;

    /** 수정자 ID. */
    private String updateUserId;

    /** 수정일시. */
    private ZonedDateTime updateTime;

    // /** 설비 BOM 정보 목록. */
    // private Set<Mraseqpbom> mraseqpbomSet;
}
