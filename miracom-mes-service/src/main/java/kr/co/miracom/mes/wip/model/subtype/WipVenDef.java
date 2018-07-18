package kr.co.miracom.mes.wip.model.subtype;

import kr.co.miracom.dbist.annotation.DbistTable;
import lombok.Data;

@DbistTable(name = "MWIPVENDEF")
@Data
public class WipVenDef {
    private String vendorCode;
    private String vendorDesc;
}
