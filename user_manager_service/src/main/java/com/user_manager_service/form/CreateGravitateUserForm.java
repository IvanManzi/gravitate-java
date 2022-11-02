package com.user_manager_service.form;

import java.util.Date;

public record CreateGravitateUserForm(String firstName,
                                      String lastName,
                                      String password,
                                      String country,
                                      Date dob,
                                      String phoneNumber,
                                      String email,
                                      String alternativeEmail,
                                      Integer roleId,
                                      String bankName,
                                      String profilePicture) {
}
