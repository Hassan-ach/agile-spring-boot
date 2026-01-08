package com.ensa.agile.domain.testfactory;

import com.ensa.agile.domain.product.entity.ProductBackLog;

public final class TestProductBackLogFactory {
    public static ProductBackLog validProduct() {
        return ProductBackLog.builder()
            .name("Test Product")
            .description("This is a test product backlog.")
            .build();
    }
}
