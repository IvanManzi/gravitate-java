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

    //66 for accepted, 77 for rejected, 88 for failed
    private Integer referralStatus;


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
