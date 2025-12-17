package com.ensa.agile.infrastructure.persistence.product.jpa.productBackLog;

import com.ensa.agile.domain.product.row.ProductBackLogRow;
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
        p.id as backlogId, p.name as backlogName, p.description as backlogDescription,

        e.id as epicId, e.title as epicTitle, e.description as epicDescription,

        u.id as storyId, u.title as storyTitle, u.description as storyDescription,
        u.status as status, u.priority as priority, u.story_points as storyPoints,
        u.acceptance_criteria as acceptanceCriteria

        FROM product_backlogs p
        LEFT JOIN epics e ON e.product_backlog_id = p.id
        LEFT JOIN user_stories u ON u.epic_id = e.id
            OR (u.epic_id IS NULL AND u.product_backlog_id = p.id)
        WHERE p.id = :id
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
                @ColumnResult(name = "status", type = String.class),
                @ColumnResult(name = "priority", type = Integer.class),
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
