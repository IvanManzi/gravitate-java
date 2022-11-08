package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSuggestionVO implements Serializable {

    private static final long serialVersionUID=1L;

    private Long userSuggestionId;

    private Long userId;

    private String name;

    private String email;

    private String suggestion;

    private Date createdAt;

    private Date updatedAt;

}
