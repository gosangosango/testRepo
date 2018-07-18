package kr.co.miracom.mes.wip.resource.simple.component.service.get_list;

import kr.co.miracom.framework.service.GetListIn;
import lombok.Getter;
import lombok.Setter;

/**
 * Input V O: Component
 * @author myjung.jung
 * @since 2018. 06. 26.
 */
@Getter
@Setter
public class GetComponentListIn extends GetListIn {
    private String matCode;
    // private String childMatCode;
    // private int seqNo;
    // private double componentQty;
    // @JsonDeserialize(using = DateStrDeserializer.class)
    // private String applyStartDate;
    // @JsonDeserialize(using = DateStrDeserializer.class)
    // private String applyEndDate;
    private boolean applyOnlyFlag;
    // private String createUserId;
    // private ZonedDateTime createTime;
    // private String updateUserId;
    // private ZonedDateTime updateTime;
}
