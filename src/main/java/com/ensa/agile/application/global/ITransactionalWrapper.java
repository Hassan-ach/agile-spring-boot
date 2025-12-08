package com.ensa.agile.application.global;

public interface ITransactionalWrapper {
  <T> T execute(ITransactionCallBack<T> callback);
}
