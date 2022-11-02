package com.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity(name = "topic_reply")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicReplyVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "topic_reply_id", updatable = false)
    private UUID topicReplyId;

    @ManyToOne
    @JoinColumn(name = "user_topic_id")
    private UserTopicVO userTopic;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserVO user;

    @Column(nullable = false)
    private String comment;

    @OneToMany
    @JoinColumn(name = "topic_reply_id")
    @Column(name = "parent")
    private Set<TopicReplyVO> topicReplies;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",updatable = false)
    private Date createdAt;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date updatedAt;

}
