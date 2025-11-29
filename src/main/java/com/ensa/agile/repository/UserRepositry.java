package com.ensa.agile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensa.agile.model.User;

@Repository
public interface UserRepositry extends JpaRepository<User, Long> {
}
