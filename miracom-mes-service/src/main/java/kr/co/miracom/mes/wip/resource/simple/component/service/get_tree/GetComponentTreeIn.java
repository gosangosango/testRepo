package kr.co.miracom.mes.wip.resource.simple.component.service.get_tree;

import lombok.Data;

/**
 * Input V O: Component
 * @author myjung.jung
 * @since 2018. 06. 26.
 */
@Data
public class GetComponentTreeIn {
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
