package kr.co.miracom.mes.wip.model.subtype;

import kr.co.miracom.dbist.annotation.DbistTable;
import lombok.Data;

@DbistTable(name = "MWIPLINDEF")
@Data
public class WipLinDef {
    private String lineCode;
    private String lineDesc;
}
