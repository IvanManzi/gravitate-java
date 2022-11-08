package com.user_manager_service.form;

import java.util.Date;

public record CreateGravitateUserForm(String userType,
                                      String firstName,
                                      String lastName,
                                      String password,
                                      String country,
                                      Date dob,
                                      Date joinedOn,
                                      String employmentStatus,
                                      String billing,
                                      String phoneNumber,
                                      String email,
                                      String alternativeEmail,
                                      Long roleId,
                                      String bankName,
                                      String accountNumber,
                                      String profilePicture,
                                      String contractPath,
                                      Long managerId) {
}
