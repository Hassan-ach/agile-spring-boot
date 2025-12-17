package com.ensa.agile.application.story.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserStoryCreateRequest {

    @NotBlank private String title;

    @NotBlank private String description;

    @NotNull @Min(1) private Integer priority;

    @NotNull @Min(1) private Integer storyPoints;

    @NotBlank private String acceptanceCriteria;

    private String productId;

    public UserStoryCreateRequest(String productId,
                                  UserStoryCreateRequest req) {
        this.productId = productId;
        this.title = req.getTitle();
        this.description = req.getDescription();
        this.priority = req.getPriority();
        this.storyPoints = req.getStoryPoints();
        this.acceptanceCriteria = req.getAcceptanceCriteria();
    }
}
