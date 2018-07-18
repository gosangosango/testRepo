package kr.co.miracom.mes.ras.resource.simple.tool.model;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * Item V O: Tool
 * @author hhk
 * @since 2018. 07. 03.
 */
@Data
public class Tool {
    private String id;
    private String toolCode;
    private String toolDesc;
    private String toolType;
    private String toolTypeDesc;
    private String matCode;
    private String matDesc;
    private Double oneTimeUsageQty;
    private Double usageQty;
    private Double totalLifeQty;
    private String periodCode;
    private String periodDesc;
    private String createUserId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime createTime;
    private String updateUserId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    
    private ZonedDateTime updateTime;
    /**
     * @return the remainQty
     */
    public Double getRemainQty() {
        return totalLifeQty - usageQty;
    }
}
