package com.ensa.agile.application.epic.usecase;

import com.ensa.agile.application.common.response.DeleteResponse;
import com.ensa.agile.application.epic.exception.EpicNotFoundException;
import com.ensa.agile.application.epic.request.EpicRequest;
import com.ensa.agile.application.global.transaction.ITransactionalWrapper;
import com.ensa.agile.application.global.useCase.BaseUseCase;
import com.ensa.agile.application.product.exception.ProductBackLogNotFoundException;
import com.ensa.agile.domain.epic.repository.EpicRepository;
import com.ensa.agile.domain.product.repository.ProductBackLogRepository;
import org.springframework.stereotype.Component;

@Component
public class DeleteEpicUseCase
    extends BaseUseCase<EpicRequest, DeleteResponse> {
    private final EpicRepository epicRepository;
    private final ProductBackLogRepository productBackLogRepository;

    public DeleteEpicUseCase(ITransactionalWrapper tr, EpicRepository ep,
                             ProductBackLogRepository pb) {
        super(tr);
        this.epicRepository = ep;
        this.productBackLogRepository = pb;
    }

    @Override
    public DeleteResponse execute(EpicRequest request) {

        if (!this.productBackLogRepository.existsById(request.getProductId())) {
            throw new ProductBackLogNotFoundException();
        }

        if (this.epicRepository.existsById(request.getEpicId())) {
            throw new EpicNotFoundException();
        }

        epicRepository.deleteById(request.getEpicId());

        return DeleteResponse.builder()
            .message("Epic deleted successfully")
            .success(true)
            .build();
    }
}
