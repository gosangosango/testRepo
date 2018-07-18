package kr.co.miracom.framework.util;

import java.util.HashMap;
import java.util.Map;

public class ThreadUtils {
    private static ThreadLocal<Map<String, Object>> tprops = new ThreadLocal<>();

    public static boolean isInit() {
        return tprops.get() != null;
    }

    public static void initProps() {
        tprops.set(new HashMap<>());
    }

    public static void removeProps() {
        tprops.remove();
    }

    /**
     * 실행 중인 Thread에서 설정한 name 속성의 값을 반환합니다.<br>
     * ThreadLocal 변수 값 조회
     * @param name
     * @return
     */
    public static Object getProp(String name) {
        Map<String, Object> map = tprops.get();
        if (map == null) {
            return null;
        }
        Object value = map.get(name);
        return value;
    }

    /**
     * 실행 중인 Thread에 name 속성의 값을 설정합니다.<br>
     * ThreadLocal 변수 값 설정
     * @param name
     * @param value
     */
    public static void setProp(String name, Object value) {
        Map<String, Object> map = tprops.get();
        if (map == null) {
            return;
        }
        map.put(name, value);
    }

}
