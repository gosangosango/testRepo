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
import kr.co.miracom.mes.wip.model.subtype.WipMatDef;
import lombok.Data;

/**
 * @author hhk
 *
 */
@Data
public class MRasTolDef {

    private String factoryCode;
    private String toolCode;
    private String toolDesc;
    private String toolType;
    @DbistRelation(field = { "factoryCode", "value:TOOL_TYPE", "toolType", "value:NONE", "value:NONE", "value:NONE", "value:NONE" })
    private GcmTblDat toolTypeData;
    /** MWIPMATDEF 자제,제품하고 relation */
    private String matCode; 
    @DbistRelation(field = { "factoryCode", "value:TOOL_MAT", "matCode", "value:NONE", "value:NONE", "value:NONE", "value:NONE" })
    private GcmTblDat matData;
    private Double oneTimeUsageQty;
    private Double usageQty;
    private Double totalLifeQty;
    private String periodCode;
    @DbistRelation(field = { "factoryCode", "value:PERIOD_CODE", "periodCode", "value:NONE", "value:NONE", "value:NONE", "value:NONE" })
    private GcmTblDat periodData;
    private String createUserId;
    private ZonedDateTime createTime;
    private String updateUserId;
    private ZonedDateTime updateTime;

}
