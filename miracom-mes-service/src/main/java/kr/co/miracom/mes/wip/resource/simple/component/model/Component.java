package kr.co.miracom.mes.wip.resource.simple.component.model;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import kr.co.miracom.framework.converter.jackson.DateStrDeserializer;
import kr.co.miracom.framework.converter.jackson.DateStrSerializer;
import lombok.Data;

/**
 * Item V O: Component
 * @author myjung.jung
 * @since 2018. 06. 26.
 */
@Data
public class Component {
    private String id;
    private String matCode;
    private String childMatCode;
    private String childMatDesc;
    private String childMatShortDesc;
    private int seqNo;
    private double componentQty;
    @JsonSerialize(using = DateStrSerializer.class)
    @JsonDeserialize(using = DateStrDeserializer.class)
    private String applyStartDate;
    @JsonSerialize(using = DateStrSerializer.class)
    @JsonDeserialize(using = DateStrDeserializer.class)
    private String applyEndDate;
    private String createUserId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime createTime;
    private String updateUserId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime updateTime;
}
