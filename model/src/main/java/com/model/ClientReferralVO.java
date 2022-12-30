package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientReferralVO implements Serializable {

    private static final long serialVersionUID=1L;

    private Long clientReferralId;

    //66 for accepted, 77 for pending, 88 for failed
    private Integer referralStatus;


    private String isReferencable;


    private Long referredBy;


    private String  organisationName;


    private String clientName;


    private String phoneNumber;


    private String clientEmail;


    private String clientDescription;


    private String projectDetails;


    private Date createdAt;


    private Date updatedAt;


}
