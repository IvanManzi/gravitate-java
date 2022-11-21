package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSkillVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userSkillId;

    private Long userId;

    private String title;

    private String category;

    private String expertise;

    private String certificatePath;

    private Date createdAt;

    private Date updatedAt;
}
