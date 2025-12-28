package com.ensa.agile.domain.product.repository;

import com.ensa.agile.domain.global.repository.BaseDomainRepository;
import com.ensa.agile.domain.product.entity.ProductBackLog;

public interface ProductBackLogRepository
    extends BaseDomainRepository<ProductBackLog, String> {

    ProductBackLog findProductBackLogById(String id);
}
