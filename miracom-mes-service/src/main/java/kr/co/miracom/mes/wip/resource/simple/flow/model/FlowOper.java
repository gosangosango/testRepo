package kr.co.miracom.mes.wip.resource.simple.flow.model;

import java.time.ZonedDateTime;

import lombok.Data;

/**
 * Item V O: Flow
 * @author myjung.jung
 * @since 2018. 06. 05.
 */
@Data
public class FlowOper {
    private String id;
    private String operCode;
    private String operDesc;
    private int seqNo;
    private String createUserId;
    private ZonedDateTime createTime;
    private String updateUserId;
    private ZonedDateTime updateTime;
}
