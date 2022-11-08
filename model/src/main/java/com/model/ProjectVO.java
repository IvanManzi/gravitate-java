package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long projectId;

    private String projectName;

    private String clientName;

    private String clientEmail;

    private String phoneNumber;

    private String projectDescription;

    private Date createdAt;

    private Date updatedAt;

}
