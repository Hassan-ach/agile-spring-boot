package com.ensa.agile.testfactory;

import com.ensa.agile.domain.sprint.entity.SprintBackLog;

public class TestSprintBackLogFactory {

    public static SprintBackLog validSprintBackLog() {
        return SprintBackLog.builder()
            .name("Valid Sprint Backlog")
            .goal("Valid Sprint Goal")
            .productBackLog(TestProductBackLogFactory.validProduct())
            .scrumMaster(TestUserFactory.validUser())
            .startDate(java.time.LocalDate.now())
            .endDate(java.time.LocalDate.now().plusWeeks(2))
            .build();
    }
}
