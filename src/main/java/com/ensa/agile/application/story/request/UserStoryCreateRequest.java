package com.ensa.agile.application.story.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserStoryCreateRequest {

    @NotBlank private final String title;

    @NotBlank private final String description;

    @NotBlank private final Integer priority;

    @NotBlank private final Integer storyPoints;

    @NotBlank private final String acceptanceCriteria;

    private String productId;
}
