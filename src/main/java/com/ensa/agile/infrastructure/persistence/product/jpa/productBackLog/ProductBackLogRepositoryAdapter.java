package com.ensa.agile.infrastructure.persistence.product.jpa.productBackLog;

import com.ensa.agile.application.product.exception.ProductBackLogNotFoundException;
import com.ensa.agile.domain.product.entity.ProductBackLog;
import com.ensa.agile.domain.product.repository.ProductBackLogRepository;
import com.ensa.agile.domain.product.row.ProductBackLogRow;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductBackLogRepositoryAdapter
    implements ProductBackLogRepository {
    private final JpaProductBackLogRepository jpaProductBackLogRepository;

    @Override
    public ProductBackLog save(ProductBackLog entity) {
        return ProductBackLogJpaMapper.toDomainEntity(
            this.jpaProductBackLogRepository.save(
                ProductBackLogJpaMapper.toJpaEntity(entity)));
    }

    @Override
    public ProductBackLog findById(String s) {
        return this.jpaProductBackLogRepository.findById(s)
            .map(ProductBackLogJpaMapper::toDomainEntity)
            .orElseThrow(ProductBackLogNotFoundException::new);
    }

    @Override
    public List<ProductBackLog> findAll() {
        return this.jpaProductBackLogRepository.findAll()
            .stream()
            .map(ProductBackLogJpaMapper::toDomainEntity)
            .toList();
    }

    @Override
    public void deleteById(String s) {
        this.jpaProductBackLogRepository.deleteById(s);
    }

    @Override
    public boolean existsById(String s) {
        return this.jpaProductBackLogRepository.existsById(s);
    }

    @Override
    public List<ProductBackLogRow> findProductBackLogRowsById(String id) {

        return this.jpaProductBackLogRepository.findProductBackLogRowsById(id);
    }
}
