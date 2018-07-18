package kr.co.miracom.mes.inv.resource.simple.delivery.service.inspect_list;

import java.util.List;

import kr.co.miracom.mes.inv.resource.simple.delivery.model.Delivery;
import lombok.Data;

@Data
public class InspectDeliveryListIn {
    private String operCode;
    private List<Delivery> list;
}
