package com.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscussionForumVO implements Serializable {

    private static final long serialVersionUID=1L;

    private Long discussionForumId;

    private Long userId;

    private Integer views;

    private String title;

    private String tags;

    private String description;

    private Date createdAt;

    private Date updatedAt;
}
