package kr.co.miracom.mes.wip.model.subtype;

import kr.co.miracom.dbist.annotation.DbistTable;
import lombok.Data;

@DbistTable(name = "MWIPMATDEF")
@Data
public class WipMatDef {
    private String matCode;
    private String matDesc;
    private String matShortDesc;
}
