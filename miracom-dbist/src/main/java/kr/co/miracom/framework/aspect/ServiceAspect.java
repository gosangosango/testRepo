package kr.co.miracom.framework.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.core.Ordered;

public class ServiceAspect implements Ordered {
    private int order = 2;

    @Override
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Object doAround(final ProceedingJoinPoint point) throws Throwable {
        return point.proceed();
    }
}
