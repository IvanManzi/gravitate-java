package com.user_manager_service.form;

import javax.validation.constraints.NotBlank;

public record CreateUserSecurityQuestionRequest(@NotBlank String question,@NotBlank String answer) {
}
