package com.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "user_topic")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTopicVO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "user_topic_id", updatable = false)
    private UUID userTopicId;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private UserVO user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String tags;

    @Column(nullable = false)
    private String problemDescription;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "userTopic",cascade = CascadeType.REMOVE,orphanRemoval = true)
    Set<TopicReplyVO> replies = new HashSet<>();

    @OneToOne(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE,orphanRemoval = true)
    private AdditionalPointVO additionalPoint;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",updatable = false)
    private Date createdAt;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date updatedAt;
}
