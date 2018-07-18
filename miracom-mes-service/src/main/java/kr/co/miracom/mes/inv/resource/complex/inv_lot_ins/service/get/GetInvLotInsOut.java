package kr.co.miracom.mes.inv.resource.complex.inv_lot_ins.service.get;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import kr.co.miracom.framework.converter.jackson.DateStrDeserializer;
import kr.co.miracom.framework.converter.jackson.DateStrSerializer;
import lombok.Data;

/**
 * Output V O: InvLotIns
 * @author myjung.jung
 * @since 2018. 06. 25.
 */
@Data
public class GetInvLotInsOut {
    private String id;
    private String invLotId;
    private String operCode;
    private String operDesc;
    private String matCode;
    private String matDesc;
    private boolean invFlag;
    private double qty;
    private boolean inOperFlag;
    private String inputOrderNo;
    @JsonSerialize(using = DateStrSerializer.class)
    private String recvDate;
    private double recvQty;
    private String lastTranCode;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime lastTranTime;
    private boolean holdFlag;
    private String holdCode;
    private String holdDesc;
    private String fromToType;
    private String fromToLotId;
    private String vendorCode;
    private String vendorDesc;
    private String locNo;
    private boolean lotDelFlag;
    private String lotDelCode;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime lotDelTime;
    private String createUserId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime createTime;
    private String updateUserId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime updateTime;
}
