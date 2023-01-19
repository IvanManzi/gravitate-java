package com.model;


import com.util.AdminPageAccessTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;
import org.apache.ibatis.annotations.TypeDiscriminator;

import java.io.Serializable;
import java.util.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private String userType;

    private boolean isAccountNonExpired;

    private boolean isAccountNonLocked;

    private boolean status;

    //1 for admin,2 for manager,3 for developer, 4 for client user
    private Integer userLevel;

    private Long managedBy;

    private String[] adminPageAccess;

    @TypeDiscriminator(column = "", typeHandler = AdminPageAccessTypeHandler.class, cases = {})
    public void setAdminPageAccess(String[] adminPageAccess){
        this.adminPageAccess = adminPageAccess;
    }
    public String[] getAdminPageAccess(){
        return this.adminPageAccess;
    }

    private String otp;

    private String email;

    private String jiraId;

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
