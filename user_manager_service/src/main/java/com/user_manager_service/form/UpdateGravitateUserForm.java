package com.user_manager_service.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public record UpdateGravitateUserForm(@NotNull
                                      Long userId,
                                      @NotBlank String userType,
                                      @NotBlank
                                      Integer userLevel,
                                      @NotBlank
                                      String firstName,
                                      @NotBlank
                                      String otp,
                                      @NotBlank
                                      String lastName,
                                      @NotBlank
                                      String country,
                                      @NotBlank
                                      Date dob,
                                      @NotBlank
                                      Date joinedOn,
                                      @NotBlank
                                      String employmentStatus,
                                      @NotBlank
                                      String billing,
                                      @NotBlank
                                      String phoneNumber,
                                      @Email @NotBlank
                                      String email,
                                      @Email @NotBlank
                                      String alternativeEmail,
                                      @NotBlank
                                      Long roleId,
                                      @NotBlank
                                      String bankName,
                                      @NotBlank
                                      String accountNumber,
                                      @NotBlank
                                      String profilePicture,
                                      @NotBlank
                                      String contractPath,
                                      @NotBlank
                                      String jiraId,

                                      String[] pageAccess,

                                      List<Long> projects,
                                      Long managerId) {
}
