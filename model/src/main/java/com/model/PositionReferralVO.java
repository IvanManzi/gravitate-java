package com.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PositionReferralVO implements Serializable {

    private static final long serialVersionUID=1L;

    private Long positionReferralId;


    private Long positionId;


    private boolean referralStatus;


    private Long referredBy;


    private String firstName;


    private String lastName;


    private String phoneNumber;


    private String country;


    private String emailId;


    private String keySkills;


    private String cvPath;


    private Date createdAt;


    private Date updatedAt;


}
