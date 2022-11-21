package com.user_manager_service.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public record CreateGravitateUserForm(String userType,
                                      @NotNull
                                      Integer isAdmin,
                                      @NotBlank
                                      String firstName,
                                      @NotBlank
                                      String lastName,
                                      @NotBlank
                                      String password,
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
                                      @NotBlank
                                      String email,
                                      @NotBlank
                                      String alternativeEmail,
                                      @NotNull
                                      Long roleId,
                                      @NotBlank
                                      String bankName,
                                      @NotBlank
                                      String accountNumber,
                                      @NotBlank
                                      String profilePicture,
                                      @NotBlank
                                      String contractPath,
                                      @NotNull
                                      Long managerId) {
}
