package com.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PositionVO implements Serializable {

    private static final long serialVersionUID=1L;

    private Long positionId;


    private Long adminId;


    private Long projectId;

    private boolean isPositionOpen;

    private String jobId;

    private Long roleId;

    private String experience;

    private String keySkills;

    private Double referralAmount;

    private String positionType;


    private String opportunityType;

    private String kra;

    private Double incentiveAmount;

    private Integer points;

    private Date startDate;

    private Date endDate;

    private Date createdAt;

    private Date updatedAt;






}
