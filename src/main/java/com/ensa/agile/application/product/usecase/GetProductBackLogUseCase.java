package com.ensa.agile.application.product.usecase;

import com.ensa.agile.application.global.transaction.ITransactionalWrapper;
import com.ensa.agile.application.global.useCase.BaseUseCase;
import com.ensa.agile.application.product.mapper.ProductBackLogResponseMapper;
import com.ensa.agile.application.product.response.ProductBackLogResponse;
import com.ensa.agile.domain.product.repository.ProductBackLogRepository;
import org.springframework.stereotype.Component;

@Component
public class GetProductBackLogUseCase
    extends BaseUseCase<String, ProductBackLogResponse> {
    private final ProductBackLogRepository productBackLogRepository;

    public GetProductBackLogUseCase(
        ITransactionalWrapper tr,
        ProductBackLogRepository productBackLogRepository) {

        super(tr);
        this.productBackLogRepository = productBackLogRepository;
    }

    public ProductBackLogResponse execute(String id) {
        return ProductBackLogResponseMapper.tResponse(
            productBackLogRepository.findById(id));
    }
}
