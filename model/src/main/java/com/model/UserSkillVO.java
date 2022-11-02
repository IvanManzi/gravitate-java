package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity(name = "user_skill")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSkillVO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "user_skill_id", updatable = false)
    private UUID userSkillId;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private UserVO user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String expertise;

    @Column(nullable = false)
    private String certificatePath;

    @OneToOne(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE,orphanRemoval = true)
    private AdditionalPointVO additionalPoint;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",updatable = false)
    private Date createdAt;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date updatedAt;
}
