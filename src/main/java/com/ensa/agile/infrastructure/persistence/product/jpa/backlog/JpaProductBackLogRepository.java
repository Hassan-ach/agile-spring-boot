package com.ensa.agile.infrastructure.persistence.product.jpa.backlog;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaProductBackLogRepository
    extends JpaRepository<ProductBackLogJpaEntity, String> {

    @Query(name = "loadProductBackLogRowsById", nativeQuery = true)
    List<ProductBackLogRow> findProductBackLogRowsById(
       @Param("id") String id);
}
