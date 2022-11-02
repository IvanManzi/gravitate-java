package com.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
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
    @Column(name = "blog_reply_id", updatable = false, nullable = false)
    private UUID blogReplyId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserVO user;

    private String reply;

    /*@ManyToMany
    @JoinColumn(name = "blog_reply_id")
    private BlogReplyVO blogReply;*/

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",updatable = false)
    private Date createdAt;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date updatedAt;
}
