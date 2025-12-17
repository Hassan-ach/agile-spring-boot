package com.ensa.agile.application.story.usecase;

import com.ensa.agile.application.global.transaction.ITransactionalWrapper;
import com.ensa.agile.application.global.useCase.BaseUseCase;
import com.ensa.agile.application.story.mapper.UserStoryResponseMapper;
import com.ensa.agile.application.story.request.UserStoryCreateRequest;
import com.ensa.agile.application.story.response.UserStoryResponse;
import com.ensa.agile.domain.epic.entity.Epic;
import com.ensa.agile.domain.product.entity.ProductBackLog;
import com.ensa.agile.domain.product.repository.ProductBackLogRepository;
import com.ensa.agile.domain.story.entity.UserStory;
import com.ensa.agile.domain.story.enums.StoryStatus;
import com.ensa.agile.domain.story.repository.UserStoryRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateUserStoryUseCase
    extends BaseUseCase<UserStoryCreateRequest, UserStoryResponse> {

    private final UserStoryRepository userStoryRepository;
    private final ProductBackLogRepository productBackLogRepository;

    public CreateUserStoryUseCase(ITransactionalWrapper tr,
                                  UserStoryRepository usr,
                                  ProductBackLogRepository pbr) {
        super(tr);
        this.userStoryRepository = usr;
        this.productBackLogRepository = pbr;
    }

    @Override
    public UserStoryResponse execute(UserStoryCreateRequest request) {
        ProductBackLog pb =
            this.productBackLogRepository.findById(request.getProductId());


        UserStory us = UserStory.builder()
                           .title(request.getTitle())
                           .description(request.getDescription())
                           .status(StoryStatus.TODO)
                           .priority(request.getPriority())
                           .storyPoints(request.getStoryPoints())
                           .acceptanceCriteria(request.getAcceptanceCriteria())
                           .productBackLog(pb)
                           .build();

        return UserStoryResponseMapper.toResponse(
            this.userStoryRepository.save(us));
    }
}
