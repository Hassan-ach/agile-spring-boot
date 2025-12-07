package com.ensa.agile.application.user.mapper;

import com.ensa.agile.application.user.request.RegisterRequest;
import com.ensa.agile.domain.user.entity.User;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegisterRequestMapper {

    public static User toUser(final RegisterRequest req, final String hashedPassword) {

        return new User(
                req.getFirstName(),
                req.getLastName(),
                req.getEmail(),
                hashedPassword);

    }

}
