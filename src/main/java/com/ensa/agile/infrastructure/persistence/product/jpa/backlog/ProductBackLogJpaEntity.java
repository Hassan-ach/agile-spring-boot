package com.ensa.agile.infrastructure.persistence.product.jpa.backlog;

import com.ensa.agile.infrastructure.persistence.global.entity.BaseJpaEntity;
import jakarta.persistence.Column;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@NamedNativeQuery(name = "loadProductBackLogRowsById", query = """
    SELECT 
    p.id AS backlogId, p.name AS backlogName, p.description AS backlogDescription,

    e.id AS epicId, e.title AS epicTitle, e.description AS epicDescription,

    u.id AS storyId, u.title AS storyTitle, u.description AS storyDescription,
    u.priority AS priority, u.story_points AS storyPoints,
    u.acceptance_criteria AS acceptanceCriteria
FROM product_backlogs p
LEFT JOIN epics e ON e.product_backlog_id = p.id
LEFT JOIN user_stories u ON u.epic_id = e.id
WHERE p.id = :id

UNION ALL

SELECT 
    p.id AS backlogId, p.name AS backlogName, p.description AS backlogDescription,

    NULL AS epicId, NULL AS epicTitle, NULL AS epicDescription,

    u.id AS storyId, u.title AS storyTitle, u.description AS storyDescription,
    u.priority AS priority, u.story_points AS storyPoints,
    u.acceptance_criteria AS acceptanceCriteria
FROM product_backlogs p
INNER JOIN user_stories u ON u.product_backlog_id = p.id
WHERE p.id = :id AND u.epic_id IS NULL
        """,
                  resultSetMapping = "ProductBackLogRowMapping")

@SqlResultSetMapping(
    name = "ProductBackLogRowMapping",
    classes = @ConstructorResult(
        targetClass = ProductBackLogRow.class,
        columns =
        {
            @ColumnResult(name = "backlogId", type = String.class)
            , @ColumnResult(name = "backlogName", type = String.class),
                @ColumnResult(name = "backlogDescription", type = String.class),

                @ColumnResult(name = "epicId", type = String.class),
                @ColumnResult(name = "epicTitle", type = String.class),
                @ColumnResult(name = "epicDescription", type = String.class),

                @ColumnResult(name = "storyId", type = String.class),
                @ColumnResult(name = "storyTitle", type = String.class),
                @ColumnResult(name = "storyDescription", type = String.class),
                @ColumnResult(name = "priority", type = String.class),
                @ColumnResult(name = "storyPoints", type = Integer.class),
                @ColumnResult(name = "acceptanceCriteria", type = String.class)
        }

        ))
@Getter
@SuperBuilder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_backlogs")
public class ProductBackLogJpaEntity extends BaseJpaEntity {

    @Column(nullable = false) private String name;

    @Column(nullable = false) private String description;

    public ProductBackLogJpaEntity(String id, String name, String description,
                                   LocalDateTime createdDate, String createdBy,
                                   LocalDateTime lastModifiedDate,
                                   String lastModifiedBy) {
        super(id, createdDate, createdBy, lastModifiedDate, lastModifiedBy);
        this.name = name;
        this.description = description;
    }
}
