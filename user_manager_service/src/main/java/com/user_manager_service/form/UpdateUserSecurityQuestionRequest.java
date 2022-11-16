package com.user_manager_service.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record UpdateUserSecurityQuestionRequest(@NotNull Long securityQuestionId, @NotBlank String question,@NotBlank String answer) {
}
