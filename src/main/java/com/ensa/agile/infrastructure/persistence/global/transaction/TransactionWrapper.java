package com.ensa.agile.infrastructure.persistence.global.transaction;

import com.ensa.agile.application.global.transaction.ITransactionCallBack;
import com.ensa.agile.application.global.transaction.ITransactionalWrapper;
import com.ensa.agile.domain.global.exception.AlreadyExistException;
import com.ensa.agile.domain.global.exception.ApplicationException;
import com.ensa.agile.domain.global.exception.DataBaseTransactionException;
import com.ensa.agile.domain.global.exception.DomainException;
import com.ensa.agile.domain.global.exception.ForbidException;
import com.ensa.agile.domain.global.exception.NotFoundException;
import com.ensa.agile.domain.global.exception.ValidationException;
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
            } catch (AlreadyExistException e) {
                status.setRollbackOnly();
                throw e;
            } catch (ApplicationException e) {
                status.setRollbackOnly();
                throw e;
            } catch (ForbidException e) {
                status.setRollbackOnly();
                throw e;
            } catch (NotFoundException e) {
                status.setRollbackOnly();
                throw e;
            } catch (ValidationException e) {
                status.setRollbackOnly();
                throw e;
            } catch (DomainException e) {
                status.setRollbackOnly();
                throw e;
            } catch (Exception e) {
                status.setRollbackOnly();
                throw new DataBaseTransactionException();
            }
        });
    }
}
