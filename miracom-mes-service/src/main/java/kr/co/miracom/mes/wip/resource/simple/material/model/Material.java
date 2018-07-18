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
public class Material {
    private String id;
    private String matCode;
    private String matDesc;
    private String matShortDesc;
    private boolean purchaseInspFlag;
    private boolean importInspFlag;
    private boolean outgoingInspFlag;
    private String unit;
    private String unitDesc;
    private String matType;
    private String matTypeDesc;
    private String matGrp;
    private String matGrpDesc;
    private Double weightNet;
    private Double weightGross;
    private Double volume;
    private Double dimensionHr;
    private Double dimensionVt;
    private Double dimensionHt;
    private String deductionType;
    private String deductionTypeDesc;
    private boolean fifoFlag;
    private Double smallPackQty;
    private Double packQty;
    private Double largePackQty;
    private Double palletQty;
    private Double lotQty;
    private boolean deleteFlag;
    private String deleteUserId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime deleteTime;
    private String createUserId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime createTime;
    private String updateUserId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime updateTime;
}
