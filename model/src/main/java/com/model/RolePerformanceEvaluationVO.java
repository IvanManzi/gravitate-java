package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePerformanceEvaluationVO implements Serializable {

    private static final long serialVersionUID=1;

    private Long rolePerformanceEvaluationId;

    private Long roleId;

    private Long adminId;

    private String criteria;

    private String description;

    private Date createdAt;

    private Date updatedAt;
}
