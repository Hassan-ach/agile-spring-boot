package com.ensa.agile.infrastructure.persistence.global.transaction;

import com.ensa.agile.application.global.transaction.ITransactionCallBack;
import com.ensa.agile.application.global.transaction.ITransactionalWrapper;
import com.ensa.agile.domain.global.exception.DataBaseTransactionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

@RequiredArgsConstructor
@Component
public class TransactionWrapper implements ITransactionalWrapper {

    private final TransactionTemplate transactionTemplate;
    public <T> T execute(ITransactionCallBack<T> callBack) {
        return transactionTemplate.execute(status -> {
            try {
                return callBack.execution();
            } catch (RuntimeException e) {
                status.setRollbackOnly();
                throw e;
            } catch (Exception e) {
                status.setRollbackOnly();
                throw new DataBaseTransactionException();
            }
        });
    }
}
