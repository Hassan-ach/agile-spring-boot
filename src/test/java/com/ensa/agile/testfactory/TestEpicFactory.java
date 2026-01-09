package com.ensa.agile.testfactory;

import com.ensa.agile.domain.epic.entity.Epic;

public class TestEpicFactory {

    public static Epic validEpic() {
        return Epic.builder()
            .title("Valid Epic Title")
            .description("Valid Epic Description")
            .productBackLog(TestProductBackLogFactory.validProduct())
            .build();
    }
}
