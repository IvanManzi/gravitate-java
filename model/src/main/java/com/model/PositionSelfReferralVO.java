package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PositionSelfReferralVO implements Serializable {

    private static final long serialVersionUID=1L;


    private Long positionSelfReferralId;

    private Long positionId;

    private Long userId;


    //66 for accepted, 77 for rejected, 88 for failed
    private Integer referralStatus;

    private Date createdAt;

    private Date updatedAt;



}
