package com.ensa.agile.application.product.usecase;

import com.ensa.agile.application.epic.mapper.EpicResponseMapper;
import com.ensa.agile.application.global.transaction.ITransactionalWrapper;
import com.ensa.agile.application.global.useCase.BaseUseCase;
import com.ensa.agile.application.product.mapper.ProductBackLogResponseMapper;
import com.ensa.agile.application.product.response.ProductBackLogResponse;
import com.ensa.agile.application.story.mapper.UserStoryResponseMapper;
import com.ensa.agile.domain.epic.repository.EpicRepository;
import com.ensa.agile.domain.product.entity.ProductBackLog;
import com.ensa.agile.domain.product.repository.ProductBackLogRepository;
import com.ensa.agile.domain.story.repository.UserStoryRepository;
import org.springframework.stereotype.Component;

@Component
public class GetProductBackLogUseCase
    extends BaseUseCase<String, ProductBackLogResponse> {
    private final ProductBackLogRepository productBackLogRepository;
    private final EpicRepository epicRepository;
    private final UserStoryRepository userStoryRepository;

    public GetProductBackLogUseCase(ITransactionalWrapper tr,
                                    ProductBackLogRepository pr,
                                    EpicRepository er,
                                    UserStoryRepository usr) {
        super(tr);
        this.productBackLogRepository = pr;
        this.epicRepository = er;
        this.userStoryRepository = usr;
    }

    @Override
    public ProductBackLogResponse execute(String id) {
        // return builld(this.productBackLogRepository.findById(id));
        return ProductBackLogResponseMapper.tLogResponse(
            this.productBackLogRepository.findProductBackLogRowsById(id));
    }

    private ProductBackLogResponse builld(ProductBackLog pr) {
        return ProductBackLogResponseMapper.toResponseWithEpics(
            pr, this.epicRepository.findAllByProductBackLogId(pr.getId())
                    .stream()
                    .map(epic
                         -> EpicResponseMapper.toResponse(
                             epic, this.userStoryRepository
                                       .findAllByEpicId(epic.getId())
                                       .stream()
                                       .map(UserStoryResponseMapper::toResponse)
                                       .toList()))
                    .toList());
    }
}
