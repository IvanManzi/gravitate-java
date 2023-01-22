package com.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProjectVO implements Serializable {

    private final static long serialVersionUID=1L;

    private Long userProjectId;

    //set to false by default
    private boolean isFavorite;

    private Long userId;

    private Long projectId;

    private Date createdAt;

    private Date updatedAt;
}
