package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecurityQuestionVO implements Serializable {

    private static final long serialVersionUID=1L;

    private Long securityQuestionId;

    private Long userId;

    private String question;

    private String answer;

    private Date createdAt;

    private Date updatedAt;
}
