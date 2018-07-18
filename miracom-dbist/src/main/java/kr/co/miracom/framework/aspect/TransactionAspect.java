/**
 * Copyright (c) 1998-2012 Miracom Inc. All rights reserved.
 *
 * Don't copy or redistribute this source code without permission.
 * This software is provided "As Is" and any expressed or implied
 * warranties, including, but not limited to, the implied warranties of
 * merchantability and fitness for a particular purpose are disclaimed.
 * In no event shall Miracom Inc. or its contributors be liable for any
 * direct, indirect, incidental, special, exemplary, or consequential
 * damages including, but not limited to, procurement of substitute
 * goods or services; loss of use, data, or profits; or business
 * interruption) however caused and on any theory of liability, whether
 * in contract, strict liability, or tort (including negligence or otherwise)
 * arising in any way out of the use of this software, even if advised
 * of the possibility of such damage.
 *
 * For more information on this product, please see
 * http://www.miracom.co.kr
 */
package kr.co.miracom.framework.aspect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.core.Ordered;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.framework.util.Closure;
import kr.co.miracom.framework.util.SyncCtrlUtils;
import kr.co.miracom.framework.util.ThreadUtils;
import kr.co.miracom.framework.util.ValueUtils;

public class TransactionAspect implements Ordered {
    private int order = 1;
    private TransactionInterceptor defaultTransactionInterceptor;
    private TransactionInterceptor annotationTransactionInterceptor;
    private Map<String, TransactionInterceptor> interceptorMap = new ConcurrentHashMap<String, TransactionInterceptor>();

    @Override
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    private TransactionInterceptor getDefaultTransactionInterceptor() {
        if (defaultTransactionInterceptor == null) {
            defaultTransactionInterceptor = BeanUtils.get("defaultTransactionInterceptor",
                            TransactionInterceptor.class);
        }
        return defaultTransactionInterceptor;
    }

    private TransactionInterceptor getAnnotationTransactionInterceptor() {
        if (annotationTransactionInterceptor == null) {
            annotationTransactionInterceptor = BeanUtils.get("annotationTransactionInterceptor",
                            TransactionInterceptor.class);
        }
        return annotationTransactionInterceptor;
    }

    private static final Field FIELD_METHODINVOCATION;
    static {
        Field methodInvocation;
        try {
            methodInvocation = MethodInvocationProceedingJoinPoint.class.getDeclaredField("methodInvocation");
            methodInvocation.setAccessible(true);
        } catch (SecurityException e) {
            methodInvocation = null;
        } catch (NoSuchFieldException e) {
            methodInvocation = null;
        }
        FIELD_METHODINVOCATION = methodInvocation;
    }

    public Object doAround(final ProceedingJoinPoint point) throws Throwable {
        MethodInvocation invocation = (MethodInvocation) FIELD_METHODINVOCATION.get(point);
        TransactionInterceptor interceptor = getTransactionInterceptor(invocation);
        ThreadUtils.setProp("transactionManager", interceptor.getTransactionManager());
        return interceptor.invoke(invocation);
    }

    private TransactionInterceptor getTransactionInterceptor(MethodInvocation invocation) {
        Method method = invocation.getMethod();
        Class<?> clazz = invocation.getThis().getClass();
        String prefix = (String) ThreadUtils.getProp("miracom.dataSourcePrefix");

        // Annotation 설정이 되어있는지 여부에 따라 다른 Interceptor 를 이용해야 합니다.
        boolean annotation = method.getAnnotation(Transactional.class) != null
                        || clazz.getAnnotation(Transactional.class) != null;

        // if (Databases.isFactoryDataSourceEnabled() && Values.isEmpty(prefix) && !Values.isEmpty(Values.getFactory())
        // && !"SYSTEM".equals(Values.getFactory())) {
        // prefix = Values.getFactory().toLowerCase();
        // }
        if (ValueUtils.isEmpty(prefix)) {
            return annotation ? getAnnotationTransactionInterceptor() : getDefaultTransactionInterceptor();
        }

        final String beanName = prefix + (annotation ? "AnnotationTransactionInterceptor" : "TransactionInterceptor");

        if (interceptorMap.containsKey(beanName))
            return interceptorMap.get(beanName);

        return SyncCtrlUtils.wrap("TransactionAspect." + beanName, interceptorMap, beanName,
                        new Closure<TransactionInterceptor, RuntimeException>() {
                            public TransactionInterceptor execute() throws RuntimeException {
                                return BeanUtils.get(beanName, TransactionInterceptor.class);
                            }
                        });
    }
}
