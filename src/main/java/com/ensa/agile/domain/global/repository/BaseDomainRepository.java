package com.ensa.agile.domain.global.repository;

import java.util.List;
import java.util.Optional;

public interface BaseDomainRepository<T, ID> {
    T save(T entity);

    Optional<T> findById(ID id);

    List<T> findAll();

    void deleteById(ID id);

    boolean existsById(ID id);
}
