package kr.co.miracom.mes.bas.model.subtype;

import kr.co.miracom.dbist.annotation.DbistTable;
import lombok.Data;

@DbistTable(name = "MGCMTBLDAT")
@Data
public class GcmTblDat {
    private String key1;
    private String keyDesc;
}
