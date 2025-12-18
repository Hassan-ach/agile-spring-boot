package com.ensa.agile.application.epic.usecase;

import com.ensa.agile.application.epic.mapper.EpicResponseMapper;
import com.ensa.agile.application.epic.request.EpicRequest;
import com.ensa.agile.application.epic.response.EpicResponse;
import com.ensa.agile.application.global.useCase.BaseUseCase;
import com.ensa.agile.domain.epic.repository.EpicRepository;

public class LoadEpicUseCase extends BaseUseCase<EpicRequest, EpicResponse> {
    private final EpicRepository epicRepository;
    public LoadEpicUseCase(EpicRepository epr) {
        super(null);
        this.epicRepository = epr;
    }

    public EpicResponse execute(EpicRequest req) {

        return EpicResponseMapper.toResponse(
            this.epicRepository.loadEpicRowsById(req.getEpicId()));
    }
}
