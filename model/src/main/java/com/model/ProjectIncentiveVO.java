package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectIncentiveVO implements Serializable {

    private static final long serialVersionUID=1L;

    private Long projectIncentiveId;
    private Long projectId;
    private Long userId;
    private Long adminId;
    private boolean status;
    private Double performanceBonus;
    private Double clientReferral;
    private Double employeeReferral;
    private Double hotOpportunity;
    private Double totalAmount;
    private Date createdAt;
    private Date updatedAt;


}
