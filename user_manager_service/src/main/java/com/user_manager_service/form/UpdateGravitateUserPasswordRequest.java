package com.user_manager_service.form;

import javax.validation.constraints.NotBlank;

public record UpdateGravitateUserPasswordRequest(@NotBlank String password) {
}
