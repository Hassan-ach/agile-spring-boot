package com.ensa.agile.domain.product.repository;

import com.ensa.agile.domain.global.repository.BaseDomainRepository;
import com.ensa.agile.domain.product.entity.ProductBackLog;
import com.ensa.agile.domain.product.row.ProductBackLogRow;
import java.util.List;

public interface ProductBackLogRepository
    extends BaseDomainRepository<ProductBackLog, String> {

    List<ProductBackLogRow> findProductBackLogRowsById(String id);
}
