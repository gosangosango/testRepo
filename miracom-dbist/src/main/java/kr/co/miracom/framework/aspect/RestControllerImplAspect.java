package kr.co.miracom.framework.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

import kr.co.miracom.dbist.util.DbistConfig;
import kr.co.miracom.framework.util.AuthUtils;
import kr.co.miracom.framework.util.ThreadUtils;

public class RestControllerImplAspect {
    public Object doAround(final ProceedingJoinPoint point) throws Throwable {
        try {
            DbistConfig.setZoneId(AuthUtils.getZoneId());
            ThreadUtils.initProps();
            return point.proceed();
        } finally {
            ThreadUtils.removeProps();
            DbistConfig.setZoneId(null);
        }
    }
}
