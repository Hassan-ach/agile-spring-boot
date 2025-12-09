package com.ensa.agile.application.global.transaction;

public interface ITransactionalWrapper {
  <T> T execute(ITransactionCallBack<T> callback);
}
