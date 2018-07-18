package kr.co.miracom.framework.transaction;

import java.lang.reflect.Method;

import org.springframework.transaction.interceptor.TransactionAttribute;

@SuppressWarnings("serial")
public class AnnotationTransactionAttributeSource
                extends org.springframework.transaction.annotation.AnnotationTransactionAttributeSource {
    @Override
    public TransactionAttribute getTransactionAttribute(Method method, Class<?> targetClass) {
        TransactionAttribute ta = super.getTransactionAttribute(method, targetClass);
        if (ta != null)
            ta = new TransactionAttributeWrapper(ta);
        return ta;
    }
}
