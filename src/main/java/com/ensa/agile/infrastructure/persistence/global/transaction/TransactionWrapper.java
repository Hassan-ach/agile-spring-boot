package com.ensa.agile.infrastructure.persistence.global.transaction;

import com.ensa.agile.application.global.ITransactionCallBack;
import com.ensa.agile.application.global.ITransactionalWrapper;
import com.ensa.agile.domain.global.exception.DataBaseException;
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
        return callBack.execute();
      } catch (Exception e) {
        status.setRollbackOnly();
        throw new DataBaseException();
      }
    });
  }
}
