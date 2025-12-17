package com.ensa.agile.application.epic.usecase;

import com.ensa.agile.application.epic.mapper.EpicResponseMapper;
import com.ensa.agile.application.epic.request.EpicGetRequest;
import com.ensa.agile.application.epic.response.EpicResponse;
import com.ensa.agile.application.global.transaction.ITransactionalWrapper;
import com.ensa.agile.application.global.useCase.BaseUseCase;
import com.ensa.agile.application.product.exception.ProductBackLogNotFoundException;
import com.ensa.agile.application.story.mapper.UserStoryResponseMapper;
import com.ensa.agile.domain.epic.entity.Epic;
import com.ensa.agile.domain.epic.repository.EpicRepository;
import com.ensa.agile.domain.product.repository.ProductBackLogRepository;
import com.ensa.agile.domain.story.repository.UserStoryRepository;
import org.springframework.stereotype.Component;

@Component
public class GetEpicUseCase extends BaseUseCase<EpicGetRequest, EpicResponse> {
    private final EpicRepository epicRepository;
    private final UserStoryRepository userStoryRepository;
    private final ProductBackLogRepository productBackLogRepository;

    public GetEpicUseCase(ITransactionalWrapper tr,
                          EpicRepository epicRepository,
                          UserStoryRepository userStoryRepository,
                          ProductBackLogRepository productBackLogRepository) {
        super(tr);
        this.epicRepository = epicRepository;
        this.userStoryRepository = userStoryRepository;
        this.productBackLogRepository = productBackLogRepository;
    }

    @Override
    public EpicResponse execute(EpicGetRequest req) {
        if (!this.productBackLogRepository.existsById(req.getProductId())) {
            throw new ProductBackLogNotFoundException();
        }

        return EpicResponseMapper.toResponse(
            this.epicRepository.findById(req.getEpicId()));
    }

    private EpicResponse build(Epic epic) {
        return EpicResponseMapper.toResponse(
            epic, this.userStoryRepository.findAllByEpicId(epic.getId())
                      .stream()
                      .map(UserStoryResponseMapper::toResponse)
                      .toList());
    }
}
