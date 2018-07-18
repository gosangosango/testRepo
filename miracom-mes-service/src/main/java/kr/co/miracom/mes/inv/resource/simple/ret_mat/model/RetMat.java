package kr.co.miracom.mes.inv.resource.simple.ret_mat.model;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import kr.co.miracom.framework.converter.jackson.DateStrDeserializer;
import kr.co.miracom.framework.converter.jackson.DateStrSerializer;

import lombok.Data;

/**
 * Item V O: RetMat
 * @author mo21.kim
 * @since 2018. 07. 06.
 */
@Data
public class RetMat {
    private String id;
    private String factoryCode;
    private String retTimeStr;
    private String invLotId;
    private String matCode;
    @JsonSerialize(using = DateStrSerializer.class)
    @JsonDeserialize(using = DateStrDeserializer.class)
    private String retDate;
    private String orderNo;
    private String fromOperCode;
    private String toOperCode;
    private String retType;
    private String retCode;
    private String retReason;
    private String createUserId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime createTime;
    private String updateUserId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime updateTime;
}
