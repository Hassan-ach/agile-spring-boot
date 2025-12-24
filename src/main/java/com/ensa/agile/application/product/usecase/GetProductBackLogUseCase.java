package com.ensa.agile.application.product.usecase;

import com.ensa.agile.application.global.transaction.ITransactionalWrapper;
import com.ensa.agile.application.global.usecase.BaseUseCase;
import com.ensa.agile.application.product.mapper.ProductBackLogResponseMapper;
import com.ensa.agile.application.product.request.ProductBackLogGetRequest;
import com.ensa.agile.application.product.response.ProductBackLogResponse;
import com.ensa.agile.domain.product.repository.ProductBackLogRepository;
import org.springframework.stereotype.Component;

@Component
public class GetProductBackLogUseCase
    extends BaseUseCase<ProductBackLogGetRequest, ProductBackLogResponse> {
    private final ProductBackLogRepository productBackLogRepository;

    public GetProductBackLogUseCase(
        ITransactionalWrapper tr,
        ProductBackLogRepository productBackLogRepository) {

        super(tr);
        this.productBackLogRepository = productBackLogRepository;
    }

    public ProductBackLogResponse execute(ProductBackLogGetRequest req) {
        if (req.getWith() == "") {
            //
        }
        if (req.getWith() == "epics") {
            //
        }
        if (req.getWith() == "sprints") {
            //
        }
        if (req.getWith() == "stories") {
            //
        }
        if (req.getWith() == "all") {
            //
        }
        return null;
    }
}
