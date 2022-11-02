package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity(name = "performance_evaluation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePerformanceEvaluationVO implements Serializable {

    private static final long serialVersionUID=1;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "role_performance_evaluation_id", updatable = false)
    private UUID rolePerformanceEvaluationId;

    @OneToOne
    @JoinColumn(name = "role_id")
    private RoleVO role;

    private String criteria;

    private String description;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",updatable = false)
    private Date createdAt;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date updatedAt;
}
