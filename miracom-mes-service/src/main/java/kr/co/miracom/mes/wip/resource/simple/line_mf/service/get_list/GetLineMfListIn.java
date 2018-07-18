package kr.co.miracom.mes.wip.resource.simple.line_mf.service.get_list;

import kr.co.miracom.framework.service.GetListIn;

import lombok.Getter;
import lombok.Setter;

/**
 * Input V O: LineMf
 * @author mo21.kim
 * @since 2018. 07. 03.
 */
@Getter
@Setter
public class GetLineMfListIn extends GetListIn {
     private String matCode;
     private String flowCode;
     private String lineCode;
    // private int seqNo;
    // private String createUserId;
    // @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    // private ZonedDateTime createTime;
    // private String updateUserId;
    // @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    // private ZonedDateTime updateTime;
}
