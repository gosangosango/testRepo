package kr.co.miracom.mes.ras.model.subtype;

import kr.co.miracom.dbist.annotation.DbistTable;
import lombok.Data;

@DbistTable(name = "MRASEQPDEF")
@Data
public class RasEqpDef {
    private String equipCode;
    private String equipDesc;
}
