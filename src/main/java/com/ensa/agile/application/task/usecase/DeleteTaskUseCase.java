package com.ensa.agile.application.task.usecase;

import com.ensa.agile.application.common.response.DeleteResponse;
import com.ensa.agile.application.global.transaction.ITransactionalWrapper;
import com.ensa.agile.application.global.usecase.BaseUseCase;

public class DeleteTaskUseCase extends BaseUseCase<String, DeleteResponse> {

    public DeleteTaskUseCase(ITransactionalWrapper tr) { super(tr); }

    @Override
    public DeleteResponse execute(String request) {
        // TODO Auto-generated method stub
        return null;
    }
}
