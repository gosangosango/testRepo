package kr.co.miracom.mes.config.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RespControllerImplAspect extends kr.co.miracom.framework.aspect.RestControllerImplAspect {
    @Around("execution(* kr.co.miracom.*.*.resource.*.*.controller.*Impl.*(..)) "
                    + "or execution(* kr.co.miracom.*.*.*.resource.*.*.controller.*Impl.*(..)) ")
    @Override
    public Object doAround(final ProceedingJoinPoint point) throws Throwable {
        return super.doAround(point);
    }

}
