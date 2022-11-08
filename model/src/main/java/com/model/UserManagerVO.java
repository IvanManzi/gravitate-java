package com.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserManagerVO implements Serializable {

    private static final long serialVersionUID=1L;

    private Long userManagerId;

    private Long managerId;

    private Long userId;

    private Date createdAt;

    private Date updatedAt;
}
