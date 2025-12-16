package com.ensa.agile.application.product.mapper;

import com.ensa.agile.application.product.response.ProductBackLogResponse;
import com.ensa.agile.domain.product.entity.ProductBackLog;

public class ProductBackLogResponseMapper {

    public static ProductBackLogResponse
    tResponse(ProductBackLog productBackLog) {
        return ProductBackLogResponse.builder()
            .id(productBackLog.getId())
            .name(productBackLog.getName())
            .description(productBackLog.getDescription())
            .build();
    }
}
