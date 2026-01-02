package com.ensa.agile.application.task.usecase;

import com.ensa.agile.application.global.transaction.ITransactionalWrapper;
import com.ensa.agile.application.global.usecase.BaseUseCase;
import com.ensa.agile.application.task.request.TaskGetRequest;
import com.ensa.agile.application.task.response.TaskResponse;

public class GetTaskUseCase extends BaseUseCase<TaskGetRequest, TaskResponse> {
    public GetTaskUseCase(ITransactionalWrapper tr) { super(tr); }

    @Override
    public TaskResponse execute(TaskGetRequest request) {
        return null;
    }
}
