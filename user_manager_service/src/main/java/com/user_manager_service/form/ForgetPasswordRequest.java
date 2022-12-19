package com.user_manager_service.form;

import javax.validation.constraints.NotNull;

public record ForgetPasswordRequest(@NotNull String password, @NotNull String email) {
}
