package com.ensa.agile.infrastructure.persistence.product.jpa.productBackLog;

import com.ensa.agile.domain.product.entity.ProductBackLog;
import com.ensa.agile.application.product.exception.ProductBackLogNotFoundException;
import com.ensa.agile.domain.product.repository.ProductBackLogRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;
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
        .orElseThrow(() -> new ProductBackLogNotFoundException());
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
}
