package kr.co.miracom.framework.util;

import java.time.ZoneId;

public class AuthUtils {
    /**
     * 사용자가 접속 중인 공장 코드를 반환합니다.
     * @return
     * @throws Exception
     */
    public static String getFactoryCode() throws Exception {
        return "MRCKR01";
    }

    /**
     * 현재의 사용자 아이디를 반환합니다.
     * @return
     * @throws Exception
     */
    public static String getUserId() throws Exception {
        return "myjung.jung";
    }

    /**
     * 사용자의 Zone ID를 반환합니다.
     * @return
     */
    public static ZoneId getZoneId() {
        // return ZoneId.of("UTC");
        return ZoneId.of("Asia/Seoul");
    }
}
