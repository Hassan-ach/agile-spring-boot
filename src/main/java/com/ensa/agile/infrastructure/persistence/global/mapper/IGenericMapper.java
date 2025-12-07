package com.ensa.agile.infrastructure.persistence.global.mapper;

import com.ensa.agile.domain.global.entity.BaseDomainEntity;
import com.ensa.agile.infrastructure.persistence.global.entity.BaseJpaEntity;

public interface IGenericMapper<D extends BaseDomainEntity, J extends BaseJpaEntity> {

    BaseJpaEntity toJpaEntity(D entity);

    BaseDomainEntity toDomainEntity(BaseJpaEntity user);
}
