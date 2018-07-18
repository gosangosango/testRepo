package kr.co.miracom.mes.wip.resource.simple.customer.model;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * Item V O: Customer
 * @author myjung.jung
 * @since 2018. 06. 14.
 */
@Data
public class Customer {
    private String id;
    private String customerCode;
    private String customerDesc;
    private String createUserId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime createTime;
    private String updateUserId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime updateTime;
}
