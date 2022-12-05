package com.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class PolicyVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long policyId;

    private Long adminId;

    private String policyType;

    private String policyName;

    private String policyFilePath;

    private Date createdAt;

    private Date updatedAt;

}
