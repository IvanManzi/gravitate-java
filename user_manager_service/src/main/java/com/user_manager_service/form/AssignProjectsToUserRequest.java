package com.user_manager_service.form;

import javax.validation.constraints.NotNull;
import java.util.List;

public record AssignProjectsToUserRequest(@NotNull Long userId, @NotNull List<Long> projects) {
}
