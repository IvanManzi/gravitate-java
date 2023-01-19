package com.user_manager_service.form;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public record CreateGravitateUserForm(@NotNull String userType,
                                      @NotBlank
                                      Integer userLevel,
                                      @NotBlank
                                      String firstName,
                                      @NotBlank
                                      String lastName,
                                      @NotBlank
                                      String otp,
                                      @NotBlank
                                      String country,
                                      @NotBlank
                                      Date dob,
                                      @NotBlank
                                      Date joinedOn,
                                      @NotBlank
                                      String employmentStatus,
                                      String billing,
                                      @NotBlank
                                      String phoneNumber,
                                      @Email @NotBlank
                                      String email,
                                      @Email @NotBlank
                                      String alternativeEmail,
                                      Long roleId,
                                      @NotBlank
                                      String bankName,
                                      @NotBlank
                                      String accountNumber,
                                      @NotBlank
                                      String profilePicture,
                                      @NotBlank
                                      String contractPath,
                                      String jiraId,

                                      String[] pageAccess,
                                      List<Long> projects,
                                      Long managerId) {
}
