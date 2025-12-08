package com.ensa.agile.application.product.mapper;

import com.ensa.agile.application.product.response.ProductBackLogResponse;
import com.ensa.agile.domain.product.entity.ProductBackLog;

public class ProductBackLogUpdateResponseMapper {

    public static ProductBackLogResponse tResponse(ProductBackLog productBackLog) {
        return new ProductBackLogResponse(
                productBackLog.getId(),
                productBackLog.getName(),
                productBackLog.getDescription());
    }
}
