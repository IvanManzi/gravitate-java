package com.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBlogVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userBlogId;

    private Long userId;

    private String title;

    private String content;

    private Date createdAt;

    private Date updatedAt;
}
