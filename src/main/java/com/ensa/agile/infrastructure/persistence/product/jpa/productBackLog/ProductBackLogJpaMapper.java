package com.ensa.agile.infrastructure.persistence.product.jpa.productBackLog;

import com.ensa.agile.domain.product.entity.ProductBackLog;

public class ProductBackLogJpaMapper {

    public static ProductBackLogJpaEntity toJpaEntity(ProductBackLog ProductBackLog) {
        ProductBackLogJpaEntity jpaEntity = new ProductBackLogJpaEntity(
                ProductBackLog.getId(),
                ProductBackLog.getName(),
                ProductBackLog.getDescription(),
                ProductBackLog.getCreatedDate(),
                ProductBackLog.getCreatedBy(),
                ProductBackLog.getLastModifiedDate(),
                ProductBackLog.getLastModifiedBy());
        return jpaEntity;
    }

    public static ProductBackLog toDomainEntity(ProductBackLogJpaEntity jpaEntity) {
        return new ProductBackLog(
                jpaEntity.getId(),
                jpaEntity.getName(),
                jpaEntity.getDescription(),
                jpaEntity.getCreatedDate(),
                jpaEntity.getCreatedBy(),
                jpaEntity.getLastModifiedDate(),
                jpaEntity.getLastModifiedBy());

    }
}
