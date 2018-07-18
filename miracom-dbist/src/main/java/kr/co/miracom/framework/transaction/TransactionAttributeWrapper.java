package kr.co.miracom.framework.transaction;

import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;

import kr.co.miracom.framework.util.ValueUtils;

public class TransactionAttributeWrapper implements TransactionAttribute {
    private TransactionAttribute transactionAttribute;

    public TransactionAttributeWrapper(TransactionAttribute transactionAttribute) {
        this.transactionAttribute = transactionAttribute;
    }

    public int getPropagationBehavior() {
        return transactionAttribute.getPropagationBehavior();
    }

    public int getIsolationLevel() {
        return transactionAttribute.getIsolationLevel();
    }

    public int getTimeout() {
        return transactionAttribute.getTimeout();
    }

    public boolean isReadOnly() {
        return transactionAttribute.isReadOnly();
    }

    public String getName() {
        return transactionAttribute.getName();
    }

    public String getQualifier() {
        return transactionAttribute.getQualifier();
    }

    public boolean rollbackOn(Throwable ex) {
        if (ex == null)
            return transactionAttribute.rollbackOn(ex);
        if (transactionAttribute instanceof RuleBasedTransactionAttribute) {
            RuleBasedTransactionAttribute rbta = (RuleBasedTransactionAttribute) transactionAttribute;
            if (!ValueUtils.isEmpty(rbta.getRollbackRules()))
                return transactionAttribute.rollbackOn(ex);
        }
        return true;
    }
}
