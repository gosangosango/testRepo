package kr.co.miracom.mes.inv.resource.simple.ret_mat.service.ret_list;

import java.util.List;

import kr.co.miracom.mes.inv.resource.simple.ret_mat.model.RetMat;
import lombok.Data;

@Data
public class RetMatListIn {    
    private List<RetMat> list;   
}
