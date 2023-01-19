package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForumAnswerUpVoteVO implements Serializable {

    private static final long serialVersionUID=1L;

    private Long upVoteId;

    private Long forumAnswerId;

    private Long userId;

    private Date createdAt;

    private Date updatedAt;
}
