package com.user_manager_service.form;

import javax.validation.constraints.NotNull;

public record CreateUserSkillRequest(@NotNull String title,@NotNull String category,@NotNull String expertise, String certificateUrl) {
}
