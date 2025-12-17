package com.ensa.agile.application.product.response;

import com.ensa.agile.application.epic.response.EpicResponse;
import com.ensa.agile.application.story.response.UserStoryResponse;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ProductBackLogResponse {

    private final String id;

    private final String name;

    private final String description;

    private List<EpicResponse> epics;

    private List<UserStoryResponse> userStories;
}
