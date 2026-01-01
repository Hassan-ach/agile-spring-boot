package com.ensa.agile.application.sprint.usecase;

import com.ensa.agile.application.global.transaction.ITransactionalWrapper;
import com.ensa.agile.application.global.usecase.BaseUseCase;
import com.ensa.agile.application.sprint.exception.SprintBackLogNotFoundException;
import com.ensa.agile.application.sprint.mapper.SprintBacklogResponseMapper;
import com.ensa.agile.application.sprint.request.SprintBackLogGetRequest;
import com.ensa.agile.application.sprint.response.SprintBackLogResponse;
import com.ensa.agile.domain.sprint.repository.SprintBackLogRepository;

public class GetSprintBackLogUseCase
    extends BaseUseCase<SprintBackLogGetRequest, SprintBackLogResponse> {
    private SprintBackLogRepository sprintBackLogRepository;
    public GetSprintBackLogUseCase(
        ITransactionalWrapper tr,
        SprintBackLogRepository sprintBackLogRepository) {
        super(tr);
        this.sprintBackLogRepository = sprintBackLogRepository;
    }

    @Override
    public SprintBackLogResponse execute(SprintBackLogGetRequest request) {
        if (!sprintBackLogRepository.existsById(request.getId())) {
            throw new SprintBackLogNotFoundException();
        }
        return SprintBacklogResponseMapper.toResponse(
            this.sprintBackLogRepository.findById(request.getId()));
    }
}
