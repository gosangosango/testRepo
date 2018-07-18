package kr.co.miracom.mes.wip.model.subtype;

import kr.co.miracom.dbist.annotation.DbistTable;
import lombok.Data;

@DbistTable(name = "MWIPCUSDEF")
@Data
public class WipCusDef {
    private String customerCode;
    private String customerDesc;
}
