package com.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity(name = "additional_point")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalPointVO implements Serializable {

    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "additional_point_id", updatable = false, nullable = false)
    private UUID additionalPointId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserVO user;

    private String quarter;

    private Integer points;

    @OneToOne
    @JoinColumn(name = "user_skill_id")
    private UserSkillVO userSkill;

    @OneToOne
    @JoinColumn(name = "user_topic_id")
    private UserTopicVO userTopic;

    @OneToOne
    @JoinColumn(name = "user_blog_id")
    private UserBlogVO userBlog;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",updatable = false)
    private Date createdAt;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date updatedAt;
}
