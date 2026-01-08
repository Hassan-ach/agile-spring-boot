package com.ensa.agile.domain.product.backlog;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.ensa.agile.domain.global.exception.ValidationException;
import com.ensa.agile.domain.product.entity.ProductBackLog;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ProductBackLogTest {

    @ParameterizedTest
    @CsvSource({"testName, testDescription, true", "'', testDescription, false",
                "testName, '', false", ", testDescription, false",
                "testName, , false"})
    public void shouldValidateProductBackLogCreation(String name,
                                                     String description,
                                                     boolean expectedValidity) {
        if (expectedValidity) {
            assertDoesNotThrow(() -> {
                ProductBackLog.builder()
                    .name(name)
                    .description(description)
                    .build();
            });
        } else {
            assertThrows(ValidationException.class, () -> {
                ProductBackLog.builder()
                    .name(name)
                    .description(description)
                    .build();
            });
        }
    }

    @ParameterizedTest
    @CsvSource({"testName, testDescription, true", "'', testDescription, false",
                "testName, '', false", ", testDescription, true",
                "testName, , true"})
    public void shouldValidateProductBackLogUpdate(String name,
                                                   String description,
                                                   boolean expectedValidity) {
        ProductBackLog productBackLog = ProductBackLog.builder()
                                            .name("initialName")
                                            .description("initialDescription")
                                            .build();
        if (expectedValidity) {
            assertDoesNotThrow(
                () -> { productBackLog.updateMetadata(name, description); });

            if (name != null)
                assertEquals(name, productBackLog.getName());
            if (description != null)
                assertEquals(description, productBackLog.getDescription());

        } else {
            assertThrows(ValidationException.class, () -> {
                productBackLog.updateMetadata(name, description);
            });
        }
    }
}
