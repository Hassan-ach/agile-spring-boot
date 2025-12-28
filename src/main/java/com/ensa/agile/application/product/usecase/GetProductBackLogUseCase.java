package com.ensa.agile.application.product.usecase;

import com.ensa.agile.application.global.service.IFetchService;
import com.ensa.agile.application.global.transaction.ITransactionalWrapper;
import com.ensa.agile.application.global.usecase.BaseUseCase;
import com.ensa.agile.application.product.request.ProductBackLogGetRequest;
import com.ensa.agile.application.product.response.ProductBackLogResponse;
import com.ensa.agile.domain.product.repository.ProductBackLogRepository;
import org.springframework.stereotype.Component;

@Component
public class GetProductBackLogUseCase
    extends BaseUseCase<ProductBackLogGetRequest, ProductBackLogResponse> {
    private final ProductBackLogRepository productBackLogRepository;
    private IFetchService fetchService;

    public GetProductBackLogUseCase(
        ITransactionalWrapper tr,
        ProductBackLogRepository productBackLogRepository,
        IFetchService fetchService) {

        super(tr);
        this.productBackLogRepository = productBackLogRepository;
        this.fetchService = fetchService;
    }

    public ProductBackLogResponse execute(ProductBackLogGetRequest req) {
        return fetchService.getResponse(req);
        // return ProductBackLogResponseMapper.tResponse(
        //     productBackLogRepository.findById(req.getId()));
    }
}
