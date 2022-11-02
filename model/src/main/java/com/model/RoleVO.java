package com.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity(name = "role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleVO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "role_id", updatable = false)
    private UUID roleId;

    @Column(nullable = false)
    private String roleName;

    @Column(nullable = false)
    private String roleKRA;

    @OneToOne(mappedBy = "role",cascade = CascadeType.REMOVE,orphanRemoval = true)
    private RolePerformanceEvaluationVO rolePerformanceEvaluation;

    @OneToOne(mappedBy = "role",fetch = FetchType.LAZY)
    private UserVO user;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",updatable = false)
    private Date createdAt;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date updatedAt;

}
