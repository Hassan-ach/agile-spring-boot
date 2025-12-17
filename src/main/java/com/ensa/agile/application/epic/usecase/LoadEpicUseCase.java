package com.ensa.agile.application.epic.usecase;

import com.ensa.agile.application.epic.request.EpicRequest;
import com.ensa.agile.application.epic.response.EpicResponse;
import com.ensa.agile.application.global.useCase.BaseUseCase;

public class LoadEpicUseCase extends BaseUseCase<EpicRequest, EpicResponse> {
    public LoadEpicUseCase() { super(null); }

    public EpicResponse execute(EpicRequest req) { return null; }
}
