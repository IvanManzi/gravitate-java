package com.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogReplyVO implements Serializable {

    private final static long serialVersionUID=1L;

    private Long blogReplyId;

    private Long userId;

    private String reply;

    private Long parentBlogReplyId;

    private Date createdAt;

    private Date updatedAt;
}
