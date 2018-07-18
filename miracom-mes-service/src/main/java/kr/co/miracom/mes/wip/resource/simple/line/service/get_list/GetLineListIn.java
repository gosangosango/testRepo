package kr.co.miracom.mes.wip.resource.simple.line.service.get_list;

import java.util.List;

import kr.co.miracom.framework.service.GetListIn;
import lombok.Getter;
import lombok.Setter;

/**
 * Input V O: Line
 * @author myjung.jung
 * @since 2018. 06. 05.
 */
@Getter
@Setter
public class GetLineListIn extends GetListIn {
    private String lineCode;
    private String lineDesc;
    // private String shift1Start;
    // private String erpLineCode;
    private List<String> lineGrp;
    // private boolean prodFlag;
    // private String inpMatOperCode;
    // private String toOperCode;
    private boolean includeDeleteFlag;
    // private String deleteUserId;
    // private ZonedDateTime deleteTime;
    // private String createUserId;
    // private ZonedDateTime createTime;
    // private String updateUserId;
    // private ZonedDateTime updateTime;
}
