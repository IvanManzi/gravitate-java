package com.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalPointVO implements Serializable {

    private static final long serialVersionUID=1L;

    private Long additionalPointId;

    private Long adminId;

    private Long userId;

    private String quarter;

    private Integer points;

    private Long userSkillId;

    private Long userTopicId;

    private Long userBlogId;

    private Date createdAt;

    private Date updatedAt;
}
