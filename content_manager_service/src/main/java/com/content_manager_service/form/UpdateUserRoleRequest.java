package com.content_manager_service.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record UpdateUserRoleRequest(@NotNull Long roleId, @NotBlank String name, @NotBlank String kra) {
}
