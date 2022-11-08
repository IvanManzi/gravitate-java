package com.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTopicVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userTopicId;

    private Long userId;

    private String title;

    private String tags;

    private String problemDescription;

    private Date createdAt;

    private Date updatedAt;
}
