package com.ensa.agile.domain.testfactory;

import com.ensa.agile.domain.user.entity.User;

public final class TestUserFactory {

    public static User validUser() {
        return User.builder()
            .firstName("TestFirstName")
            .lastName("TestLastName")
            .email("test@gmail.com")
            .password("Test@1234")
            .build();
    }
}
