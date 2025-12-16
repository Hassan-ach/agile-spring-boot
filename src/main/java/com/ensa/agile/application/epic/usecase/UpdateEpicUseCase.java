package com.ensa.agile.application.epic.usecase;

import com.ensa.agile.application.epic.mapper.EpicResponseMapper;
import com.ensa.agile.application.epic.request.EpicUpdateRequest;
import com.ensa.agile.application.epic.response.EpicResponse;
import com.ensa.agile.application.global.transaction.ITransactionalWrapper;
import com.ensa.agile.application.global.useCase.BaseUseCase;
import com.ensa.agile.domain.epic.entity.Epic;
import com.ensa.agile.domain.epic.repository.EpicRepository;
import org.springframework.stereotype.Component;

@Component
public class UpdateEpicUseCase
    extends BaseUseCase<EpicUpdateRequest, EpicResponse> {
    private final EpicRepository epicRepository;

    public UpdateEpicUseCase(ITransactionalWrapper tr, EpicRepository ep) {
        super(tr);
        this.epicRepository = ep;
    }

    @Override
    public EpicResponse execute(EpicUpdateRequest request) {

        Epic epic = epicRepository.findById(request.getId());

        return EpicResponseMapper.toResponse(
            epicRepository.save(merge(epic, request)));
    }

    private Epic merge(Epic epic, EpicUpdateRequest request) {
        if (request.getTitle() != null) {
            epic.setTitle(request.getTitle());
        }
        if (request.getDescription() != null) {
            epic.setDescription(request.getDescription());
        }

        return epic;
    }
}
