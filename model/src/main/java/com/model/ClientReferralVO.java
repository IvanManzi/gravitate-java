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


    private Long referredBy;


    private boolean isInterested;


    private String  organisationName;


    private String clientName;


    private String clientEmail;


    private String clientDescription;


    private String projectDetails;


    private Date createdAt;


    private Date updatedAt;


}
