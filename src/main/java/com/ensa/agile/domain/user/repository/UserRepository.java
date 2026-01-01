package com.ensa.agile.domain.user.repository;

import com.ensa.agile.domain.global.repository.BaseDomainRepository;
import com.ensa.agile.domain.user.entity.User;

public interface UserRepository extends BaseDomainRepository<User, String> {
    User findByEmail(String email);

    boolean existsByEmail(String email);
}
