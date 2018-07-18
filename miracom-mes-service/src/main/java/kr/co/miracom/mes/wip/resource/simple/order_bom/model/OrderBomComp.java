package kr.co.miracom.mes.wip.resource.simple.order_bom.model;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import kr.co.miracom.framework.converter.jackson.DateStrDeserializer;
import kr.co.miracom.framework.converter.jackson.DateStrSerializer;
import lombok.Data;

/**
 * Item V O: OrderBom
 * @author myjung.jung
 * @since 2018. 06. 26.
 */
@Data
public class OrderBomComp {
    private String id;
    private String childMatCode;
    private String childMatDesc;
    private String matCode;
    private String matDesc;
    private String matShortDesc;
    private String operCode;
    private String operDesc;
    private double componentQty;
    @JsonSerialize(using = DateStrSerializer.class)
    @JsonDeserialize(using = DateStrDeserializer.class)
    private String applyDate;
    private String createUserId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime createTime;
    private String updateUserId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime updateTime;
}
