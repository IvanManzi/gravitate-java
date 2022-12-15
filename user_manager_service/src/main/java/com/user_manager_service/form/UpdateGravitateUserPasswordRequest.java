package com.user_manager_service.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record UpdateGravitateUserPasswordRequest(@NotBlank
                                                 String password,
                                                 @NotNull
                                                 String oldPassword
                                                 ) {
}
