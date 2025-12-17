package com.ensa.agile.application.epic.usecase;

import com.ensa.agile.application.epic.mapper.EpicResponseMapper;
import com.ensa.agile.application.epic.response.EpicResponse;
import com.ensa.agile.application.global.transaction.ITransactionalWrapper;
import com.ensa.agile.application.global.useCase.BaseUseCase;
import com.ensa.agile.application.product.exception.ProductBackLogNotFoundException;
import com.ensa.agile.application.story.mapper.UserStoryResponseMapper;
import com.ensa.agile.application.story.response.UserStoryResponse;
import com.ensa.agile.domain.epic.entity.Epic;
import com.ensa.agile.domain.epic.repository.EpicRepository;
import com.ensa.agile.domain.product.repository.ProductBackLogRepository;
import com.ensa.agile.domain.story.repository.UserStoryRepository;
import java.util.List;

public class GetAllEpicsUseCase
    extends BaseUseCase<String, List<EpicResponse>> {

    private final ProductBackLogRepository productBackLogRepository;
    private final EpicRepository epicRepository;
    private final UserStoryRepository userStoryRepository;

    public GetAllEpicsUseCase(ITransactionalWrapper tr,
                              ProductBackLogRepository pbr, EpicRepository epr,
                              UserStoryRepository usr) {
        super(tr);
        this.productBackLogRepository = pbr;
        this.epicRepository = epr;
        this.userStoryRepository = usr;
    }

    @Override
    public List<EpicResponse> execute(String productId) {
        if (this.productBackLogRepository.existsById(productId) == false) {
            throw new ProductBackLogNotFoundException();
        }

        return epicRepository.findAllByProductBackLogId(productId)
            .stream()
            .map(EpicResponseMapper::toResponse)
            .toList();
    }

    private EpicResponse build(Epic epic) {
        List<UserStoryResponse> us =
            this.userStoryRepository.findAllByEpicId(epic.getId())
                .stream()
                .map(UserStoryResponseMapper::toResponse)
                .toList();

        return EpicResponseMapper.toResponse(epic, us);
    }
}
