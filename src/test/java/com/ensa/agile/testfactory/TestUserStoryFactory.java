package com.ensa.agile.testfactory;

import com.ensa.agile.domain.story.entity.UserStory;
import com.ensa.agile.domain.story.enums.MoscowType;

public class TestUserStoryFactory {

    public static UserStory validUserStory() {
        return UserStory.builder()
            .title("Valid User Story Title")
            .description("Valid User Story Description")
            .priority(MoscowType.MUST_HAVE)
            .storyPoints(5)
            .acceptanceCriteria("Valid Acceptance Criteria")
            .productBackLog(TestProductBackLogFactory.validProduct())
            .build();
    }
}
