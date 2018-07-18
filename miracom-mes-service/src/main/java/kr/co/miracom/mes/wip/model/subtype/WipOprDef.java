package kr.co.miracom.mes.wip.model.subtype;

import kr.co.miracom.dbist.annotation.DbistTable;
import lombok.Data;

@DbistTable(name = "MWIPOPRDEF")
@Data
public class WipOprDef {
    private String operCode;
    private String operDesc;
}
