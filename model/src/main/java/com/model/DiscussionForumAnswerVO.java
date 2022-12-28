package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscussionForumAnswerVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long forumAnswerId;

    private Long forumId;

    private Long userId;

    private String comment;

    private Long parent;

    private Date createdAt;

    private Date updatedAt;
}
