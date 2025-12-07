package com.ensa.agile.infrastructure.persistence.product.jpa.productBackLog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaProductBackLogRepository extends JpaRepository<ProductBackLogJpaEntity, String> {

}
