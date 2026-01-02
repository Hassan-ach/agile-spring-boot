package com.ensa.agile.application.task.usecase;

import com.ensa.agile.application.global.transaction.ITransactionalWrapper;
import com.ensa.agile.application.global.usecase.BaseUseCase;
import com.ensa.agile.application.task.request.TaskUpdateRequest;
import com.ensa.agile.application.task.response.TaskResponse;

public class UpdateTaskUseCase
    extends BaseUseCase<TaskUpdateRequest, TaskResponse> {

    public UpdateTaskUseCase(ITransactionalWrapper tr) { super(tr); }

    @Override
    public TaskResponse execute(TaskUpdateRequest request) {
        // TODO Auto-generated method stub
        return null;
    }
}
