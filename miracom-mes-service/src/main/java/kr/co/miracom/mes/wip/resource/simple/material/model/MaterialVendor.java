package kr.co.miracom.mes.wip.resource.simple.material.model;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * Item V O: Material
 * @author myjung.jung
 * @since 2018. 06. 05.
 */
@Data
public class MaterialVendor {
    private String id;
    private String vendorCode;
    private String vendorDesc;
    private String createUserId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime createTime;
    private String updateUserId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime updateTime;
}
