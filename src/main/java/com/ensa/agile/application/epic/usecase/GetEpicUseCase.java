package com.ensa.agile.application.epic.usecase;

import com.ensa.agile.application.epic.response.EpicResponse;
import com.ensa.agile.application.global.transaction.ITransactionalWrapper;
import com.ensa.agile.application.global.useCase.BaseUseCase;
import com.ensa.agile.domain.epic.entity.Epic;
import com.ensa.agile.domain.epic.repository.EpicRepository;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class GetEpicUseCase extends BaseUseCase<String, EpicResponse> {
    private final EpicRepository epicRepository;

    public GetEpicUseCase(ITransactionalWrapper tr,
                          EpicRepository epicRepository) {
        super(tr);
        this.epicRepository = epicRepository;
    }

    @Override
    public EpicResponse execute(String epicId) {
        Epic epic = epicRepository.findById(epicId);

        return EpicResponse.builder()
            .id(epic.getId())
            .title(epic.getTitle())
            .description(epic.getDescription())
            .userStories(new ArrayList<>()) // Assuming user stories
                                            // are to be added later
            .build();
    }
}
