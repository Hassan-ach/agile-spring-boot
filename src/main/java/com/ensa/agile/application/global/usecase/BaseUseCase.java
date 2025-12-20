package com.ensa.agile.application.global.useCase;

import com.ensa.agile.application.global.transaction.ITransactionCallBack;
import com.ensa.agile.application.global.transaction.ITransactionalWrapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class BaseUseCase<T, D> implements IBaseUseCase<T, D> {
    private final ITransactionalWrapper transactionalWrapper;

    public D executeTransactionally(T data) {
        return transactionalWrapper.execute(new ITransactionCallBack<D>() {
            @Override
            public D execution() {
                return execute(data);
            }
        });
    }
}
