package kr.co.miracom.mes.wip.model.subtype;

import kr.co.miracom.dbist.annotation.DbistTable;
import lombok.Data;

@DbistTable(name = "MWIPFLWDEF")
@Data
public class WipFlwDef {
    private String flowCode;
    private String flowDesc;
}
