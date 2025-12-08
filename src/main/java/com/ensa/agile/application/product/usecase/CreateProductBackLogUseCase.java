package com.ensa.agile.application.product.usecase;

import org.springframework.stereotype.Component;

import com.ensa.agile.application.global.BaseUseCase;
import com.ensa.agile.application.product.mapper.ProductBackLogResponseMapper;
import com.ensa.agile.application.product.request.ProductBackLogCreateRequest;
import com.ensa.agile.application.product.response.ProductBackLogResponse;
import com.ensa.agile.domain.product.entity.ProductBackLog;
import com.ensa.agile.domain.product.repository.ProductBackLogRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CreateProductBackLogUseCase implements BaseUseCase<ProductBackLogCreateRequest, ProductBackLogResponse> {

    private final ProductBackLogRepository productBackLogRepository;

    public ProductBackLogResponse execute(ProductBackLogCreateRequest request) {
        ProductBackLog productBackLog = ProductBackLog.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();

        return ProductBackLogResponseMapper
                .tResponse(productBackLogRepository
                        .save(productBackLog));
    }

}
