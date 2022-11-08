package com.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicReplyVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long topicReplyId;

    private Long topicId;

    private Long userId;

    private String comment;

    private Long parentTopicReplyId;

    private Date createdAt;

    private Date updatedAt;

}
