package com.ensa.agile.application.product.usecase;

import com.ensa.agile.application.global.transaction.ITransactionalWrapper;
import com.ensa.agile.application.global.usecase.BaseUseCase;
import com.ensa.agile.application.product.mapper.ProductBackLogResponseMapper;
import com.ensa.agile.application.product.response.ProductBackLogResponse;
import com.ensa.agile.domain.product.repository.ProductBackLogRepository;
import org.springframework.stereotype.Component;

@Component
public class LoadProductBackLogUseCase
    extends BaseUseCase<String, ProductBackLogResponse> {
    private final ProductBackLogRepository productBackLogRepository;

    public LoadProductBackLogUseCase(ITransactionalWrapper tr,
                                     ProductBackLogRepository pr) {
        super(tr);
        this.productBackLogRepository = pr;
    }

    @Override
    public ProductBackLogResponse execute(String id) {
        return ProductBackLogResponseMapper.toResponseWithEpicsAndUserStories(
            this.productBackLogRepository.findProductBackLogRowsById(id));
    }
}
