package com.ensa.agile.infrastructure.persistence.product.jpa.productBackLog;

import com.ensa.agile.domain.product.entity.ProductBackLog;

public class ProductBackLogJpaMapper {

  public static ProductBackLogJpaEntity
  toJpaEntity(ProductBackLog ProductBackLog) {
    ProductBackLogJpaEntity jpaEntity =
        ProductBackLogJpaEntity.builder()
            .id(ProductBackLog.getId())
            .name(ProductBackLog.getName())
            .description(ProductBackLog.getDescription())
            .createdDate(ProductBackLog.getCreatedDate())
            .createdBy(ProductBackLog.getCreatedBy())
            .lastModifiedDate(ProductBackLog.getLastModifiedDate())
            .lastModifiedBy(ProductBackLog.getLastModifiedBy())
            .build();
    return jpaEntity;
  }

  public static ProductBackLog
  toDomainEntity(ProductBackLogJpaEntity jpaEntity) {
    return new ProductBackLog(
        jpaEntity.getId(), jpaEntity.getName(), jpaEntity.getDescription(),
        jpaEntity.getCreatedDate(), jpaEntity.getCreatedBy(),
        jpaEntity.getLastModifiedDate(), jpaEntity.getLastModifiedBy());
  }
}
