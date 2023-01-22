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

    private Long adminId;

    private Long projectLead;

    private String leadJiraAccountId;

    private String jiraProjectKey;

    private Integer status;

    private String projectName;

    private String clientName;

    private String clientEmail;

    private String phoneNumber;

    private String projectDescription;

    private String technologies;

    private Date startDate;

    //0 for investigation(Default),1 for Design, 2 Build, 3 for Testing, 4 for launch
    private Integer phase;

    private Date createdAt;

    private Date updatedAt;

}
