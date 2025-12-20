package com.ensa.agile.application.epic.usecase;

import com.ensa.agile.application.epic.mapper.EpicResponseMapper;
import com.ensa.agile.application.epic.request.EpicRequest;
import com.ensa.agile.application.epic.response.EpicResponse;
import com.ensa.agile.application.global.transaction.ITransactionalWrapper;
import com.ensa.agile.application.global.usecase.BaseUseCase;
import com.ensa.agile.domain.epic.repository.EpicRepository;
import org.springframework.stereotype.Component;

@Component
public class GetEpicUseCase extends BaseUseCase<EpicRequest, EpicResponse> {
    private final EpicRepository epicRepository;

    public GetEpicUseCase(ITransactionalWrapper tr,
                          EpicRepository epicRepository) {
        super(tr);
        this.epicRepository = epicRepository;
    }

    @Override
    public EpicResponse execute(EpicRequest req) {

        return EpicResponseMapper.toResponse(
            this.epicRepository.findById(req.getEpicId()));
    }

    // private EpicResponse build(Epic epic) {
    //     return EpicResponseMapper.toResponse(
    //         epic, this.userStoryRepository.findAllByEpicId(epic.getId())
    //                   .stream()
    //                   .map(UserStoryResponseMapper::toResponse)
    //                   .toList());
    // }
}
