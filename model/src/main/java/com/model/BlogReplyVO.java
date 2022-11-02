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

@Entity(name = "blog_reply")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogReplyVO implements Serializable {

    private final static long serialVersionUID=1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "blog_reply_id", updatable = false)
    private UUID blogReplyId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserVO user;

    @Column(nullable = false)
    private String reply;

    @OneToMany
    @JoinColumn(name = "blog_reply_id")
    @Column(name = "parent")
    private Set<BlogReplyVO> blogReplies;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",updatable = false)
    private Date createdAt;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date updatedAt;
}
