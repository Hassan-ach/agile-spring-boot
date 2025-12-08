package com.ensa.agile.application.user.usecase;

import org.springframework.stereotype.Component;

import com.ensa.agile.application.global.BaseUseCase;
import com.ensa.agile.domain.user.entity.User;
import com.ensa.agile.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class LoadUserInfoUseCase implements BaseUseCase<String, User> {

    private final UserRepository userRepository;

    public User execute(String email) {
        return userRepository.findByEmail(email);
    }
}
