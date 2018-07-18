package kr.co.miracom.mes.ras.resource.simple.sparepart.model;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * Item V O: Spt
 * @author hhk
 * @since 2018. 07. 02.
 */
@Data
public class Sparepart {
    private String id;
    private String partCode;
    private String partDesc;
    private String storeCode;
    private String storeOperDesc;
    private String locNo;
    private String locNoDesc;
    private Double saftyQty;
    private String unit;
    private String unitDesc;
    private boolean stockFlag;
    private Double stockQty;
    private boolean labelFlag;
    private String createUserId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime createTime;
    private String updateUserId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime updateTime;
}
