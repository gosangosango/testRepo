package kr.co.miracom.mes.wip.resource.simple.operation.service.get_list;

import java.util.List;

import kr.co.miracom.framework.service.GetListIn;
import lombok.Getter;
import lombok.Setter;

/**
 * Input V O: Operation
 * @author myjung.jung
 * @since 2018. 06. 05.
 */
@Getter
@Setter
public class GetOperationListIn extends GetListIn {
    private String operCode;
    private String operDesc;
    // private String unit;
    // private boolean startRequireFlag;
    // private boolean pushPullFlag;
    // private boolean erpIfFlag;
    // private String erpOperCode;
    private boolean storeOnlyFlag;
    private boolean operOnlyFlag;
    private List<String> storeGrp;
    private boolean equipSetOnlyFlag;
    // private String createUserId;
    // private ZonedDateTime createTime;
    // private String updateUserId;
    // private ZonedDateTime updateTime;
}
