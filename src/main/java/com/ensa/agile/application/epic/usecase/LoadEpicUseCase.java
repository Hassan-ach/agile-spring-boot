package com.ensa.agile.application.epic.usecase;

import com.ensa.agile.application.epic.request.EpicGetRequest;
import com.ensa.agile.application.epic.response.EpicResponse;
import com.ensa.agile.application.global.useCase.BaseUseCase;

public class LoadEpicUseCase extends BaseUseCase<EpicGetRequest, EpicResponse> {
    public LoadEpicUseCase() { super(null); }

    public EpicResponse execute(EpicGetRequest req) { return null; }
}
