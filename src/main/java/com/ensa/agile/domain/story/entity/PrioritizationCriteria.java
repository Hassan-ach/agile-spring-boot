package com.ensa.agile.domain.story.entity;

import java.time.LocalDate;

import com.ensa.agile.domain.global.entity.BaseDomainEntity;
import com.ensa.agile.domain.story.enums.MoscowType;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PrioritizationCriteria extends BaseDomainEntity {

        private final UserStory userStory;

        private final Integer buisnessVakue;

        private final Integer uregency;

        private final Integer complexity;

        private final Integer risk;

        private final String dependencies;

        private final MoscowType moscowCategory;

        public PrioritizationCriteria(UserStory userStory, Integer buisnessVakue, Integer uregency,
                        Integer complexity, Integer risk, String dependencies, MoscowType moscowCategory) {
                super(null);
                this.userStory = userStory;
                this.buisnessVakue = buisnessVakue;
                this.uregency = uregency;
                this.complexity = complexity;
                this.risk = risk;
                this.dependencies = dependencies;
                this.moscowCategory = moscowCategory;
        }

        public PrioritizationCriteria(String id, UserStory userStory, Integer buisnessVakue, Integer uregency,
                        Integer complexity, Integer risk, String dependencies, MoscowType moscowCategory,
                        LocalDate createdDate, String createdBy, LocalDate lastModifiedDate, String lastModifiedBy) {
                super(id, createdDate, createdBy, lastModifiedDate, lastModifiedBy);
                this.userStory = userStory;
                this.buisnessVakue = buisnessVakue;
                this.uregency = uregency;
                this.complexity = complexity;
                this.risk = risk;
                this.dependencies = dependencies;
                this.moscowCategory = moscowCategory;
        }

}
