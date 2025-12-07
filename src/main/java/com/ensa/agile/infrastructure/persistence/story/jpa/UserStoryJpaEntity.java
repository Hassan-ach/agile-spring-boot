package com.ensa.agile.infrastructure.persistence.story.jpa;

import com.ensa.agile.domain.story.enums.StoryStatus;
import com.ensa.agile.infrastructure.persistence.global.entity.BaseJpaEntity;
import com.ensa.agile.infrastructure.persistence.sprint.jpa.SprintBacklogJpaEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Table(name = "user_stories")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserStoryJpaEntity extends BaseJpaEntity {
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer priority;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StoryStatus status;

    @Column(name = "story_points")
    private Integer storyPoints;

    @Column(name = "acceptance_criteria")
    private String acceptanceCriteria;

    // @ManyToOne
    // @JoinColumn(name = "epic_id")
    // private Epic epic;

    // @ManyToOne
    // @JoinColumn(name = "product_backlog_id", nullable = false)
    // private ProductBacklog productBacklog;

    @ManyToOne
    @JoinColumn(name = "sprint_backlog_id")
    private SprintBacklogJpaEntity sprintBacklog;

}
