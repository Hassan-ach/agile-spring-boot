package com.ensa.agile.application.sprint.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class SprintBackLogCreateRequest {
    @NotBlank private final String name;
    // @NotBlank private final String scrumMasterEmail;
    @NotNull private final LocalDate startDate;
    @NotNull private final LocalDate endDate;

    @NotNull private final List<String> userStoriesIds;

    @NotBlank private String productId;
}
