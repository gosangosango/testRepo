package kr.co.miracom.mes.ras.resource.simple.equipment.service.get_list;

import java.util.List;

import kr.co.miracom.framework.service.GetListIn;
import lombok.Getter;
import lombok.Setter;

/**
 * Input V O: Equipment
 * @author myjung.jung
 * @since 2018. 06. 11.
 */
@Getter
@Setter
public class GetEquipmentListIn extends GetListIn {
    private String equipCode;
    private String equipDesc;
    private List<String> equipType;
    // private String areaCode;
    private String lineCode;
    // private boolean pmSchFlag;
    // private Integer maxProcCount;
    private boolean includeDeleteFlag;
    // private String deleteUserId;
    // private ZonedDateTime deleteTime;
    // private String createUserId;
    // private ZonedDateTime createTime;
    // private String updateUserId;
    // private ZonedDateTime updateTime;
}
