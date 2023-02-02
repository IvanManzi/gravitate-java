package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceFeedbackVO implements Serializable {

    private static final long serialVersionUID=1L;


    private Long performanceFeedbackId;


    private Long doneBy;


    private Long userId;


    private Integer year;


    private Integer quarter;


    private Integer sprint;


    private String feedback;


    private Date createdAt;


    private Date updatedAt;


}
