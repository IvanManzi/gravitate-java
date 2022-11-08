package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWishVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userWishId;

    private Long wishId;

    private Long userId;

    private String message;

    private Date createdAt;

    private Date updatedAt;

}
