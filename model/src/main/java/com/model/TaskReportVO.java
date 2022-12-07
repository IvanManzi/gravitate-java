package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskReportVO implements Serializable {

    private final static long serialVersionUID=1L;
    private Long taskReportId;
    private Long projectId;
    private Long userId;
    private String jiraProjectId;
    private String jiraTaskId;
    private Date startDate;
    private String taskName;
    private Double hoursSpent;
    private Date createdAt;
    private Date updatedAt;
}
