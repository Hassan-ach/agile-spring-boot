
package com.ensa.agile.application.product.usecase;

import org.springframework.stereotype.Component;

import com.ensa.agile.application.global.BaseUseCase;
import com.ensa.agile.application.product.mapper.ProductBackLogResponseMapper;
import com.ensa.agile.application.product.request.ProductBackLogUpdateRequest;
import com.ensa.agile.application.product.response.ProductBackLogResponse;
import com.ensa.agile.domain.product.entity.ProductBackLog;
import com.ensa.agile.domain.product.repository.ProductBackLogRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UpdateProductBackLogInfoUseCase
        implements BaseUseCase<ProductBackLogUpdateRequest, ProductBackLogResponse> {
    private final ProductBackLogRepository productBackLogRepository;

    public ProductBackLogResponse execute(ProductBackLogUpdateRequest data) {
        ProductBackLog productBackLog = productBackLogRepository.findById(data.getId());

        return ProductBackLogResponseMapper.tResponse(
                productBackLogRepository.save(
                        merge(productBackLog, data)));

    }

    private ProductBackLog merge(ProductBackLog oldProductBackLog, ProductBackLogUpdateRequest newProductBackLog) {
        if (newProductBackLog.getName() != null) {
            oldProductBackLog.setName(newProductBackLog.getName());
        }
        if (newProductBackLog.getDescription() != null) {
            oldProductBackLog.setDescription(newProductBackLog.getDescription());
        }

        return oldProductBackLog;

    }
}
