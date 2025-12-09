package com.ensa.agile.application.user.usecase;

import com.ensa.agile.application.global.useCase.BaseUseCase;
import com.ensa.agile.application.global.transaction.ITransactionalWrapper;
import com.ensa.agile.domain.user.entity.User;
import com.ensa.agile.domain.user.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class LoadUserInfoUseCase extends BaseUseCase<String, User> {

    private final UserRepository userRepository;

    public LoadUserInfoUseCase(UserRepository userRepository,
                               ITransactionalWrapper transactionalWrapper) {

        super(transactionalWrapper);
        this.userRepository = userRepository;
    }

    public User execute(String email) {
        return userRepository.findByEmail(email);
    }
}
