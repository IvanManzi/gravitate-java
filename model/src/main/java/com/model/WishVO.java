package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long wishId;

    private Long userId;

    private Long adminId;

    private String withType;

    private String comment;

    private Date createdAt;

    private Date updatedAt;

}
