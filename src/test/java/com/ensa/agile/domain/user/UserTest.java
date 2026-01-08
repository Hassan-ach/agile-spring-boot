package com.ensa.agile.domain.user;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.ensa.agile.domain.global.exception.ValidationException;
import com.ensa.agile.domain.user.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class UserTest {
    @ParameterizedTest
    @CsvSource({"testFirstName, testLastName, test@gmail.com, Test@1234, true",
                ", testLastName, test@gmail.com, Test@1234, false",
                "testFirstName, , test@gmail.com, Test@1234, false",
                "testFirstName, testLastName, invalidEmail, Test@1234, false",
                "testFirstName, testLastName, test@email.com, , false"})
    public void shouldValidateUserCreation(String firstName, String lastName,
                                           String email, String password,
                                           boolean expectedValidity) {
        if (expectedValidity) {
            assertDoesNotThrow(() -> {
                User.builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .email(email)
                    .password(password)
                    .build();
            });
        } else {
            assertThrows(ValidationException.class, () -> {
                User.builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .email(email)
                    .password(password)
                    .build();
            });
        }
    }

    @ParameterizedTest
    @CsvSource({"testFirstName, testLastName, Test@1234, true",
                "'', testLastName, Test@1234, false",
                ", testLastName, Test@1234, true",
                "testFirstName, , Test@1234, true",
                "testFirstName, testLastName, , true"})
    public void shouldValidateUserUpdate(String firstName, String lastName,
                                         String password,
                                         boolean expectedValidity) {
        User user = User.builder()
                        .firstName("initialFirstName")
                        .lastName("initialLastName")
                        .email("initial@gmail.com")
                        .password("Initial@1234")
                        .build();

        if (expectedValidity) {
            assertDoesNotThrow(
                () -> user.updateMetadata(firstName, lastName, password));
        } else {
            assertThrows(
                ValidationException.class,
                () -> user.updateMetadata(firstName, lastName, password));
        }
    }

    @Test
    public void shouldLockeUser() {
        User user = User.builder()
                        .firstName("initialFirstName")
                        .lastName("initialLastName")
                        .email("test@gmail.com")
                        .password("Initial@1234")
                        .build();

        assertEquals(false, user.isLocked());

        user.lockAccount();

        assertEquals(true, user.isLocked());
    }
}
