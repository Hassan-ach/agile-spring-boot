package com.ensa.agile.application.epic.usecase;

import com.ensa.agile.application.epic.mapper.EpicResponseMapper;
import com.ensa.agile.application.epic.request.EpicCreateRequest;
import com.ensa.agile.application.epic.response.EpicResponse;
import com.ensa.agile.application.global.transaction.ITransactionalWrapper;
import com.ensa.agile.application.global.useCase.BaseUseCase;
import com.ensa.agile.domain.epic.entity.Epic;
import com.ensa.agile.domain.epic.repository.EpicRepository;
import com.ensa.agile.domain.product.entity.ProductBackLog;
import com.ensa.agile.domain.product.repository.ProductBackLogRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateEpicUseCase
    extends BaseUseCase<EpicCreateRequest, EpicResponse> {

    private final EpicRepository epicRepository;
    private final ProductBackLogRepository prosuctBackLogRepository;

    public CreateEpicUseCase(ITransactionalWrapper tr, EpicRepository ep,
                             ProductBackLogRepository pb) {

        super(tr);
        this.epicRepository = ep;
        this.prosuctBackLogRepository = pb;
    }

    @Override
    public EpicResponse execute(EpicCreateRequest request) {
        ProductBackLog pr =
            prosuctBackLogRepository.findById(request.getProductId());

        Epic epic = Epic.builder()
                        .title(request.getTitle())
                        .description(request.getDescription())
                        .productBackLog(pr)
                        .build();

        return EpicResponseMapper.toResponse(epicRepository.save(epic));
    }
}
