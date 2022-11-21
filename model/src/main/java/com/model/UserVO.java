package com.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import java.io.Serializable;
import java.util.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private String userType;

    private Integer isAdmin;

    private Long managedBy;

    private String email;

    private String alternateEmail;

    @JsonIgnore
    private String password;

    private String firstName;

    private String lastName;

    private String country;

    private Date dateOfBirth;

    private Date joiningDate;

    private String phoneNumber;

    private String profilePicturePath;

    private String contractPath;

    private Long roleId;

    private String employmentStatus;

    private String billing;

    private String bankName;

    private String accountNumber;

    private Date createdAt;

    private Date updatedAt;

}
