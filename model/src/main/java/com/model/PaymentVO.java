package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentVO implements Serializable {

    private static final long serialVersionUID=1L;

    private Long paymentId;

    private Long userId;


    private Long projectId;


    private Double totalAmount;


    private Integer month;


    private Integer year;


    private Date createdAt;

    private Date updatedAt;


}
