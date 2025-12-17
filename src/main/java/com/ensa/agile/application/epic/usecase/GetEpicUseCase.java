package com.ensa.agile.application.epic.usecase;

import com.ensa.agile.application.epic.exception.EpicNotFoundException;
import com.ensa.agile.application.epic.mapper.EpicResponseMapper;
import com.ensa.agile.application.epic.response.EpicResponse;
import com.ensa.agile.application.global.transaction.ITransactionalWrapper;
import com.ensa.agile.application.global.useCase.BaseUseCase;
import com.ensa.agile.application.story.mapper.UserStoryResponseMapper;
import com.ensa.agile.domain.epic.entity.Epic;
import com.ensa.agile.domain.epic.repository.EpicRepository;
import com.ensa.agile.domain.story.repository.UserStoryRepository;
import org.springframework.stereotype.Component;

@Component
public class GetEpicUseCase extends BaseUseCase<String, EpicResponse> {
    private final EpicRepository epicRepository;
    private final UserStoryRepository userStoryRepository;

    public GetEpicUseCase(ITransactionalWrapper tr,
                          EpicRepository epicRepository,
                          UserStoryRepository userStoryRepository) {
        super(tr);
        this.epicRepository = epicRepository;
        this.userStoryRepository = userStoryRepository;
    }

    @Override
    public EpicResponse execute(String epicId) {

        return build(this.epicRepository.findById(epicId));
    }

    private EpicResponse build(Epic epic) {
        return EpicResponseMapper.toResponse(
            epic, this.userStoryRepository.findAllByEpicId(epic.getId())
                      .stream()
                      .map(UserStoryResponseMapper::toResponse)
                      .toList());
    }
}
