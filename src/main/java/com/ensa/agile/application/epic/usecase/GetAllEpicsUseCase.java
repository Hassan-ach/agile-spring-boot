package com.ensa.agile.application.epic.usecase;

import java.util.List;

import com.ensa.agile.application.epic.mapper.EpicResponseMapper;
import com.ensa.agile.application.epic.response.EpicResponse;
import com.ensa.agile.application.global.transaction.ITransactionalWrapper;
import com.ensa.agile.application.global.usecase.BaseUseCase;
import com.ensa.agile.domain.epic.repository.EpicRepository;

public class GetAllEpicsUseCase
    extends BaseUseCase<String, List<EpicResponse>> {

    private final EpicRepository epicRepository;

    public GetAllEpicsUseCase(ITransactionalWrapper tr, EpicRepository epr) {
        super(tr);
        this.epicRepository = epr;
    }

    @Override
    public List<EpicResponse> execute(String productId) {

        return epicRepository.findAllByProductBackLogId(productId)
            .stream()
            .map(EpicResponseMapper::toResponse)
            .toList();
    }

    // private EpicResponse build(Epic epic) {
    //     List<UserStoryResponse> us =
    //         this.userStoryRepository.findAllByEpicId(epic.getId())
    //             .stream()
    //             .map(UserStoryResponseMapper::toResponse)
    //             .toList();
    //
    //     return EpicResponseMapper.toResponse(epic, us);
    // }
}
