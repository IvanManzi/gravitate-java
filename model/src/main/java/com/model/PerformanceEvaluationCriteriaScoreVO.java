package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceEvaluationCriteriaScoreVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long performanceEvaluationCriteriaScoreId;

    private Long performanceEvaluationId;


    private Long adminId;

    private Long userId;

    private Integer quarter;


    private Integer sprint;


    private Integer year;


    private Integer points;

    private Date createdAt;

    private Date updatedAt;


}
