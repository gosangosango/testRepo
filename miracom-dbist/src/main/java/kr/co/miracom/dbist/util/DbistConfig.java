package kr.co.miracom.dbist.util;

import java.time.ZoneId;

import lombok.Data;

@Data
public class DbistConfig {
    private static ThreadLocal<ZoneId> zoneId = new ThreadLocal<>();

    public static ZoneId getZoneId() {
        ZoneId zoneId = DbistConfig.zoneId.get();
        if (zoneId == null) {
            zoneId = ZoneId.systemDefault();
        }
        return zoneId;
    }

    public static void setZoneId(ZoneId zoneId) {
        if (zoneId == null) {
            DbistConfig.zoneId.remove();
            return;
        }
        DbistConfig.zoneId.set(zoneId);
    }
}
