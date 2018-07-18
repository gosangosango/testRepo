package kr.co.miracom.mes.wip.resource.simple.order_bom.model;

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
public class OrderBom {
    private String id;
    private String orderNo;
    private String orderDesc;
    private String matCode;
    private String matDesc;
    private String matShortDesc;
    private String flowCode;
    @JsonSerialize(using = DateStrSerializer.class)
    @JsonDeserialize(using = DateStrDeserializer.class)
    private String ordDate;
    @JsonSerialize(using = DateStrSerializer.class)
    @JsonDeserialize(using = DateStrDeserializer.class)
    private String applyDate;
}
